package com.core.computerscience.chatbot.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ChatGptRequestFileUploadDto(
	val customId: String,
	val method: String,
	val url: String,
	val body: ChatGptRestRequestDto,
) {
	companion object {
		@JvmStatic
		fun from(
			body: ChatGptRestRequestDto,
			customId: String,
			method: String,
			url: String,
		): ChatGptRequestFileUploadDto =
			ChatGptRequestFileUploadDto(
				customId = customId,
				method = method,
				body = body,
				url = url,
			)
	}
}
