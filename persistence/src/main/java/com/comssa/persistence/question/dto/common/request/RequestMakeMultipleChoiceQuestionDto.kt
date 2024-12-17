package com.comssa.persistence.question.dto.common.request

import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.common.QuestionCategory
import com.comssa.persistence.question.domain.common.QuestionChoice
import com.comssa.persistence.question.domain.common.QuestionLevel

class RequestMakeMultipleChoiceQuestionDto(
	val questionChoices: List<RequestMakeQuestionChoiceDto>,
	content: String,
	questionCategory: QuestionCategory,
	questionLevel: QuestionLevel,
	description: String,
) : RequestMakeQuestionDto(
		content = content,
		questionCategory = questionCategory,
		questionLevel = questionLevel,
		description = description,
	) {
	companion object {
		@JvmStatic
		fun <T : QuestionChoice> from(
			question: Question,
			questionChoices: List<T>,
		): RequestMakeMultipleChoiceQuestionDto {
			val mappedChoices =
				questionChoices.map { questionChoice ->
					RequestMakeQuestionChoiceDto.from(questionChoice)
				}
			return RequestMakeMultipleChoiceQuestionDto(
				questionChoices = mappedChoices,
				content = question.content,
				description = question.description,
				questionCategory = question.questionCategory,
				questionLevel = question.questionLevel,
			)
		}
	}
}
