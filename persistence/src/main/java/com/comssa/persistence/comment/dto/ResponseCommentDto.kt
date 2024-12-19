package com.comssa.persistence.comment.dto

import com.comssa.persistence.comment.domain.Comment
import com.comssa.persistence.member.domain.Member

data class ResponseCommentDto(
	val content: String,
	val ifAuthor: Boolean,
	val memberId: String,
	val commentId: String,
	val createdAt: String,
) {
	companion object {
		fun from(
			comment: Comment,
			loginMember: Member?,
		): ResponseCommentDto =
			ResponseCommentDto(
				content = comment.content,
				memberId = comment.member.cognitoId,
				commentId = comment.id.toString(),
				// member가 null이면 자동으로 false
				ifAuthor = loginMember?.id == comment.member.id,
				createdAt = comment.createdAt.toString(),
			)
	}
}
