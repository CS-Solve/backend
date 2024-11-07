package com.server.computerscience.chatbot.service;

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;

public interface ChatbotService {
	String chat(ChatBotRequestDto chatBotRequestDto);
}
