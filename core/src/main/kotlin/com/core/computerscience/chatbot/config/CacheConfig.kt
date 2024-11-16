package com.core.computerscience.chatbot.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class CacheConfig {
	@Bean
	fun cacheManager(): CacheManager {
		val cacheManager = CaffeineCacheManager()
		cacheManager.setCaffeine(
			Caffeine
				.newBuilder()
				.expireAfterWrite(1, TimeUnit.HOURS) // 1시간 후 만료
				.maximumSize(1000),
		) // 최대 1000개의 항목 유지
		return cacheManager
	}
}
