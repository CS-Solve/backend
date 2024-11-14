package com.server.computerscience.chatbot.service.implement

import com.server.computerscience.chatbot.dto.request.ChatMessageDto
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatCacheService(
    private val cacheManager: CacheManager,
    private val privilegeEvaluator: WebInvocationPrivilegeEvaluator
) {
    val DEFAULT_USED_COUNT: Int = 0;

    /**
     * 이미 저장되어있는 대화 목록이 있을 경우 기존 목록 반환, 아니라면 비어있는 목록을 반환
     */
    private fun getChatMessages(userId: String): List<ChatMessageDto?> {
        val cache = cacheManager.getCache(CHAT_MESSAGE)
        if (cache != null) {
            val chatMessages = cache.get(userId, List::class.java)
            //filterIsInstance는 리스트내에서 특정타입의 요소들만 필터링 한다 -> as 보다 타입 안전성을 확보하면서 제네릭 타입을 활용 가능하다.
            // reified를 사용하면 필터링없이 바로 반환 가능하지만, cache.get는 스프링 내장함수고 일반의 T를 반환하다.
            return chatMessages?.filterIsInstance<ChatMessageDto>() ?: LinkedList()
        }
        return LinkedList()
    }

    @CachePut(value = [CHAT_MESSAGE], key = "#userId")
    fun saveChatMessage(
        userId: String,
        chatMessageDto: ChatMessageDto,
        maxMessageSize: Int
    ): List<ChatMessageDto> {
        val chatMessages: MutableList<ChatMessageDto> = getChatMessages(userId).filterNotNull().toMutableList()
        chatMessages.add(chatMessageDto)
        if (chatMessages.size > maxMessageSize) {
            chatMessages.removeAt(0)
        }
        return chatMessages.toList()
    }

    //남은 이용 횟수를 가져오는 메소드
    fun getUsedChance(userId: String): Int {
        val cache = cacheManager.getCache(CHAT_USED_CHANCE)
        return cache?.get(userId)?.get() as? Int ?: DEFAULT_USED_COUNT
    }

    //사용 횟수 증가 메소드
    @CachePut(value = [CHAT_USED_CHANCE], key = "#userId")
    fun increaseUsedChance(userId: String): Int {
        val usedChance = getUsedChance(userId)
        return usedChance.inc()
    }

    /**
    매 시간마다 모든 사용자에 대한 사용 횟수 캐시를 삭제하는 메서드
     */
    @Scheduled(cron = "0 0 * * * *") // 매 시간 정각에 실행
    @CacheEvict(value = [CHAT_USED_CHANCE], allEntries = true)
    open fun clearAllUsedChance() {
    }

    companion object {
        const val CHAT_MESSAGE = "chatMessages"
        const val CHAT_USED_CHANCE = "chatRemainChance";
    }
}
