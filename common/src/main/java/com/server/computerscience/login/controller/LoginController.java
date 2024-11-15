package com.server.computerscience.login.controller;

import com.server.computerscience.login.dto.ResponseAccessTokenDto;
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
