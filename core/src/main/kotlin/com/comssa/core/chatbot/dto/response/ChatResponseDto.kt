package com.comssa.core.chatbot.dto.response

class ChatResponseDto(
	val content: String? = null,
) {
	companion object {
		@JvmStatic
		fun from(content: String): ChatResponseDto =
			ChatResponseDto(
				content = content,
			)
	}
}
