package com.comssa.core.chatbot.service

import com.comssa.core.chatbot.dto.request.ChatBotRequestDto
import org.springframework.security.oauth2.core.user.OAuth2User

interface ChatbotService {
	fun talkToAssistant(
		chatBotRequestDto: ChatBotRequestDto,
		user: OAuth2User?,
	): String
}
