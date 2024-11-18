package com.comssa.api.comment.controller

import com.comssa.core.comment.dto.RequestMakeCommentDto
import com.comssa.core.comment.dto.ResponseCommentDto
import com.comssa.core.comment.service.CommentService
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(("/question/comment"))
@Api(tags = ["댓글"])
class CommentController(
	private val commentService: CommentService,
) {
	@PostMapping
	fun addComment(
		@RequestBody requestMakeCommentDto: RequestMakeCommentDto,
		@AuthenticationPrincipal user: OAuth2User?,
	): ResponseEntity<ResponseCommentDto> = ResponseEntity.ok(commentService.makeComment(requestMakeCommentDto, user))

	@GetMapping
	fun getComments(
		@AuthenticationPrincipal user: OAuth2User?,
	): ResponseEntity<List<ResponseCommentDto>> =
		ResponseEntity.ok(
			commentService
				.getComments(user),
		)
}
