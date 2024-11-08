package com.server.computerscience.chatbot.service.implement;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	@Value("${openai.secret-key}")
	private String secretKey;
	private final String model = "gpt-4o-mini";
	private final String BASE_URL = "https://api.openai.com";
	private final String advancedChatApiUrl = "/v1/chat/completions";
	private final String fileUploadUrl = "/v1/files";
	private final String batchCreateUrl = "/v1/batches";
	private final RestTemplateService restTemplateService;
	private final FileConvertService fileConvertService;

	public String sendChatMessage(List<ChatMessageDto> chatMessages) {
		ChatGptRestRequestDto chatGptRestRequestDto = ChatGptRestRequestDto.from(model,
			chatMessages);
		ResponseEntity<ChatGptResponseDto> response = restTemplateService.sendPostRequest(
			BASE_URL + advancedChatApiUrl,
			secretKey,
			MediaType.APPLICATION_JSON,
			chatGptRestRequestDto,
			ChatGptResponseDto.class
		);
		return response.getBody().getFirstChoiceContent();
	}

	public ChatGptFileUploadResponseDto sendFileUploadMessage(List<ChatMessageDto> chatMessages) {
		ChatGptRestRequestDto chatGptRestRequestDto = ChatGptRestRequestDto.from(model, chatMessages);
		ChatGptRequestFileUploadDto chatGptRequestFileUploadDto = ChatGptRequestFileUploadDto.from(
			chatGptRestRequestDto,
			String.valueOf(LocalDateTime.now()),
			"POST",
			advancedChatApiUrl
		);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(chatGptRequestFileUploadDto));

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		List<ChatGptRequestFileUploadDto> dataForFile = Arrays.asList(chatGptRequestFileUploadDto);
		// 메모리 내 ByteArrayResource로 변환
		ByteArrayResource resource = fileConvertService.convertToByteArrayResource(dataForFile);
		// MultiValueMap to hold form data
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", resource);
		body.add("purpose", "batch");

		ResponseEntity<ChatGptFileUploadResponseDto> response = restTemplateService.sendPostRequest(
			BASE_URL + fileUploadUrl,
			secretKey,
			MediaType.MULTIPART_FORM_DATA,
			body,
			ChatGptFileUploadResponseDto.class
		);
		return response.getBody();
	}

	public ChatGptBatchResponseDto sendBatchMessage(ChatGptBatchRequestDto chatGptBatchRequestDto) {
		ResponseEntity<ChatGptBatchResponseDto> response = restTemplateService.sendPostRequest(
			BASE_URL + batchCreateUrl,
			secretKey,
			MediaType.APPLICATION_JSON,
			chatGptBatchRequestDto,
			ChatGptBatchResponseDto.class
		);
		return response.getBody();
	}
}
