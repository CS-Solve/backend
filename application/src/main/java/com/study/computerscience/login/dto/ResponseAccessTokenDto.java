package com.study.computerscience.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResponseAccessTokenDto {
	private String accessToken;

	public static ResponseAccessTokenDto from(String accessToken) {
		return ResponseAccessTokenDto.builder()
			.accessToken(accessToken)
			.build();
	}
}
