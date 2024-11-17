package com.comssa.api.question.major.admin.service.implement

import com.comssa.core.chatbot.dto.request.ChatContentDto
import com.comssa.core.chatbot.dto.request.ChatContentDto.Companion.from
import com.comssa.persistence.chatbot.ChatContentType
import com.comssa.persistence.question.common.domain.Question
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion
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
