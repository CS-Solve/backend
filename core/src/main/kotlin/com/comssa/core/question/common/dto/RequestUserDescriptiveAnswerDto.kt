package com.comssa.core.question.common.dto

import com.fasterxml.jackson.annotation.JsonCreator

class RequestUserDescriptiveAnswerDto
	@JsonCreator
	constructor(
		val content: String,
	)
