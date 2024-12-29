package com.comssa.api.member

import com.comssa.api.member.dto.ResponseAccessTokenDto
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["로그인"])
class LoginController {
	/*
	해당 API는 테스트용
	실제 로그인 요청은 클라이언트에서 직접 authorization/cognito 요청을 보낸다.
	 */
	@GetMapping("/cognito")
	fun getAccessToken(
		authentication: Authentication,
		model: Model,
	): ResponseEntity<ResponseAccessTokenDto> {
		if (authentication.isAuthenticated) {
			model.addAttribute("principal", authentication.principal)
		}
		return ResponseEntity.ok(ResponseAccessTokenDto.from("hi"))
	}
}
