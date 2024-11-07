package com.server.computerscience.chatbot.service.implement;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.dto.request.ChatGptRestRequestDto;
import com.server.computerscience.chatbot.dto.response.ChatGptResponseDto;
import com.server.computerscience.chatbot.service.ChatbotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatGptService implements ChatbotService {
	private final String model = "gpt-4o-mini";
	private final String payingApiUrl = "https://api.openai.com/v1/chat/completions";
	private final RestTemplate restTemplate;

	@Override
	public String chat(ChatBotRequestDto chatBotRequestDto) {
		ChatGptRestRequestDto chatGptRestRequestDto = ChatGptRestRequestDto.from(model,
			Arrays.asList(chatBotRequestDto.getPrompt()));
		ChatGptResponseDto chatGptResponseDto = restTemplate.postForObject(
			payingApiUrl,
			chatGptRestRequestDto,
			ChatGptResponseDto.class);
		return chatGptResponseDto.getChoices().get(0).getMessage().getContent();
	}
}
