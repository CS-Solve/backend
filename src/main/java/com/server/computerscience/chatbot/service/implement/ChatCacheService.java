package com.server.computerscience.chatbot.service.implement;

import java.util.LinkedList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.dto.request.ChatMessageDto;

@Service
public class ChatCacheService {
	private static final String CHAT_MESSAGE = "chatMessages";
	private static final String CHAT_USED_CHANCE = "chatRemainChance";

	private static final int MAX_MESSAGES_SIZE = 10;

	/**
	 * 이미 저장되어있는 대화목록이 있을 경우
	 * 아니라면 그대로 반환
	 */
	@Cacheable(value = CHAT_MESSAGE, key = "#userId")
	public List<ChatMessageDto> getChatMessages(String userId) {
		return new LinkedList<>();
	}

	@CachePut(value = CHAT_MESSAGE, key = "#userId")
	public List<ChatMessageDto> saveChatMessage(String userId, ChatMessageDto chatMessageDto) {
		List<ChatMessageDto> chatMessages = getChatMessages(userId);
		chatMessages.add(chatMessageDto);
		if (chatMessages.size() > MAX_MESSAGES_SIZE) {
			chatMessages.remove(0);
		}
		return chatMessages;
	}

	/**
	 * 특정 사용자의 메시지 캐시를 삭제하는 메서드
	 */
	@CacheEvict(value = CHAT_MESSAGE, key = "#userId")
	public void clearUserMessages(String userId) {
		// 캐시에서 삭제할 내용이 없으면 자동으로 무시됩니다
	}

	// 남은 이용 횟수를 가져오는 메서드
	@Cacheable(value = CHAT_USED_CHANCE, key = "#userId")
	public Integer getUsedChance(String userId) {
		System.out.println("cacheNew");
		return 0;
	}

	// 사용 횟수를 증가시키는 메서드
	@CachePut(value = CHAT_USED_CHANCE, key = "#userId")
	public Integer increaseUsedChance(String userId) {
		int currentUsedChance = getUsedChance(userId);
		System.out.println(currentUsedChance);
		return currentUsedChance + 1; // 현재 사용 횟수에 1 추가
	}

	/**
	 * 매 시간마다 모든 사용자에 대한 사용 횟수 캐시를 삭제하는 메서드
	 */
	@Scheduled(cron = "0 0 * * * *") // 매 시간 정각에 실행
	@CacheEvict(value = CHAT_USED_CHANCE, allEntries = true)
	public void clearAllUsedChances() {
		// 모든 사용자에 대한 사용 횟수 캐시를 삭제합니다.
		System.out.println("모든 사용자에 대한 사용 횟수 캐시를 삭제했습니다.");
	}

}
