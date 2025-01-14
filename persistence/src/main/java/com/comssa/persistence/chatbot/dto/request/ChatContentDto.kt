package com.comssa.persistence.chatbot.dto.request

import com.comssa.persistence.chatbot.ChatContentType

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
