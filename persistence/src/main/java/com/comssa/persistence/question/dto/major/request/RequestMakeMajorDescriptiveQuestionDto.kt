package com.comssa.persistence.question.dto.major.request

import com.comssa.persistence.question.dto.common.request.RequestMakeQuestionDto

data class RequestMakeMajorDescriptiveQuestionDto(
	val gradeStandard: String,
) : RequestMakeQuestionDto()
