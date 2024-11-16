package com.core.computerscience.chatbot.dto.request

data class ChatGptRestRequestDto(
	val model: String,
	val messages: List<ChatMessageDto?>,
) {
	companion object {
		@JvmStatic
		fun from(
			model: String,
			chatMessages: List<ChatMessageDto?>,
		): ChatGptRestRequestDto =
			ChatGptRestRequestDto(
				model = model,
				messages = chatMessages,
			)
	}
}
