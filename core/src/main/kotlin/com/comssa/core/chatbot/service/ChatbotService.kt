package com.comssa.core.chatbot.service

import com.comssa.core.chatbot.dto.request.ChatRequestDto
import org.springframework.security.oauth2.core.user.OAuth2User

interface ChatbotService {
	fun talkToAssistant(
		chatRequestDto: ChatRequestDto,
		user: OAuth2User?,
	): String
}
