package com.comssa.api.question.domain

enum class QuestionType(
	val urlPath: String,
) {
	MULTIPLE_CHOICE("multiple"),
	DESCRIPTIVE("descriptive"),
}
