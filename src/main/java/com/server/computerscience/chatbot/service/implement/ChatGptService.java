package com.server.computerscience.chatbot.service.implement;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.server.computerscience.chatbot.config.RestTemplateService;
import com.server.computerscience.chatbot.dto.request.ChatGptBatchRequestDto;
import com.server.computerscience.chatbot.dto.request.ChatGptRequestFileUploadDto;
import com.server.computerscience.chatbot.dto.request.ChatGptRestRequestDto;
import com.server.computerscience.chatbot.dto.request.ChatMessageDto;
import com.server.computerscience.chatbot.dto.response.ChatGptBatchResponseDto;
import com.server.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto;
import com.server.computerscience.chatbot.dto.response.ChatGptResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatGptService {
	private final String model = "gpt-4o-mini";
	private final String BASE_URL = "https://api.openai.com";
	private final String advancedChatApiUrl = "/v1/chat/completions";
	private final String fileUploadUrl = "/v1/files";
	private final String batchCreateUrl = "/v1/batches";
	private final RestTemplateService restTemplateService;
	private final FileConvertService fileConvertService;
	@Value("${openai.secret-key}")
	private String secretKey;

	private static MultiValueMap<String, Object> makeBodyForFileUpload(ByteArrayResource resource) {
		// body 생성
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", resource);
		body.add("purpose", "batch");
		return body;
	}

	public String sendChatMessage(List<ChatMessageDto> chatMessages) {
		ChatGptRestRequestDto chatGptRestRequestDto = ChatGptRestRequestDto.from(model,
			chatMessages);
		return Objects.requireNonNull(restTemplateService.sendPostRequest(
				BASE_URL + advancedChatApiUrl,
				secretKey,
				MediaType.APPLICATION_JSON,
				chatGptRestRequestDto,
				ChatGptResponseDto.class
			).getBody())
			.getFirstChoiceContent();
	}

	public ChatGptFileUploadResponseDto sendFileUploadMessage(List<ChatMessageDto> chatMessages) {
		List<ChatGptRequestFileUploadDto> dataForFile = makeFileUploadDto(chatMessages);
		// 메모리 내 ByteArrayResource로 변환
		ByteArrayResource resource = fileConvertService.convertToByteArrayResource(dataForFile);
		MultiValueMap<String, Object> body = makeBodyForFileUpload(resource);

		return restTemplateService.sendPostRequest(
			BASE_URL + fileUploadUrl,
			secretKey,
			MediaType.MULTIPART_FORM_DATA,
			body,
			ChatGptFileUploadResponseDto.class
		).getBody();
	}

	private List<ChatGptRequestFileUploadDto> makeFileUploadDto(List<ChatMessageDto> chatMessages) {
		ChatGptRequestFileUploadDto chatGptRequestFileUploadDto = ChatGptRequestFileUploadDto.from(
			ChatGptRestRequestDto.from(model, chatMessages),
			String.valueOf(LocalDateTime.now()),
			"POST",
			advancedChatApiUrl
		);

		List<ChatGptRequestFileUploadDto> dataForFile = Collections.singletonList(chatGptRequestFileUploadDto);
		return dataForFile;
	}

	public ChatGptBatchResponseDto sendBatchMessage(ChatGptBatchRequestDto chatGptBatchRequestDto) {
		return restTemplateService.sendPostRequest(
			BASE_URL + batchCreateUrl,
			secretKey,
			MediaType.APPLICATION_JSON,
			chatGptBatchRequestDto,
			ChatGptBatchResponseDto.class
		).getBody();
	}
}
