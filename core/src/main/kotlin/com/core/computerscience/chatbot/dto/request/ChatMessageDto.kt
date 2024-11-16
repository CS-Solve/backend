package com.core.computerscience.chatbot.dto.request

import com.core.computerscience.chatbot.domain.ChatContentType
import com.core.computerscience.chatbot.domain.ChatRole

class ChatMessageDto(
	val role: String,
	val content: List<com.core.computerscience.chatbot.dto.request.ChatContentDto?>,
) {
	companion object {
		@JvmStatic
		fun from(
			text: String,
			role: ChatRole,
		): ChatMessageDto =
			ChatMessageDto(
				role = role.lower,
				content =
					listOf(
						com.core.computerscience.chatbot.dto.request.ChatContentDto.from(
							ChatContentType.TEXT,
							text,
						),
					),
			)

		@JvmStatic
		fun from(
			chatContents: List<com.core.computerscience.chatbot.dto.request.ChatContentDto?>,
			role: ChatRole,
		): ChatMessageDto =
			ChatMessageDto(
				role = role.lower,
				content = chatContents,
			)
	}
}
