package com.server.computerscience.chatbot.service.implement;

import java.util.Arrays;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.domain.ChatRole;
import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.dto.request.ChatMessageDto;
import com.server.computerscience.chatbot.service.ChatbotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginChatBotService implements ChatbotService {
	private final ChatGptService chatGptService;
	private final String NOT_LOGIN = "로그인이 필요합니다.";

	@Override
	public String talkToAssistant(ChatBotRequestDto chatBotRequestDto, OAuth2User user) {
		if (user == null) {
			return NOT_LOGIN;
		}
		String answer = chatGptService.chat(Arrays.asList(ChatMessageDto.from(chatBotRequestDto.getPrompt(),
			ChatRole.USER)));
		return answer;
	}
}
