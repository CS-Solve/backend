package com.server.computerscience.chatbot.service.implement;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.domain.ChatRole;
import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.dto.request.ChatContentDto;
import com.server.computerscience.chatbot.dto.request.ChatMessageDto;
import com.server.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatManageService {
	private static final int MAX_CHAT_CHANCE = 30;
	private static final int MAX_MESSAGES_SIZE = 15;
	private final String NO_MORE_CHANCE = "채팅 기회를 모두 소모하셨습니다. 1시간마다 초기화됩니다.";
	private final ChatCacheService chatCacheService;
	private final ChatGptService chatGptService;

	public String talkForChat(String userId, ChatBotRequestDto chatBotRequestDto) {
		if (chatCacheService.getUsedChance(userId) >= MAX_CHAT_CHANCE) {
			return NO_MORE_CHANCE;
		}
		/**
		 * User가 가지고 있는 이전 대화 기록을 가져온다.
		 */
		List<ChatMessageDto> chatMessages = beforeRespond(userId,
			chatBotRequestDto);
		/**
		 * 챗봇에게 받은 답변 또한 이전 대화 기록에 넣는다.
		 */
		String answer = chatGptService.sendChatMessage(chatMessages);
		afterRespond(userId, answer);
		return answer;
	}

	private List<ChatMessageDto> beforeRespond(String userId, ChatBotRequestDto chatBotRequestDto) {
		ChatMessageDto chatMessageFromUser = ChatMessageDto.from(chatBotRequestDto.getPrompt(), ChatRole.USER);
		return chatCacheService.saveChatMessage(userId, chatMessageFromUser, MAX_MESSAGES_SIZE);
	}

	private void afterRespond(String userId, String answer) {
		ChatMessageDto chatMessageFromAssistant = ChatMessageDto.from(answer, ChatRole.ASSISTANT);
		chatCacheService.saveChatMessage(userId, chatMessageFromAssistant, MAX_MESSAGES_SIZE);
		chatCacheService.increaseUsedChance(userId);
	}

	public ChatGptFileUploadResponseDto talkForBatch(List<ChatContentDto> chatMessages) {
		ChatMessageDto chatMessage = ChatMessageDto.from(chatMessages, ChatRole.USER);
		return chatGptService.sendFileUploadMessage(Collections.singletonList(chatMessage));
	}
}
