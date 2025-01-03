package com.comssa.core.comment.service

import com.comssa.core.authuser.service.AuthUserService
import com.comssa.persistence.comment.domain.Comment
import com.comssa.persistence.comment.dto.RequestMakeCommentDto
import com.comssa.persistence.comment.dto.ResponseCommentDto
import com.comssa.persistence.comment.repository.CommentRepository
import com.comssa.persistence.exception.NotLoginException
import com.comssa.persistence.member.repository.MemberRepository
import com.comssa.persistence.question.repository.jpa.QuestionRepository
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CommentService(
	private val authUserService: AuthUserService,
	private val commentRepository: CommentRepository,
	private val memberRepository: MemberRepository,
	private val questionRepository: QuestionRepository,
) {
	fun makeComment(
		requestMakeCommentDto: RequestMakeCommentDto,
		questionId: Long,
		user: OAuth2User?,
	): ResponseCommentDto {
		val member =
			authUserService
				.getCognitoId(user)
				?.let { memberRepository.findByCognitoId(it).orElseThrow { NotLoginException() } }
				?: throw NotLoginException()
		val question =
			questionRepository.findById(questionId).orElseThrow { NoSuchElementException() }
				?: throw NoSuchElementException()
		val newComment =
			Comment.from(
				requestMakeCommentDto.content,
				member,
				question,
			)
		commentRepository.save(newComment)
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
			authUserService
				.getCognitoId(user)
				?.let { memberRepository.findByCognitoId(it).orElseThrow { NotLoginException() } }

		/**
		 * 없는 문제라면 에러 발생
		 */
		val question =
			questionRepository.findByIdFetchCommentsOrderByCreatedAtDesc(questionId)
				?: throw NoSuchElementException("Question $questionId not found")

		return question.get().comments.map { comment -> ResponseCommentDto.from(comment, member) }
	}

	fun deleteComment(commentId: Long) {
		authUserService
		commentRepository.deleteById(commentId)
	}
}
