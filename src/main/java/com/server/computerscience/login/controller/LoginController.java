package com.server.computerscience.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.computerscience.login.dto.ResponseAccessTokenDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	@GetMapping("/cognito")
	public ResponseEntity<ResponseAccessTokenDto> getAccessToken(
		Authentication authentication
	) {
		System.out.println(authentication.getPrincipal());
		System.out.println(authentication.getDetails());
		System.out.println(authentication.getPrincipal());
		System.out.println(authentication.getCredentials());
		return ResponseEntity.ok(ResponseAccessTokenDto.from("hi"));
	}
}
