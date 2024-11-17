package com.comssa.persistence.chatbot

enum class ChatRole(
	val lower: String,
) {
	SYSTEM("system"),
	USER("user"),
	ASSISTANT("assistant"),
}
