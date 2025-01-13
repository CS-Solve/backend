package com.comssa.core.chatbot.service.implement

import com.comssa.persistence.chatbot.dto.request.ChatGptMessageDto
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.LinkedList

@Service
class ChatCacheService(
	private val cacheManager: CacheManager,
) {
	private fun getChatMessages(key: String): List<ChatGptMessageDto>? {
		// filterIsInstance는 "List" 내에서 특정타입의 요소들만 필터링 하여, 새로운 List를 만들어 반환한다.
		return cacheManager
			.getCache(CHAT_MESSAGE)
			?.get(key, List::class.java)
			?.filterIsInstance<ChatGptMessageDto>()
	}

	@CachePut(
		value = [CHAT_MESSAGE],
		key = "#key",
	)
	fun saveChatMessage(
		key: String,
		chatGptMessageDto: List<ChatGptMessageDto>,
		maxMessageSize: Int,
	): List<ChatGptMessageDto> {
		val chatMessages = getChatMessages(key)?.toMutableList() ?: LinkedList()
		chatMessages.addAll(chatGptMessageDto)
		if (chatMessages.size > maxMessageSize) {
			chatMessages.removeAt(0)
		}
		return chatMessages.toList()
	}

	// 남은 이용 횟수를 가져오는 메소드
	fun getUsedChance(userId: String): Int {
		val cache =
			cacheManager.getCache(CHAT_USED_CHANCE)
		return cache?.get(userId)?.get() as? Int
			?: DEFAULT_USED_COUNT
	}

	// 사용 횟수 증가 메소드
	@CachePut(
		value = [CHAT_USED_CHANCE],
		key = "#userId",
	)
	fun increaseUsedChance(userId: String): Int {
		val usedChance = getUsedChance(userId)

		return usedChance.inc()
	}

	/**
	 * 캐시 삭제로 채팅 가능 횟수 초기화
	 */
	@Scheduled(cron = "0 0 * * * *") // 매 시간 정각에 실행
	@CacheEvict(
		value = [CHAT_USED_CHANCE],
		allEntries = true,
	)
	fun clearAllUsedChance() {
	}

	companion object {
		const val DEFAULT_USED_COUNT: Int = 0
		const val CHAT_MESSAGE = "chatMessages"
		const val CHAT_USED_CHANCE = "chatRemainChance"
	}
}
