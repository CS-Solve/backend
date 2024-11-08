package com.server.computerscience.chatbot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatGptFileUploadResponseDto {
	private String id;
	private String object;
	private int bytes;
	private long createdAt;
	private String filename;
	private String purpose;
	private String status;
	private String statusDetails;
}
