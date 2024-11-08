package com.server.computerscience.chatbot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ChatGptBatchRequestDto {
	@JsonProperty("input_file_id")
	private String inputFileId;

	@JsonProperty("endpoint")
	private String endpoint;

	@JsonProperty("completion_window")
	private String completionWindow;

	public static ChatGptBatchRequestDto from(String inputFileId, String endpoint, String completionWindow) {
		return ChatGptBatchRequestDto.builder()
			.inputFileId(inputFileId)
			.endpoint(endpoint)
			.completionWindow(completionWindow)
			.build();
	}
}
