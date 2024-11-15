package com.study.computerscience.chatbot.dto.request

import com.study.computerscience.chatbot.domain.ChatContentType

data class ChatContentDto(
	val type: String,
	val text: String,
) {
	companion object {
		@JvmStatic
		fun from(
			type: ChatContentType,
			text: String,
		): com.study.computerscience.chatbot.dto.request.ChatContentDto =
			com.study.computerscience.chatbot.dto.request.ChatContentDto(
				type = type.lower,
				text = text,
			)
	}
}
