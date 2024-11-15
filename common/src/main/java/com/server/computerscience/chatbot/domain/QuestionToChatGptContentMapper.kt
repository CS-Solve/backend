package com.server.computerscience.chatbot.domain

import com.server.computerscience.chatbot.dto.request.ChatContentDto
import com.server.computerscience.chatbot.dto.request.ChatContentDto.Companion.from
import org.springframework.stereotype.Service

@Service
class QuestionToChatGptContentMapper {
	fun getContentsFromQuestion(
		questions: List<com.server.computerscience.question.common.domain.Question>,
	): List<ChatContentDto> {
		val chatContentDtos: MutableList<ChatContentDto> = ArrayList()
		for (question in questions) {
			val chatContentDto = createChatContentDtoForChatGpt(question)
			if (chatContentDto != null) {
				chatContentDtos.add(chatContentDto)
			}
		}
		return chatContentDtos
	}

	private fun createChatContentDtoForChatGpt(
		question: com.server.computerscience.question.common.domain.Question,
	): ChatContentDto? {
		if (question is com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion) {
			return from(
				ChatContentType.TEXT,
				question.toString(),
			)
		} else if (question is com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion) {
			return from(
				ChatContentType.TEXT,
				question.toString(),
			)
		}
		return null // 지원되지 않는 Question 타입인 경우 null 반환
	}
}
