package com.comssa.core.chatbot.dto.response

data class ChatGptResponseDto(
	val id: String? = null,
	val `object`: String? = null,
	val created: Long = 0,
	val model: String? = null,
	val usage: Usage? = null,
	val choices: List<Choice>? = null,
) {
	val firstChoiceContent: String
		get() =
			this.choices
				?.get(0)
				?.message
				?.content ?: ""

	data class Choice(
		val message: Message? = null,
		val finishReason: String? = null,
		val index: Int = 0,
	)

	data class Message(
		val role: String? = null,
		val content: String? = null,
	)

	data class Usage(
		val promptTokens: Int = 0,
		val completionTokens: Int = 0,
		val totalTokens: Int = 0,
	)
}
