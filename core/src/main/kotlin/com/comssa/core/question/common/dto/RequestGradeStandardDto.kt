package com.comssa.core.question.common.dto

import com.fasterxml.jackson.annotation.JsonCreator

class RequestGradeStandardDto
	@JsonCreator
	constructor(
		val gradeStandard: String,
	)
