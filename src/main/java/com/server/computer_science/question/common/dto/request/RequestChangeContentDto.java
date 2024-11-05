package com.server.computer_science.question.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestChangeContentDto {
	private String content;

	public static RequestChangeContentDto forTest() {
		return RequestChangeContentDto.builder()
			.content("test")
			.build();
	}
}
