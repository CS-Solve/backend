package com.comssa.core.chatbot.service.implement

import com.comssa.persistence.chatbot.ChatRole
import com.comssa.persistence.chatbot.dto.request.ChatContentDto
import com.comssa.persistence.chatbot.dto.request.ChatGptMessageDto
import com.comssa.persistence.chatbot.dto.request.ChatRequestDto
import com.comssa.persistence.chatbot.dto.response.ChatGptFileUploadResponseDto
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class ChatManageService(
	private val chatCacheService: ChatCacheService,
	private val chatGptService: ChatGptService,
) {
	fun talkForChat(
		userId: String,
		chatRequestDto: ChatRequestDto,
	): String {
		if (chatCacheService.getUsedChance(userId) >= MAX_CHAT_CHANCE) {
			return NO_MORE_CHANCE
		}

		val chatMessages = beforeRespond(userId, chatRequestDto)

		val answer = chatGptService.sendChatMessage(chatMessages)
		afterRespond(userId, answer)
		return answer
	}

	/**
	 * User가 가지고 있는 이전 대화 기록을 가져온다.
	 */
	private fun beforeRespond(
		userId: String,
		chatRequestDto: ChatRequestDto,
	): List<ChatGptMessageDto> {
		val chatMessageFromUser = ChatGptMessageDto.from(chatRequestDto.prompt, ChatRole.USER)
		return chatCacheService.saveChatMessage(userId, chatMessageFromUser, MAX_MESSAGES_SIZE)
	}

	/**
	 * 챗봇에게 받은 답변 또한 이전 대화 기록에 넣는다.
	 */
	private fun afterRespond(
		userId: String,
		answer: String,
	) {
		val chatMessageFromAssistant = ChatGptMessageDto.from(answer, ChatRole.ASSISTANT)
		chatCacheService.saveChatMessage(userId, chatMessageFromAssistant, MAX_MESSAGES_SIZE)
		chatCacheService.increaseUsedChance(userId)
	}

	fun talkForBatch(
		chatMessages: List<ChatContentDto>,
		command: String,
	): ChatGptFileUploadResponseDto {
		val commandMessage = ChatGptMessageDto.from(command, ChatRole.SYSTEM)
		val chatMessage = ChatGptMessageDto.from(chatMessages, ChatRole.USER)
		return chatGptService.sendFileUploadMessage(listOf(commandMessage, chatMessage))
	}

	fun talkForGradeDescriptiveQuestion(
		command: String,
		questionContent: String,
		userDescriptiveAnswer: String,
	): SseEmitter {
		val commandMessage = ChatGptMessageDto.from(command, ChatRole.SYSTEM)
		val questionContentMessage = ChatGptMessageDto.from(questionContent, ChatRole.ASSISTANT)
		val chatMessage = ChatGptMessageDto.from(userDescriptiveAnswer, ChatRole.USER)
		return chatGptService.getMessageBySse(listOf(commandMessage, questionContentMessage, chatMessage))
	}

	companion object {
		const val NO_MORE_CHANCE = "채팅 기회를 모두 소모하셨습니다. 1시간마다 초기화됩니다."
		const val ANSWER_NULL = "답변을 생성할 수 없습니다."
		const val MAX_CHAT_CHANCE: Int = 30
		const val MAX_MESSAGES_SIZE: Int = 15
	}
}
