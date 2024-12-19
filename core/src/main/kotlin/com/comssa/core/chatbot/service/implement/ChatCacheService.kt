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
	/**
	 * 이미 저장되어있는 대화 목록이 있을 경우 기존 목록 반환, 아니라면 비어있는 목록을 반환
	 */
	private fun getChatMessages(userId: String): List<ChatGptMessageDto?> {
		val cache =
			cacheManager.getCache(com.comssa.core.chatbot.service.implement.ChatCacheService.Companion.CHAT_MESSAGE)
		if (cache != null) {
			val chatMessages = cache.get(userId, List::class.java)
			// filterIsInstance는 리스트내에서 특정타입의 요소들만 필터링 한다 -> as 보다 타입 안전성을 확보하면서 제네릭 타입을 활용 가능하다.
			// reified를 사용하면 필터링없이 바로 반환 가능하지만, cache.get는 스프링 내장함수고 일반의 T를 반환하다.
			return chatMessages?.filterIsInstance<ChatGptMessageDto>() ?: LinkedList()
		}
		return LinkedList()
	}

	@CachePut(
		value = [com.comssa.core.chatbot.service.implement.ChatCacheService.Companion.CHAT_MESSAGE],
		key = "#userId",
	)
	fun saveChatMessage(
		userId: String,
		chatGptMessageDto: ChatGptMessageDto,
		maxMessageSize: Int,
	): List<ChatGptMessageDto> {
		val chatMessages: MutableList<ChatGptMessageDto> = getChatMessages(userId).filterNotNull().toMutableList()
		chatMessages.add(chatGptMessageDto)
		if (chatMessages.size > maxMessageSize) {
			chatMessages.removeAt(0)
		}
		return chatMessages.toList()
	}

	// 남은 이용 횟수를 가져오는 메소드
	fun getUsedChance(userId: String): Int {
		val cache =
			cacheManager.getCache(com.comssa.core.chatbot.service.implement.ChatCacheService.Companion.CHAT_USED_CHANCE)
		return cache?.get(userId)?.get() as? Int
			?: com.comssa.core.chatbot.service.implement.ChatCacheService.Companion.DEFAULT_USED_COUNT
	}

	// 사용 횟수 증가 메소드
	@CachePut(
		value = [com.comssa.core.chatbot.service.implement.ChatCacheService.Companion.CHAT_USED_CHANCE],
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
		value = [com.comssa.core.chatbot.service.implement.ChatCacheService.Companion.CHAT_USED_CHANCE],
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
