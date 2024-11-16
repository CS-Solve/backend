package com.comssa.core.chatbot.domain

enum class ChatRole(
	val lower: String,
) {
	SYSTEM("system"),
	USER("user"),
	ASSISTANT("assistant"),
}
