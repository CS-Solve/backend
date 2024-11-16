package com.core.computerscience.chatbot.dto.request

import com.core.computerscience.chatbot.domain.ChatContentType

data class ChatContentDto(
	val type: String,
	val text: String,
) {
	companion object {
		@JvmStatic
		fun from(
			type: ChatContentType,
			text: String,
		): com.core.computerscience.chatbot.dto.request.ChatContentDto =
			com.core.computerscience.chatbot.dto.request.ChatContentDto(
				type = type.lower,
				text = text,
			)
	}
}
