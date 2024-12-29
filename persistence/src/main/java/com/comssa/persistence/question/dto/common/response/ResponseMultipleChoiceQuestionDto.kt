package com.comssa.persistence.question.dto.common.response

import com.comssa.persistence.question.domain.common.ChoiceBehavior
import com.comssa.persistence.question.domain.common.Question

open class ResponseMultipleChoiceQuestionDto(
	val questionChoices: List<ResponseQuestionChoiceDto>,
	question: Question,
) : ResponseQuestionDto(
		question = question,
		ifMultipleChoice = true,
	) {
	companion object {
		@JvmStatic
		fun <Q> from(question: Q): ResponseMultipleChoiceQuestionDto
		 where Q : Question, Q : ChoiceBehavior =
			ResponseMultipleChoiceQuestionDto(
				question = question,
				questionChoices = question.questionChoices.map { ResponseQuestionChoiceDto.from(it) },
			)
	}
}
