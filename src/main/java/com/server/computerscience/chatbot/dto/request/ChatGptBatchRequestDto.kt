package com.server.computerscience.chatbot.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ChatGptBatchRequestDto(
    val inputFileId: String? = null,
    val endpoint: String? = null,
    val completionWindow: String? = null

) {

    companion object {
        @JvmStatic
        fun from(inputFileId: String?, endpoint: String?, completionWindow: String?): ChatGptBatchRequestDto {
            return ChatGptBatchRequestDto(
                inputFileId = inputFileId,
                endpoint = endpoint,
                completionWindow = completionWindow
            )
        }
    }
}
