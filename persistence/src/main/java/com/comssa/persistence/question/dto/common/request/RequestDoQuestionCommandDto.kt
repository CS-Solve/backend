package com.comssa.persistence.question.dto.common.request

import com.comssa.persistence.question.domain.common.QuestionCategory

data class RequestDoQuestionCommandDto(
	val command: String,
	val multipleChoice: Boolean,
	val questionCategories: List<QuestionCategory>,
)
