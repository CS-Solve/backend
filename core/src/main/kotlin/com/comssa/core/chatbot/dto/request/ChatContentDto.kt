package com.comssa.core.chatbot.dto.request

import com.comssa.core.chatbot.domain.ChatContentType

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
