package com.server.computerscience.chatbot.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.domain.ChatRole;
import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.dto.request.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatManageService {
	private final String NO_MORE_CHANCE = "채팅 기회를 모두 소모하셨습니다.(1시간 마다 초기화)";
	private static final int MAX_CHAT_CHANCE = 20;
	private final ChatCacheService chatCacheService;
	private final ChatGptService chatGptService;

	public String respond(String userId, ChatBotRequestDto chatBotRequestDto) {
		if (chatCacheService.getUsedChance(userId) >= MAX_CHAT_CHANCE) {
			return NO_MORE_CHANCE;
		}
		System.out.println(userId);
		/**
		 * User가 가지고 있는 이전 대화 기록을 가져온다.
		 */
		List<ChatMessageDto> chatMessages = beforeRespond(userId,
			chatBotRequestDto);

		/**
		 * 챗봇에게 받은 답변 또한 이전 대화 기록에 넣는다.
		 */
		String answer = chatGptService.chat(chatMessages);
		afterRespond(userId, answer, chatMessages);
		System.out.println(chatCacheService.getUsedChance(userId));

		return answer;
	}

	private List<ChatMessageDto> beforeRespond(String userId, ChatBotRequestDto chatBotRequestDto) {
		List<ChatMessageDto> chatMessages = chatCacheService.getChatMessages(userId);
		ChatMessageDto chatMessageFromUser = ChatMessageDto.from(chatBotRequestDto.getPrompt(), ChatRole.USER);
		chatCacheService.saveChatMessage(userId, chatMessageFromUser);
		return chatMessages;
	}

	private void afterRespond(String userId, String answer, List<ChatMessageDto> chatMessages) {
		ChatMessageDto chatMessageFromAssistant = ChatMessageDto.from(answer, ChatRole.ASSISTANT);
		chatCacheService.saveChatMessage(userId, chatMessageFromAssistant);
		chatCacheService.increaseUsedChance(userId);
	}
}
