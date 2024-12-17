package com.comssa.persistence.question.dto.common.request

import com.comssa.persistence.question.domain.common.QuestionChoice

class RequestMakeQuestionChoiceDto(
	val text: String,
	val answerStatus: Boolean,
) {
	companion object {
		fun from(questionChoice: QuestionChoice): RequestMakeQuestionChoiceDto =
			RequestMakeQuestionChoiceDto(
				text = questionChoice.content,
				answerStatus = questionChoice.isAnswerStatus,
			)
	}
}
