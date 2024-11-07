package com.server.computerscience.chatbot.dto.request;

import java.util.Collections;
import java.util.List;

import com.server.computerscience.chatbot.domain.ChatGptContentType;
import com.server.computerscience.chatbot.domain.ChatGptRole;

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
public class ChatGptMessageDto {
	private String role;
	private List<ChatGptContentDto> content;

	public static ChatGptMessageDto fromUserText(String text) {
		return ChatGptMessageDto.builder()
			.role(ChatGptRole.USER.getLower())
			.content(Collections.singletonList(ChatGptContentDto.from(ChatGptContentType.TEXT, text)))
			.build();
	}
}
