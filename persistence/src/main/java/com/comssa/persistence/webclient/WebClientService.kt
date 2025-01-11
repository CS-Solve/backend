package com.comssa.persistence.webclient

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
				emitter.send(data)
			}.doOnError(emitter::completeWithError)
			.doOnComplete(emitter::complete)
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
