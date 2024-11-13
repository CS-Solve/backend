package com.server.computerscience.chatbot.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ChatGptRequestFileUploadDto(
    val customId: String? = null,
    val method: String? = null,
    val url: String? = null,
    val body: ChatGptRestRequestDto? = null
) {

    companion object {
        @JvmStatic
        fun from(
            body: ChatGptRestRequestDto?,
            customId: String?,
            method: String?,
            url: String?
        ): ChatGptRequestFileUploadDto {
            return ChatGptRequestFileUploadDto(
                customId = customId,
                method = method,
                body = body,
                url = url
            )
        }
    }
}
