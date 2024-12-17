package com.comssa.persistence.question.dto.common.response

import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.common.QuestionCategory
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.experimental.SuperBuilder

@NoArgsConstructor
@Getter
@SuperBuilder
class ResponseClassifiedQuestionDto(
	val questionCategory: QuestionCategory,
	val responseQuestions: List<ResponseQuestionDto>,
) {
	companion object {
		@JvmStatic
		fun <T : Question> from(
			questionCategory: QuestionCategory,
			questions: List<T>,
		): ResponseClassifiedQuestionDto =
			ResponseClassifiedQuestionDto(
				questionCategory = questionCategory,
				responseQuestions = questions.map { ResponseQuestionDto.from(it) },
			)
	}
}
