package com.server.computerscience.chatbot.dto.response


data class ChatGptFileUploadResponseDto(
    val id: String? = null,
    val `object`: String? = null,
    val bytes: Int = 0,
    val createdAt: Long = 0,
    val filename: String? = null,
    val purpose: String? = null,
    val status: String? = null,
    val statusDetails: String? = null
)
