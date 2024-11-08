package com.server.computerscience.chatbot.dto.request;

import java.util.Collections;
import java.util.List;

import com.server.computerscience.chatbot.domain.ChatContentType;
import com.server.computerscience.chatbot.domain.ChatRole;

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
public class ChatMessageDto {
	private String role;
	private List<ChatContentDto> content;

	public static ChatMessageDto from(String text, ChatRole role) {
		
		return ChatMessageDto.builder()
			.role(role.getLower())
			.content(Collections.singletonList(ChatContentDto.from(ChatContentType.TEXT, text)))
			.build();
	}
}
