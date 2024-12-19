package com.comssa.persistence.question.dto.common.request

data class RequestChangeQuestionContentDto(
	val content: String,
) {
	companion object {
		@JvmStatic
		fun forTest(): RequestChangeQuestionContentDto =
			RequestChangeQuestionContentDto(
				content = "test",
			)
	}
}
