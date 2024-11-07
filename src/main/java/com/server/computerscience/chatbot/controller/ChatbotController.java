package com.server.computerscience.chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.service.ChatbotService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = {"챗봇"})
@RequiredArgsConstructor
public class ChatbotController {
	private final ChatbotService chatbotService;

	@PostMapping("/chat/text")
	public ResponseEntity<String> chat(
		@RequestBody ChatBotRequestDto chatBotRequestDto
	) {
		return ResponseEntity.ok(chatbotService.chat(chatBotRequestDto));
	}
}
