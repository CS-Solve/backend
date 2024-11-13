package com.server.computerscience.chatbot.domain

enum class ChatRole(val lower: String) {
    SYSTEM("system"), USER("user"), ASSISTANT("assistant")
}
