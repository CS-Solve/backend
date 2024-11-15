package com.server.computerscience.chatbot.controller

import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto
import com.server.computerscience.chatbot.dto.response.ChatBotResponseDto
import com.server.computerscience.chatbot.dto.response.ChatBotResponseDto.Companion.from
import com.server.computerscience.chatbot.service.ChatbotService
import io.swagger.annotations.Api
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["챗봇"])
@RequiredArgsConstructor
class ChatbotController(
	private val chatbotService: ChatbotService,
) {
	@PostMapping("/chat/text")
	fun chat(
		@RequestBody chatBotRequestDto: ChatBotRequestDto,
		@AuthenticationPrincipal user: OAuth2User?,
	): ResponseEntity<ChatBotResponseDto> =
		ResponseEntity.ok(from(chatbotService.talkToAssistant(chatBotRequestDto, user)))
}