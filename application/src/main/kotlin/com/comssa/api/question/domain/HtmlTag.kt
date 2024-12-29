package com.comssa.api.question.domain

data class HtmlTag(
	val title: String,
	val questionSession: String,
	val description: String,
) {
	companion object {
		@JvmStatic
		fun from(
			title: String,
			questionSession: String,
			description: String,
		): HtmlTag =
			HtmlTag(
				title = title,
				questionSession = questionSession,
				description = description,
			)
	}
}
