package com.server.computerscience.chatbot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ChatGptConfig {

	@Value("${openai.secret-key}")
	private String secretKey;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		// Interceptor를 사용하여 기본 헤더 설정
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(restTemplate.getInterceptors());
		interceptors.add((request, body, execution) -> {
			HttpHeaders headers = request.getHeaders();
			headers.setBearerAuth(secretKey);
			headers.setContentType(MediaType.APPLICATION_JSON);
			return execution.execute(request, body);
		});
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
}
