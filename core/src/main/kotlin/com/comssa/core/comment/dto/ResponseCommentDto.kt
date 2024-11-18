package com.comssa.core.comment.dto

import com.comssa.persistence.comment.domain.Comment

data class ResponseCommentDto(
	val content: String,
	val ifAuthor: Boolean,
) {
	companion object {
		fun from(
			comment: Comment,
			ifAuthor: Boolean,
		): ResponseCommentDto =
			ResponseCommentDto(
				content = comment.content,
				ifAuthor = ifAuthor,
			)
	}
}
