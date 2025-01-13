package com.comssa.persistence.webclient

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.MismatchedInputException
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
				println(data)
				try {
					val objectMapper =
						ObjectMapper().apply {
							configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
							// 정의되지 않은 필드 무시
						}
					val parsedData: T = objectMapper.readValue(data, responseType)
					result.add(parsedData)
					contentExtractor(parsedData)?.let {
						emitter.send(it)
					}
				} catch (e: MismatchedInputException) {
					emitter.complete()
				}
			}.doOnComplete(emitter::complete)
			.subscribe()
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
