package com.server.computerscience.chatbot.dto.response


class ChatBotResponseDto(
    val content: String? = null
) {

    companion object {
        @JvmStatic
        fun from(content: String?): ChatBotResponseDto {
            return ChatBotResponseDto(
                content = content
            )
        }
    }
}
