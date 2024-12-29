package com.comssa.persistence.question.dto.common.response

import com.comssa.persistence.question.domain.common.ChoiceBehavior
import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.common.QuestionCategory
import com.comssa.persistence.question.domain.common.QuestionLevel
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion

abstract class ResponseQuestionDto(
	question: Question,
	val ifMultipleChoice: Boolean,
) {
	val id: Long = question.id
	val content: String = question.content
	val description: String = question.description
	val imageUrl: String? = question.imageUrl
	val questionCategory: QuestionCategory = question.questionCategory
	val questionLevel: QuestionLevel = question.questionLevel
	val ifApproved: Boolean = question.ifApproved

	companion object {
		@JvmStatic
		fun <R : ResponseQuestionDto> from(question: Question): R =
			when (question) {
				is ChoiceBehavior -> ResponseMultipleChoiceQuestionDto.from(question) as R
				is MajorDescriptiveQuestion -> ResponseDescriptiveQuestionDto.forMajor(question) as R
				else -> throw IllegalArgumentException("Unsupported question type: ${question.javaClass.name}")
			}
	}
}
