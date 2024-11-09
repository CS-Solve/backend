package com.server.computerscience.chatbot.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@ToString
public class ChatGptRequestFileUploadDto {
	private String customId;
	private String method;
	private String url;
	private ChatGptRestRequestDto body;

	public static ChatGptRequestFileUploadDto from(ChatGptRestRequestDto body,
		String customId,
		String method,
		String url) {
		return ChatGptRequestFileUploadDto.builder()
			.body(body)
			.customId(customId)
			.method(method)
			.url(url)
			.build();
	}
}