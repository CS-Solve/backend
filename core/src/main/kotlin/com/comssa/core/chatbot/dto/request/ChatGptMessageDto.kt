package com.comssa.core.chatbot.dto.request

import com.comssa.persistence.chatbot.ChatContentType
import com.comssa.persistence.chatbot.ChatRole

class ChatGptMessageDto(
	val role: String,
	val content: List<ChatContentDto?>,
) {
	companion object {
		@JvmStatic
		fun from(
			text: String,
			role: ChatRole,
		): ChatGptMessageDto =
			ChatGptMessageDto(
				role = role.lower,
				content =
					listOf(
						ChatContentDto.from(
							ChatContentType.TEXT,
							text,
						),
					),
			)

		@JvmStatic
		fun from(
			chatContents: List<ChatContentDto?>,
			role: ChatRole,
		): ChatGptMessageDto =
			ChatGptMessageDto(
				role = role.lower,
				content = chatContents,
			)
	}
}
