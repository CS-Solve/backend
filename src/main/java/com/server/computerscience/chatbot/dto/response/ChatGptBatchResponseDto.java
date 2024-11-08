package com.server.computerscience.chatbot.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ChatGptBatchResponseDto {
	private String id;
	private String object;
	private String endpoint;
	private String errors;
	private String inputFileId;
	private String completionWindow;
	private String status;
	private String outputFileId;
	private String errorFileId;
	private Long createdAt;
	private Long inProgressAt;
	private Long expiresAt;
	private Long finalizingAt;
	private Long completedAt;
	private Long failedAt;
	private Long expiredAt;
	private Long cancellingAt;
	private Long cancelledAt;
	private RequestCounts requestCounts;
	private Object metadata;

	@Getter
	@NoArgsConstructor
	@ToString
	public static class RequestCounts {
		private int total;
		private int completed;
		private int failed;
	}
}
