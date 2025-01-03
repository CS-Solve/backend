package com.comssa.api.chatbot.service

import com.comssa.persistence.chatbot.ChatContentType
import com.comssa.persistence.chatbot.dto.request.ChatContentDto
import com.comssa.persistence.chatbot.dto.request.ChatContentDto.Companion.from
import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion
import org.springframework.stereotype.Service

@Service
class QuestionToChatGptContentMapper {
	fun getContentsFromQuestion(questions: List<Question>): List<ChatContentDto> {
		val chatContentDtos: MutableList<ChatContentDto> =
			ArrayList()
		for (question in questions) {
			val chatContentDto = createChatContentDtoForChatGpt(question)
			if (chatContentDto != null) {
				chatContentDtos.add(chatContentDto)
			}
		}
		return chatContentDtos
	}

	private fun createChatContentDtoForChatGpt(question: Question): ChatContentDto? {
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
