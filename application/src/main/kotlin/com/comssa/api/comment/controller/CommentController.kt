package com.comssa.api.comment.controller

import com.comssa.api.login.aspect.AddLoginStatusAttributeToView
import com.comssa.core.comment.dto.RequestMakeCommentDto
import com.comssa.core.comment.dto.ResponseCommentDto
import com.comssa.core.comment.service.CommentService
import com.comssa.core.question.common.service.QuestionGetService
import com.comssa.persistence.question.common.dto.response.ResponseQuestionDto
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(("/question"))
@Api(tags = ["댓글"])
class CommentController(
	private val commentService: CommentService,
	private val questionGetService: QuestionGetService,
) {
	@ResponseBody
	@PostMapping("/{questionId}/comment")
	fun addComment(
		@PathVariable("questionId") questionId: Long,
		@RequestBody requestMakeCommentDto: RequestMakeCommentDto,
		@AuthenticationPrincipal user: OAuth2User?,
	): ResponseEntity<ResponseCommentDto> =
		ResponseEntity.ok(commentService.makeComment(requestMakeCommentDto, questionId, user))

	@DeleteMapping("/{questionId}/comment/{commentId}")
	@GetMapping("/{questionId}/comment")
	@AddLoginStatusAttributeToView
	fun getComments(
		model: Model,
		@PathVariable("questionId") questionId: Long,
		@AuthenticationPrincipal user: OAuth2User?,
	): String {
		val comments: List<ResponseCommentDto> = commentService.getComments(questionId, user)
		model.addAttribute("multipleChoice", true)
		model.addAttribute("comments", comments)
		val question = questionGetService.getQuestionById(questionId)
		model.addAttribute(
			"question",
			ResponseQuestionDto.forUser(question),
		)
		return "commentModal"
	}
}
