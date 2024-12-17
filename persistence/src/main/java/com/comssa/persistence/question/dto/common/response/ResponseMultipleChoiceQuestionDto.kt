package com.comssa.persistence.question.dto.common.response

import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion

open class ResponseMultipleChoiceQuestionDto(
	val questionChoices: List<ResponseQuestionChoiceDto>,
	question: Question,
) : ResponseQuestionDto(
		question = question,
		ifMultipleChoice = true,
	) {
	companion object {
		@JvmStatic
		fun forLicense(question: LicenseMultipleChoiceQuestion): ResponseMultipleChoiceQuestionDto =
			ResponseMultipleChoiceQuestionDto(
				question = question,
				questionChoices = question.questionChoices.map { ResponseQuestionChoiceDto.from(it) },
			)

		@JvmStatic
		fun forMajor(question: MajorMultipleChoiceQuestion): ResponseMultipleChoiceQuestionDto =
			ResponseMultipleChoiceQuestionDto(
				question = question,
				questionChoices = question.questionChoices.map { ResponseQuestionChoiceDto.from(it) },
			)
	}
}
