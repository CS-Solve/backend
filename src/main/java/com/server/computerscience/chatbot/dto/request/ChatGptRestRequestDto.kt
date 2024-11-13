package com.server.computerscience.chatbot.dto.request;

import java.util.List;

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
public class ChatGptRestRequestDto {
	private String model;
	private List<ChatMessageDto> messages;

	public static ChatGptRestRequestDto from(String model, List<ChatMessageDto> chatMessages) {
		return ChatGptRestRequestDto.builder()
			.model(model)
			.messages(chatMessages)
			.build();
	}
}
