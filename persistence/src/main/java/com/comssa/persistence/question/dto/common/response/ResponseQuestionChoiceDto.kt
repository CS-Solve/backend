package com.comssa.persistence.question.dto.common.response

import com.comssa.persistence.question.domain.common.QuestionChoice

data class ResponseQuestionChoiceDto(
	val id: Long,
	val content: String,
	val selectedCount: Int,
	val answerStatus: Boolean,
) {
	companion object {
		@JvmStatic
		fun <T : QuestionChoice> from(questionChoice: T): ResponseQuestionChoiceDto =
			ResponseQuestionChoiceDto(
				id = questionChoice.id,
				content = questionChoice.content,
				selectedCount = questionChoice.selectedCount,
				answerStatus = questionChoice.isAnswerStatus,
			)
	}
}
