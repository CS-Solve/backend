package com.comssa.core.comment.service

import com.comssa.core.authuser.AuthUserService
import com.comssa.core.comment.dto.RequestMakeCommentDto
import com.comssa.core.comment.dto.ResponseCommentDto
import com.comssa.persistence.comment.domain.Comment
import com.comssa.persistence.comment.service.CommentRepositoryService
import com.comssa.persistence.member.domain.Member
import com.comssa.persistence.member.service.MemberRepositoryService
import com.comssa.persistence.question.common.domain.Question
import com.comssa.persistence.question.common.service.QuestionRepositoryService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CommentService(
	private val authUserService: AuthUserService,
	private val commentRepositoryService: CommentRepositoryService,
	private val memberRepositoryService: MemberRepositoryService,
	private val questionRepositoryService: QuestionRepositoryService,
) {
	fun makeComment(
		requestMakeCommentDto: RequestMakeCommentDto,
		questionId: Long,
		user: OAuth2User?,
	): ResponseCommentDto {
		val (member, question) = pair(user, questionId)

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
		val (member, question) = pair(user, questionId)
		val comments = question.comments

		return comments.map { comment -> ResponseCommentDto.from(comment, member) }
	}

	private fun pair(
		user: OAuth2User?,
		questionId: Long,
	): Pair<Member?, Question> {
		val cognitoId = authUserService.getCognitoId(user)
		val member = memberRepositoryService.findByCognitoId(cognitoId)

		val question =
			questionRepositoryService.findById(questionId)
				?: throw NoSuchElementException("Question $questionId not found")
		return Pair(member, question)
	}
}