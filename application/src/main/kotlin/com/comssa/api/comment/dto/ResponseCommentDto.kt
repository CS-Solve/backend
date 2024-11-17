package com.comssa.api.comment.dto

import com.comssa.persistence.comment.domain.Comment

data class ResponseCommentDto(
	val content: String,
	val memberId: Long,
) {
	companion object {
		fun from(comment: Comment): ResponseCommentDto =
			ResponseCommentDto(
				content = comment.content,
				memberId = comment.member.id,
			)
	}
}
