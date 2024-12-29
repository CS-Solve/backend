package com.comssa.api.member.dto

data class ResponseAccessTokenDto(
	val accessToken: String,
) {
	companion object {
		@JvmStatic
		fun from(accessToken: String): ResponseAccessTokenDto =
			ResponseAccessTokenDto(
				accessToken = accessToken,
			)
	}
}
