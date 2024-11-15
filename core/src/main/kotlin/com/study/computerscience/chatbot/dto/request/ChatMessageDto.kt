package com.study.computerscience.chatbot.dto.request

import com.study.computerscience.chatbot.domain.ChatContentType
import com.study.computerscience.chatbot.domain.ChatRole

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
				content = listOf(ChatContentDto.from(ChatContentType.TEXT, text)),
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
