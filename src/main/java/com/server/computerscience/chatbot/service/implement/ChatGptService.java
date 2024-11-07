package com.server.computerscience.chatbot.service.implement;

import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.server.computerscience.chatbot.dto.request.ChatGptRestRequestDto;
import com.server.computerscience.chatbot.dto.response.ChatGptResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatGptService {
	private final String model = "gpt-4o-mini";
	private final String payingApiUrl = "https://api.openai.com/v1/chat/completions";
	private final RestTemplate restTemplate;

	public String chat(String prompt) {
		ChatGptRestRequestDto chatGptRestRequestDto = ChatGptRestRequestDto.from(model,
			Collections.singletonList(prompt));
		ChatGptResponseDto chatGptResponseDto = restTemplate.postForObject(
			payingApiUrl,
			chatGptRestRequestDto,
			ChatGptResponseDto.class);
		return chatGptResponseDto.getChoices().get(0).getMessage().getContent();
	}
}
