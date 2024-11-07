package com.server.computerscience.chatbot.service.implement;

import java.util.LinkedList;
import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.dto.request.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatCacheService {
	private static final String CHAT_MESSAGE = "chatMessages";
	private static final String CHAT_USED_CHANCE = "chatRemainChance";
	private static final int MAX_MESSAGES_SIZE = 10;
	private final CacheManager cacheManager;

	/**
	 * 이미 저장되어있는 대화 목록이 있을 경우, 아니라면 새로운 목록을 반환
	 */
	public List<ChatMessageDto> getChatMessages(String userId) {
		Cache cache = cacheManager.getCache(CHAT_MESSAGE);
		if (cache != null) {
			List<ChatMessageDto> chatMessages = cache.get(userId, List.class);
			return chatMessages != null ? chatMessages : new LinkedList<>();
		}
		return new LinkedList<>();
	}

	@CachePut(value = CHAT_MESSAGE, key = "#userId")
	public List<ChatMessageDto> saveChatMessage(String userId, ChatMessageDto chatMessageDto) {
		List<ChatMessageDto> chatMessages = getChatMessages(userId); // 캐시에서 가져오기
		chatMessages.add(chatMessageDto);
		if (chatMessages.size() > MAX_MESSAGES_SIZE) {
			chatMessages.remove(0);
		}
		return chatMessages;
	}

	// 남은 이용 횟수를 가져오는 메서드
	public Integer getUsedChance(String userId) {
		Cache cache = cacheManager.getCache(CHAT_USED_CHANCE);
		if (cache != null) {
			Integer usedChance = cache.get(userId, Integer.class);
			return usedChance != null ? usedChance : 0; // 캐시에서 가져온 값이 없으면 0 반환
		}
		return 0; // 기본값
	}

	// 사용 횟수를 증가시키는 메서드
	@CachePut(value = CHAT_USED_CHANCE, key = "#userId")
	public int increaseUsedChance(String userId) {
		int currentUsedChance = getUsedChance(userId);
		currentUsedChance = currentUsedChance + 1;
		return currentUsedChance;
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
