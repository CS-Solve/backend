package com.comssa.persistence.question.dto.major.response

import com.comssa.persistence.question.domain.common.QuestionCategory
import com.comssa.persistence.question.domain.common.QuestionLevel

data class ResponseMajorQuestionClassCountDto(
	val questionCategory: QuestionCategory,
	val questionLevel: QuestionLevel,
	val count: Int,
)
