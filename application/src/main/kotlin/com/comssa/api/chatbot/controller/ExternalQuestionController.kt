package com.comssa.api.chatbot.controller

import com.comssa.api.chatbot.service.ExternalQuestionService
import com.comssa.core.chatbot.dto.response.ChatGptBatchResponseDto
import com.comssa.core.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.comssa.core.chatbot.service.implement.ChatGptService
import com.comssa.persistence.question.common.dto.request.RequestQuestionCommandDto
import io.swagger.annotations.Api
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@Api(tags = ["AI 문제 수정 - ADMIN"])
@RequestMapping("/admin")
@RequiredArgsConstructor
class ExternalQuestionController(
	private val externalQuestionService: ExternalQuestionService,
	private val chatGptService: ChatGptService,
) {
	@PostMapping("/chat-gpt/file/question")
	fun updateQuestionToChatGpt(
		@RequestBody requestQuestionCommandDto: RequestQuestionCommandDto,
	): ResponseEntity<ChatGptFileUploadResponseDto> =
		ResponseEntity.ok(externalQuestionService.sendQuestionToExternal(requestQuestionCommandDto))

	@PostMapping("/chat-gpt/batch")
	fun createBatchToChatGpt(
		@RequestBody requestBatchDto: com.comssa.core.chatbot.dto.request.ChatGptBatchRequestDto,
	): ResponseEntity<ChatGptBatchResponseDto> = ResponseEntity.ok(chatGptService.sendBatchMessage(requestBatchDto))
}
