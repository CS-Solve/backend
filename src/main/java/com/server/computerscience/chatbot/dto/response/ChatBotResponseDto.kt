package com.server.computerscience.chatbot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ChatBotResponseDto {
	private String content;

	public static ChatBotResponseDto from(String content) {
		return ChatBotResponseDto.builder().content(content).build();
	}
}
