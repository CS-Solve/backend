package com.server.computerscience.chatbot.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;

public interface ChatbotService {
	String talkToAssistant(ChatBotRequestDto chatBotRequestDto, OAuth2User user);
}
