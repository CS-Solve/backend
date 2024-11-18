package com.comssa.core.comment.dto

data class RequestMakeCommentDto(
	val content: String,
	val questionId: Long,
)
