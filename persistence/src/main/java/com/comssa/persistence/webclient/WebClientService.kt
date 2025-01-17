package com.comssa.persistence.webclient

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class WebClientService {
	fun <T> sendPostRequestBySse(
		baseUrl: String,
		uri: String,
		bearerToken: String,
		contentType: MediaType,
		body: Any,
		responseType: Class<T>,
		emitter: SseEmitter,
		// 필요한 데이터만 추출하는 메소드( ex. ChatGpt Response Dto에서 content)
		contentExtractor: (T) -> String?,
	): T? {
		val webClient = WebClient.create(baseUrl)
		val result = mutableListOf<T>()
		webClient
			.post()
			.uri(uri)
			.header(HttpHeaders.AUTHORIZATION, "Bearer $bearerToken")
			.contentType(contentType)
			.body(BodyInserters.fromValue(body))
			.exchangeToFlux { response ->
				response
					.bodyToFlux(String::class.java)
			}.doOnNext { data ->
				val parsedData =
					try {
						val objectMapper =
							ObjectMapper().apply {
								// 없는 변수에 대해선 무시
								configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
							}
						objectMapper.readValue(data, responseType) // JSON 파싱 시도
					} catch (e: Exception) {
						null // 파싱 실패 시 null 반환
					}
				parsedData?.let {
					result.add(it)
					emitter.send(it)
					contentExtractor(it)?.let { content ->
						print(content)
// 						emitter.send(content)
					}
				}
			}.doOnComplete(emitter::complete)
			.doOnError { error ->
				emitter.completeWithError(error)
				error.printStackTrace()
			}.subscribe()
		return result.lastOrNull()
	}

	fun <T> sendPostRequest(
		baseUrl: String,
		uri: String,
		bearerToken: String,
		contentType: MediaType,
		body: Any,
		responseType: Class<T>,
	): T? {
		val webClient = WebClient.create(baseUrl)
		val result = mutableListOf<T>()
		webClient
			.post()
			.uri(uri)
			.header(HttpHeaders.AUTHORIZATION, "Bearer $bearerToken")
			.contentType(contentType)
			.body(BodyInserters.fromValue(body))
			.exchangeToFlux { response ->
				response.bodyToFlux(responseType)
			}.doOnNext { data ->
				println(data)
				result.add(data)
			}.blockLast()
		return result.lastOrNull()
	}
}
