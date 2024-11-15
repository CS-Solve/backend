package com.server.computerscience.chatbot.controller

import com.server.computerscience.chatbot.dto.request.ChatGptBatchRequestDto
import com.server.computerscience.chatbot.dto.response.ChatGptBatchResponseDto
import com.server.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.server.computerscience.chatbot.service.implement.ChatGptService
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
	private val externalQuestionService: com.server.computerscience.question.common.service.ExternalQuestionService,
	private val chatGptService: ChatGptService,
) {
	@PostMapping("/chat-gpt/file/question")
	fun updateQuestionToChatGpt(
		@RequestBody requestQuestionCommandDto:
			com.server.computerscience.question.common.dto.request.RequestQuestionCommandDto,
	): ResponseEntity<ChatGptFileUploadResponseDto> =
		ResponseEntity.ok(externalQuestionService.sendQuestionToExternal(requestQuestionCommandDto))

	@PostMapping("/chat-gpt/batch")
	fun createBatchToChatGpt(
		@RequestBody requestBatchDto: ChatGptBatchRequestDto,
	): ResponseEntity<ChatGptBatchResponseDto> = ResponseEntity.ok(chatGptService.sendBatchMessage(requestBatchDto))
}
