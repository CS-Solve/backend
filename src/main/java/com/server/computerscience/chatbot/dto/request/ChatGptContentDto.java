package com.server.computerscience.chatbot.dto.request;

import com.server.computerscience.chatbot.domain.ChatGptContentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatGptContentDto {
	private String type;
	private String text;

	public static ChatGptContentDto from(ChatGptContentType type, String text) {
		return ChatGptContentDto.builder()
			.text(text)
			.type(type.getLower())
			.build();
	}
}
