package com.server.computerscience.chatbot.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ChatGptResponseDto {
	private String id;
	private String object;
	private long created;
	private String model;
	private Usage usage;
	private List<Choice> choices;

	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@ToString
	public static class Choice {
		private Message message;
		private String finishReason;
		private int index;
	}

	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class Message {
		private String role;
		private String content;
	}

	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class Usage {
		private int promptTokens;
		private int completionTokens;
		private int totalTokens;
	}
}
