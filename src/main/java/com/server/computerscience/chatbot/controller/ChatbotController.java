package com.server.computerscience.chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.dto.response.ChatBotResponseDto;
import com.server.computerscience.chatbot.service.ChatbotService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = {"챗봇"})
@RequiredArgsConstructor
public class ChatbotController {
	private final ChatbotService chatbotService;

	@PostMapping("/chat/text")
	public ResponseEntity<ChatBotResponseDto> chat(
		@RequestBody ChatBotRequestDto chatBotRequestDto,
		@AuthenticationPrincipal OAuth2User user
	) {
		return ResponseEntity.ok(ChatBotResponseDto.from(chatbotService.chat(chatBotRequestDto, user)));
	}
}
