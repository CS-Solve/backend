package com.comssa.core.comment.dto

import com.comssa.persistence.comment.domain.Comment
import com.comssa.persistence.member.domain.Member

data class ResponseCommentDto(
	val content: String,
	val ifAuthor: Boolean,
	val memberId: String,
) {
	companion object {
		fun from(
			comment: Comment,
			member: Member?,
		): ResponseCommentDto =
			ResponseCommentDto(
				content = comment.content,
				memberId = comment.member.id.toString(),
				// member가 null이면 자동으로 false
				ifAuthor = member?.id == comment.member.id,
			)
	}
}
