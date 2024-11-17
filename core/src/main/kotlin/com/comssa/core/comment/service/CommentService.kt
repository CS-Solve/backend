package com.comssa.core.comment.service

import com.comssa.core.authuser.AuthUserService
import com.comssa.core.comment.dto.RequestMakeCommentDto
import com.comssa.persistence.comment.domain.Comment
import com.comssa.persistence.comment.service.CommentRepositoryService
import com.comssa.persistence.exception.NotLoginException
import com.comssa.persistence.member.service.MemberRepositoryService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CommentService(
	private val authUserService: AuthUserService,
	private val commentRepositoryService: CommentRepositoryService,
	private val memberRepositoryService: MemberRepositoryService,
) {
	fun makeComment(
		requestMakeCommentDto: RequestMakeCommentDto,
		user: OAuth2User?,
	): Comment {
		if (user == null) {
			throw NotLoginException()
		}
		val cognitoId = authUserService.getCognitoId(user)
		val member = memberRepositoryService.findByCognitoId(cognitoId)
		val newComment =
			Comment.from(
				requestMakeCommentDto.content,
				member,
			)
		return commentRepositoryService.save(newComment)
	}
}
