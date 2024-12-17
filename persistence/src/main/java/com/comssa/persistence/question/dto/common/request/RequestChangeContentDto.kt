package com.comssa.persistence.question.dto.common.request

data class RequestChangeContentDto(
	val content: String? = null,
) {
	companion object {
		@JvmStatic
		fun forTest(): RequestChangeContentDto =
			RequestChangeContentDto(
				content = "test",
			)
	}
}
