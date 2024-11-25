package com.comssa.core.chatbot.service.implement

import com.comssa.core.authuser.AuthUserService
import com.comssa.core.chatbot.dto.request.ChatRequestDto
import com.comssa.core.chatbot.service.ChatbotService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class LoginChatBotService(
	private val chatManageService: ChatManageService,
	private val authUserService: AuthUserService,
) : ChatbotService {
	/*
	스프링 시큐리티 컨텍스트에서 가져오는 User는 Nullable할 수 있음
	 */
	override fun talkToAssistant(
		chatRequestDto: ChatRequestDto,
		user: OAuth2User?,
	): String {
		val cognitoId = authUserService.getCognitoId(user) ?: return NOT_LOGIN
		return chatManageService.talkForChat(cognitoId, chatRequestDto)
	}

	companion object {
		const val NOT_LOGIN = "로그인이 필요합니다."
	}
}
