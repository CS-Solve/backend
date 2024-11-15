package com.study.computerscience.chatbot.dto.response

class ChatBotResponseDto(
	val content: String? = null,
) {
	companion object {
		@JvmStatic
		fun from(content: String): ChatBotResponseDto =
			ChatBotResponseDto(
				content = content,
			)
	}
}
