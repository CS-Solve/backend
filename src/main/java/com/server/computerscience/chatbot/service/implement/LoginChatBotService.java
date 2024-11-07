package com.server.computerscience.chatbot.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.domain.ChatRole;
import com.server.computerscience.chatbot.dto.request.ChatBotRequestDto;
import com.server.computerscience.chatbot.dto.request.ChatMessageDto;
import com.server.computerscience.chatbot.service.ChatbotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginChatBotService implements ChatbotService {
	private final ChatGptService chatGptService;
	private final ChatCacheService chatCacheService;
	private final String NOT_LOGIN = "로그인이 필요합니다.";
	private final String NO_MORE_CHANCE = "채팅 기회를 모두 소모하셨습니다.(1시간 마다 초기화)";
	@Value("${spring.security.oauth2.client.provider.cognito.user-name-attribute}")
	private String USER_IDENTIFIER;

	@Override
	public String talkToAssistant(ChatBotRequestDto chatBotRequestDto, OAuth2User user) {
		if (user == null) {
			return NOT_LOGIN;
		}
		String userId = (String)user.getAttributes().get(USER_IDENTIFIER);
		if (!chatCacheService.canChat(userId)) {
			return NO_MORE_CHANCE;
		}

		/**
		 * User가 가지고 있는 이전 대화 기록을 가져온다.
		 */
		List<ChatMessageDto> chatMessages = chatCacheService.getChatMessages(userId);
		ChatMessageDto chatMessageFromUser = ChatMessageDto.from(chatBotRequestDto.getPrompt(), ChatRole.USER);
		chatMessages.add(chatMessageFromUser);

		/**
		 * 챗봇에게 받은 답변 또한 이전 대화 기록에 넣는다.
		 */
		String answer = chatGptService.chat(chatMessages);
		ChatMessageDto chatMessageFromAssistant = ChatMessageDto.from(answer, ChatRole.ASSISTANT);
		chatMessages.add(chatMessageFromAssistant);

		chatCacheService.increaseUsedChance(userId);

		return answer;
	}
}
