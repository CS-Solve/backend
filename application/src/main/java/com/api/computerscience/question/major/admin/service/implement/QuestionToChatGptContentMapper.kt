package com.api.computerscience.question.major.admin.service.implement

import com.core.computerscience.chatbot.domain.ChatContentType
import com.core.computerscience.chatbot.dto.request.ChatContentDto.Companion.from
import com.persistence.computerscience.question.common.domain.Question
import com.persistence.computerscience.question.license.domain.LicenseMultipleChoiceQuestion
import com.persistence.computerscience.question.major.domain.common.MajorMultipleChoiceQuestion
import org.springframework.stereotype.Service

@Service
class QuestionToChatGptContentMapper {
	fun getContentsFromQuestion(
		questions: List<Question>,
	): List<com.core.computerscience.chatbot.dto.request.ChatContentDto> {
		val chatContentDtos: MutableList<com.core.computerscience.chatbot.dto.request.ChatContentDto> =
			ArrayList()
		for (question in questions) {
			val chatContentDto = createChatContentDtoForChatGpt(question)
			if (chatContentDto != null) {
				chatContentDtos.add(chatContentDto)
			}
		}
		return chatContentDtos
	}

	private fun createChatContentDtoForChatGpt(
		question: Question,
	): com.core.computerscience.chatbot.dto.request.ChatContentDto? {
		if (question is LicenseMultipleChoiceQuestion) {
			return from(
				ChatContentType.TEXT,
				question.toString(),
			)
		} else if (question is MajorMultipleChoiceQuestion) {
			return from(
				ChatContentType.TEXT,
				question.toString(),
			)
		}
		return null // 지원되지 않는 Question 타입인 경우 null 반환
	}
}
