package com.server.computerscience.chatbot.config

import lombok.RequiredArgsConstructor
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@RequiredArgsConstructor
class RestTemplateService(
    private val restTemplate: RestTemplate
) {

    // 공통화된 sendPostRequest 메서드
    fun <T> sendPostRequest(
        url: String,
        bearerToken: String,
        contentType: MediaType?,
        body: Any,
        responseType: Class<T>
    ): ResponseEntity<T> {
        val headers = HttpHeaders()
        headers["Authorization"] = "Bearer $bearerToken"
        headers.contentType = contentType
        val requestEntity = HttpEntity(body, headers)
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType)
    }
}
