package com.server.computerscience.chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.computerscience.chatbot.dto.request.ChatGptBatchRequestDto;
import com.server.computerscience.chatbot.dto.response.ChatGptBatchResponseDto;
import com.server.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto;
import com.server.computerscience.chatbot.service.implement.ChatGptService;
import com.server.computerscience.question.common.dto.request.RequestQuestionCommandDto;
import com.server.computerscience.question.common.service.ExternalQuestionService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Controller
@Api(tags = {"AI 문제 수정 - ADMIN"})
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ExternalQuestionController {

	private final ExternalQuestionService externalQuestionService;

	private final ChatGptService chatGptService;

	@PostMapping("/chat-gpt/file/question")
	public ResponseEntity<ChatGptFileUploadResponseDto> updateQuestionToChatGpt(
		@RequestBody RequestQuestionCommandDto requestQuestionCommandDto
	) {
		return ResponseEntity.ok(externalQuestionService.sendQuestionToExternal(requestQuestionCommandDto));
	}
	
	@PostMapping("/chat-gpt/batch")
	public ResponseEntity<ChatGptBatchResponseDto> createBatchToChatGpt(
		@RequestBody ChatGptBatchRequestDto requestBatchDto
	) {
		return ResponseEntity.ok(chatGptService.sendBatchMessage(requestBatchDto));
	}
}
