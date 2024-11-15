package com.server.computerscience.chatbot.dto.request

import com.server.computerscience.chatbot.domain.ChatContentType

data class ChatContentDto(
	val type: String,
	val text: String,
) {
	companion object {
		@JvmStatic
		fun from(
			type: ChatContentType,
			text: String,
		): ChatContentDto =
			ChatContentDto(
				type = type.lower,
				text = text,
			)
	}
}
