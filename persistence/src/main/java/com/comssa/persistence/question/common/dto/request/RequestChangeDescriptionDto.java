package com.comssa.persistence.question.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestChangeDescriptionDto {
	private String description;

	public static RequestChangeDescriptionDto forTest() {
		return RequestChangeDescriptionDto.builder()
			.description("test")
			.build();
	}
}
