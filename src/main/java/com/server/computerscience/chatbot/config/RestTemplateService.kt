package com.server.computerscience.chatbot.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestTemplateService {
	private final RestTemplate restTemplate;

	// 공통화된 sendPostRequest 메서드
	public <T> ResponseEntity<T> sendPostRequest(
		String url,
		String bearerToken,
		MediaType contentType,
		Object body,
		Class<T> responseType
	) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + bearerToken);
		headers.setContentType(contentType);
		HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
	}
}
