package com.comssa.persistence.chatbot.dto.request

data class ChatGptRestRequestDto(
	val model: String,
	val messages: List<ChatGptMessageDto?>,
) {
	companion object {
		@JvmStatic
		fun from(
			model: String,
			chatMessages: List<ChatGptMessageDto?>,
		): ChatGptRestRequestDto =
			ChatGptRestRequestDto(
				model = model,
				messages = chatMessages,
			)
	}
}
