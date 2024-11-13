package com.server.computerscience.chatbot.dto.request;

import com.server.computerscience.chatbot.domain.ChatContentType;

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
public class ChatContentDto {
	private String type;
	private String text;

	public static ChatContentDto from(ChatContentType type, String text) {
		return ChatContentDto.builder()
			.text(text)
			.type(type.getLower())
			.build();
	}
}
