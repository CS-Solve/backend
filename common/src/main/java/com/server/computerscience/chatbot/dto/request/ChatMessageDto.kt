package com.server.computerscience.chatbot.dto.request

import com.server.computerscience.chatbot.domain.ChatContentType
import com.server.computerscience.chatbot.domain.ChatRole
import com.server.computerscience.chatbot.dto.request.ChatContentDto.Companion.from

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
				content = listOf(from(ChatContentType.TEXT, text)),
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
