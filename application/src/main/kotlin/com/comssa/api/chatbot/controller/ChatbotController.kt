package com.comssa.api.chatbot.controller

import com.comssa.core.chatbot.service.ChatbotService
import com.comssa.core.chatbot.service.implement.ChatManageService
import com.comssa.persistence.chatbot.dto.request.ChatRequestDto
import com.comssa.persistence.chatbot.dto.response.ChatResponseDto
import com.comssa.persistence.chatbot.dto.response.ChatResponseDto.Companion.from
import io.swagger.annotations.Api
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@Api(tags = ["챗봇"])
class ChatbotController(
	private val chatbotService: ChatbotService,
	private val chatManageService: ChatManageService,
) {
	@PostMapping("/chat/text")
	fun chat(
		@RequestBody chatRequestDto: ChatRequestDto,
		@AuthenticationPrincipal user: OAuth2User?,
	): ResponseEntity<ChatResponseDto> = ResponseEntity.ok(from(chatbotService.talkToAssistant(chatRequestDto, user)))

	@GetMapping(
		value = ["/chat/subscribe/{key}"],
		produces = [MediaType.TEXT_EVENT_STREAM_VALUE],
	)
	fun getChatMessageBySse(
		@PathVariable key: String,
	): ResponseEntity<SseEmitter> = ResponseEntity.ok(chatManageService.getChatMessageBySse(key))
}
