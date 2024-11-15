package com.study.computerscience.chatbot.dto.response

data class ChatGptBatchResponseDto(
	val id: String? = null,
	val `object`: String? = null,
	val endpoint: String? = null,
	val errors: String? = null,
	val inputFileId: String? = null,
	val completionWindow: String? = null,
	val status: String? = null,
	val outputFileId: String? = null,
	val errorFileId: String? = null,
	val createdAt: Long? = null,
	val inProgressAt: Long? = null,
	val expiresAt: Long? = null,
	val finalizingAt: Long? = null,
	val completedAt: Long? = null,
	val failedAt: Long? = null,
	val expiredAt: Long? = null,
	val cancellingAt: Long? = null,
	val cancelledAt: Long? = null,
	val requestCounts: RequestCounts? = null,
	val metadata: Any? = null,
) {
	data class RequestCounts(
		val total: Int = 0,
		val completed: Int = 0,
		val failed: Int = 0,
	)
}
