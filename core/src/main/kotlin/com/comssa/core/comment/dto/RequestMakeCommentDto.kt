package com.comssa.core.comment.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class RequestMakeCommentDto
	@JsonCreator
	constructor(
		val content: String,
	)
