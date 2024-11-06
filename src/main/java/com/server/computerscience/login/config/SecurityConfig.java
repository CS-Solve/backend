package com.server.computerscience.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf()
			// CSRF 보호를 활성화합니다. 이 설정은 보안 강화에 도움이 됩니다.
			.and()
			.authorizeRequests(authz -> authz
				// .mvcMatchers("/")
				// 메인 페이지("/")에 대해 모든 사용자가 접근할 수 있도록 허용합니다.
				// .permitAll()
				// 나머지 요청은 인증된 사용자만 접근할 수 있도록 설정합니다.
				// 서비스는 로그인 없이도 사용할 수있는 서비스
				.anyRequest()
				.permitAll())

			// OAuth2 로그인을 활성화합니다. 이 설정으로 사용자가 로그인 페이지로 리디렉션됩니다.
			.oauth2Login()
			.defaultSuccessUrl(
				"/",
				true)
			.and()
			// 로그아웃 기능을 활성화하고, 로그아웃 성공 후 "/"로 리디렉션되도록 설정합니다.
			.logout()
			.logoutSuccessUrl("/");
		return http.build();
	}
}
