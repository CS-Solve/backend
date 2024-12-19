package com.comssa.persistence.question.dto.common.request

data class RequestChangeQuestionDescriptionDto(
	val description: String,
) {
	companion object {
		@JvmStatic
		fun forTest(): RequestChangeQuestionDescriptionDto =
			RequestChangeQuestionDescriptionDto(
				description = "test",
			)
	}
}
