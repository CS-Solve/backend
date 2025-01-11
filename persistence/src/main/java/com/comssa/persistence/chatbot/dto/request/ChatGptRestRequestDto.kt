package com.comssa.persistence.chatbot.dto.request

data class ChatGptRestRequestDto(
	val model: String,
	val messages: List<ChatGptMessageDto?>,
	val stream: Boolean,
) {
	companion object {
		@JvmStatic
		fun from(
			model: String,
			chatMessages: List<ChatGptMessageDto?>,
			stream: Boolean,
		): ChatGptRestRequestDto =
			ChatGptRestRequestDto(
				model = model,
				messages = chatMessages,
				stream = stream,
			)
	}
}
