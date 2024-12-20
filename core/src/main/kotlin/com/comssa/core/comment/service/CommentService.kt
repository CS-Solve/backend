package com.comssa.core.comment.service

import com.comssa.core.authuser.service.AuthUserService
import com.comssa.persistence.comment.domain.Comment
import com.comssa.persistence.comment.dto.RequestMakeCommentDto
import com.comssa.persistence.comment.dto.ResponseCommentDto
import com.comssa.persistence.comment.service.CommentRepositoryService
import com.comssa.persistence.exception.NotLoginException
import com.comssa.persistence.member.service.MemberRepositoryService
import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.service.QuestionRepositoryService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CommentService(
	private val authUserService: AuthUserService,
	private val commentRepositoryService: CommentRepositoryService,
	private val memberRepositoryService: MemberRepositoryService,
	private val questionRepositoryService: QuestionRepositoryService<Question>,
) {
	fun makeComment(
		requestMakeCommentDto: RequestMakeCommentDto,
		questionId: Long,
		user: OAuth2User?,
	): ResponseCommentDto {
		val member =
			authUserService.getCognitoId(user)?.let { memberRepositoryService.findByCognitoId(it) }
				?: throw NotLoginException()
		val question =
			questionRepositoryService.findById(questionId) ?: throw NoSuchElementException()
		val newComment =
			Comment.from(
				requestMakeCommentDto.content,
				member,
				question,
			)
		commentRepositoryService.save(newComment)
		return ResponseCommentDto.from(newComment, member)
	}

	fun getComments(
		questionId: Long,
		user: OAuth2User?,
	): List<ResponseCommentDto> {
		/*
		cognitoId로 유저 조회
		 */
		val member =
			authUserService.getCognitoId(user)?.let { memberRepositoryService.findByCognitoId(it) }

		/**
		 * 없는 문제라면 에러 발생
		 */
		val question =
			questionRepositoryService.findByIdFetchCommentsOrderByCreatedAtDesc(questionId)
				?: throw NoSuchElementException("Question $questionId not found")

		return question.comments.map { comment -> ResponseCommentDto.from(comment, member) }
	}

	fun deleteComment(commentId: Long) {
		authUserService
		commentRepositoryService.deleteById(commentId)
	}
}
