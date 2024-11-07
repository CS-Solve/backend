package com.server.computerscience.chatbot.dto.request;

import java.util.List;
import java.util.stream.Collectors;

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
	private List<ChatGptMessageDto> messages;

	public static ChatGptRestRequestDto from(String model, List<String> prompts) {
		return ChatGptRestRequestDto.builder()
			.model(model)
			.messages(prompts.stream()
				.map(ChatGptMessageDto::fromUserText)
				.collect(Collectors.toList()))
			.build();
	}
}
