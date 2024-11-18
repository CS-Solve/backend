package com.comssa.core.comment.service

import com.comssa.core.authuser.AuthUserService
import com.comssa.core.comment.dto.RequestMakeCommentDto
import com.comssa.core.comment.dto.ResponseCommentDto
import com.comssa.persistence.comment.domain.Comment
import com.comssa.persistence.comment.service.CommentRepositoryService
import com.comssa.persistence.exception.NotLoginException
import com.comssa.persistence.member.service.MemberRepositoryService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService(
	private val authUserService: AuthUserService,
	private val commentRepositoryService: CommentRepositoryService,
	private val memberRepositoryService: MemberRepositoryService,
) {
	fun makeComment(
		requestMakeCommentDto: RequestMakeCommentDto,
		user: OAuth2User?,
	): ResponseCommentDto {
		val cognitoId = authUserService.getCognitoId(user) ?: throw NotLoginException()
		// JAVA랑 엮이면서 Null SAFE한 것을 알 수 없음
		val member = memberRepositoryService.findByCognitoId(cognitoId)
		val newComment =
			Comment.from(
				requestMakeCommentDto.content,
				member,
			)
		commentRepositoryService.save(newComment)
		return ResponseCommentDto.from(newComment, member)
	}

	fun getAllComments(user: OAuth2User?): List<ResponseCommentDto> {
		val cognitoId = authUserService.getCognitoId(user)
		val member = memberRepositoryService.findByCognitoIdFetchJoinComments(cognitoId)
		val comments = commentRepositoryService.findAll()
		return comments.map { comment -> ResponseCommentDto.from(comment, member) }
	}
}
