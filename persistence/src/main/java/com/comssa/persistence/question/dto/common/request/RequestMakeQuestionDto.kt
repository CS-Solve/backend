package com.comssa.persistence.question.dto.common.request

import com.comssa.persistence.question.domain.common.QuestionCategory
import com.comssa.persistence.question.domain.common.QuestionLevel

abstract class RequestMakeQuestionDto(
	val content: String = "",
	val questionCategory: QuestionCategory = QuestionCategory.OOP,
	val questionLevel: QuestionLevel = QuestionLevel.LOW,
	val description: String = "",
)
