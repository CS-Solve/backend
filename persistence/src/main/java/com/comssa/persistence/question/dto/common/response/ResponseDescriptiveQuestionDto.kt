package com.comssa.persistence.question.dto.common.response

import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion

class ResponseDescriptiveQuestionDto(
	question: Question,
	val gradeStandard: String,
) : ResponseQuestionDto(
		question,
		ifMultipleChoice = false,
	) {
	companion object {
		fun forMajor(question: MajorDescriptiveQuestion): ResponseDescriptiveQuestionDto =
			ResponseDescriptiveQuestionDto(
				question = question,
				gradeStandard = question.gradeStandard,
			)
	}
}
