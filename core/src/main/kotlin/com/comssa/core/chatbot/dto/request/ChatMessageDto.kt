package com.comssa.core.chatbot.dto.request

import com.comssa.core.chatbot.domain.ChatContentType
import com.comssa.core.chatbot.domain.ChatRole

class ChatMessageDto(
	val role: String,
	val content: List<ChatContentDto?>,
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
		): ChatMessageDto =
			ChatMessageDto(
				role = role.lower,
				content = chatContents,
			)
	}
}
