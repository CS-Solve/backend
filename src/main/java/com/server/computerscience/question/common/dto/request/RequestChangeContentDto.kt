package com.server.computerscience.question.common.dto.request


data class RequestChangeContentDto(
    val content: String? = null
) {
    companion object {
        @JvmStatic
        fun forTest(): RequestChangeContentDto {
            return RequestChangeContentDto(
                content = "test"
            )
        }
    }
}
