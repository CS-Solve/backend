package com.comssa.core.chatbot.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ChatGptBatchRequestDto(
	val inputFileId: String,
	val endpoint: String,
	val completionWindow: String,
) {
	companion object {
		@JvmStatic
		fun from(
			inputFileId: String,
			endpoint: String,
			completionWindow: String,
		): com.comssa.core.chatbot.dto.request.ChatGptBatchRequestDto =
			com.comssa.core.chatbot.dto.request.ChatGptBatchRequestDto(
				inputFileId = inputFileId,
				endpoint = endpoint,
				completionWindow = completionWindow,
			)
	}
}
