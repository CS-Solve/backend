package com.study.computerscience.chatbot.service

import com.study.computerscience.chatbot.dto.request.ChatBotRequestDto
import org.springframework.security.oauth2.core.user.OAuth2User

interface ChatbotService {
	fun talkToAssistant(
		chatBotRequestDto: ChatBotRequestDto,
		user: OAuth2User?,
	): String
}
