package com.comssa.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@Profile("!test")
class SecurityConfig {
	@Bean
	@Throws(Exception::class)
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
		http
			.csrf()
			.disable()
			.authorizeRequests { authz ->
				authz
					// 서비스는 로그인 없이도 사용할 수 있는 서비스기에 .permitAll()
					.anyRequest()
					.permitAll()
			}
			// OAuth2 로그인을 활성화합니다. 이 설정으로 사용자가 로그인 페이지로 리디렉션됩니다.
			.oauth2Login()
			.defaultSuccessUrl("/", true)
			.and() // 로그아웃 기능을 활성화하고, 로그아웃 성공 후 "/"로 리디렉션되도록 설정합니다.
			.logout()
			.logoutSuccessUrl("/")
		return http.build()
	}
}
