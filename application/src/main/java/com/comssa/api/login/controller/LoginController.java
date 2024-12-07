package com.comssa.api.login.controller;

import com.comssa.api.login.dto.ResponseAccessTokenDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"로그인"})
@RequiredArgsConstructor
public class LoginController {

	/*
	해당 API는 테스트용
	실제 로그인 요청은 클라이언트에서 직접 authorization/cognito 요청을 보낸다.
	 */
	@GetMapping("/cognito")
	public ResponseEntity<ResponseAccessTokenDto> getAccessToken(
		Authentication authentication, Model model
	) {
		if (authentication.isAuthenticated()) {
			model.addAttribute("principal", authentication.getPrincipal());
		}
		return ResponseEntity.ok(ResponseAccessTokenDto.from("hi"));
	}
}
