package com.server.computerscience.chatbot.dto.request

data class ChatGptRestRequestDto(
    val model: String? = null,
    val messages: List<ChatMessageDto?>? = null
) {
    companion object {
        @JvmStatic
        fun from(model: String?, chatMessages: List<ChatMessageDto?>?): ChatGptRestRequestDto {
            return ChatGptRestRequestDto(
                model = model,
                messages = chatMessages
            )
        }
    }
}
