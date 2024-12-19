package com.comssa.persistence.question.dto.license.request

import com.comssa.persistence.question.domain.license.LicenseCategory
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto

data class RequestMakeLicenseMultipleChoiceQuestionDto(
	val questions: List<RequestMakeMultipleChoiceQuestionDto>,
	val licenseSession: String,
	val licenseCategory: LicenseCategory,
) {
	companion object {
		@JvmStatic
		fun forTest(
			licenseSession: String,
			licenseCategory: LicenseCategory,
			questions: List<LicenseMultipleChoiceQuestion>,
		): RequestMakeLicenseMultipleChoiceQuestionDto =
			RequestMakeLicenseMultipleChoiceQuestionDto(
				questions.map { q -> RequestMakeMultipleChoiceQuestionDto.from(q, q.questionChoices) },
				licenseSession,
				licenseCategory,
			)
	}
}
