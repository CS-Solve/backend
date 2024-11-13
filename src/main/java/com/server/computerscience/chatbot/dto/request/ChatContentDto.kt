package com.server.computerscience.chatbot.dto.request

import com.server.computerscience.chatbot.domain.ChatContentType

data class ChatContentDto(
    val type: ChatContentType? = null,
    val text: String? = null
) {

    companion object {
        @JvmStatic
        fun from(type: ChatContentType, text: String?): ChatContentDto {
            return ChatContentDto(
                type = type,
                text = text
            )
        }
    }
}
