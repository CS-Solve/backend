package com.server.computerscience.chatbot.service.implement

import com.server.computerscience.chatbot.domain.ChatRole
import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto
import com.server.computerscience.chatbot.dto.request.ChatContentDto
import com.server.computerscience.chatbot.dto.request.ChatMessageDto
import com.server.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto
import org.springframework.stereotype.Service

@Service
class ChatManageService(
    private val chatCacheService: ChatCacheService,
    private val chatGptService: ChatGptService,
) {
    private val NO_MORE_CHANCE = "채팅 기회를 모두 소모하셨습니다. 1시간마다 초기화됩니다."
    private val ANSWER_NULL = "답변을 생성할 수 없습니다."

    fun talkForChat(userId: String, chatBotRequestDto: ChatBotRequestDto): String {
        if (chatCacheService.getUsedChance(userId) >= MAX_CHAT_CHANCE) {
            return NO_MORE_CHANCE;
        }
        /**
         * User가 가지고 있는 이전 대화 기록을 가져온다.
         */
        val chatMessages = beforeRespond(userId, chatBotRequestDto)

        /**
         * 챗봇에게 받은 답변 또한 이전 대화 기록에 넣는다.
         */
        val answer = chatGptService.sendChatMessage(chatMessages)
        afterRespond(userId, answer)
        return answer ?: ANSWER_NULL
    }

    private fun beforeRespond(userId: String, chatBotRequestDto: ChatBotRequestDto): List<ChatMessageDto> {
        val chatMessageFromUser = ChatMessageDto.from(chatBotRequestDto.prompt, ChatRole.USER)
        return chatCacheService.saveChatMessage(userId, chatMessageFromUser, MAX_MESSAGES_SIZE)
    }

    private fun afterRespond(userId: String, answer: String) {
        val chatMessageFromAssistant = ChatMessageDto.from(answer, ChatRole.USER)
        chatCacheService.saveChatMessage(userId, chatMessageFromAssistant, MAX_MESSAGES_SIZE)
        chatCacheService.increaseUsedChance(userId)
    }

    fun talkForBatch(chatMessages: List<ChatContentDto>, command: String): ChatGptFileUploadResponseDto {
        val commandMessage = ChatMessageDto.from(command, ChatRole.SYSTEM)
        val chatMessage = ChatMessageDto.from(chatMessages, ChatRole.USER)
        return chatGptService.sendFileUploadMessage(listOf(chatMessage, commandMessage))
    }

    companion object {
        const val MAX_CHAT_CHANCE: Int = 30
        const val MAX_MESSAGES_SIZE: Int = 15
    }

}
