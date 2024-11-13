package com.server.computerscience.chatbot.service.implement

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto
import com.server.computerscience.chatbot.service.ChatbotService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class LoginChatBotService(
    private val chatManageService: ChatManageService
) : ChatbotService {
    private val NOT_LOGIN = "로그인이 필요합니다."

    /**
    lateinint - Null일 경우 UninitializedPropertyAccessException 발생
     */
    @Value("\${spring.security.oauth2.client.provider.cognito.user-name-attribute}")
    private lateinit var userIdentifier: String;

    /*
    스프링 시큐리티 컨텍스트에서 가져오는 User는 Nullable할 수 있음
     */
    override fun talkToAssistant(chatBotRequestDto: ChatBotRequestDto, user: OAuth2User?): String {
        if (user == null) {
            return NOT_LOGIN
        }
        val userId = user.attributes[userIdentifier] as? String ?: return NOT_LOGIN
        return chatManageService.talkForChat(userId, chatBotRequestDto)
    }
}
