package com.server.computerscience.chatbot.service.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.service.ChatbotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginChatBotService implements ChatbotService {
	private final ChatManageService chatManageService;
	private final String NOT_LOGIN = "로그인이 필요합니다.";
	@Value("${spring.security.oauth2.client.provider.cognito.user-name-attribute}")
	private String userIdentifier;

	@Override
	public String talkToAssistant(ChatBotRequestDto chatBotRequestDto, OAuth2User user) {
		if (user == null) {
			return NOT_LOGIN;
		}
		String userId = (String)user.getAttributes().get(userIdentifier);
		return chatManageService.talkForChat(userId, chatBotRequestDto);
	}
}
