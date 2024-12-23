package com.comssa.core.authuser.service

import com.comssa.core.member.service.MemberService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class AuthUserService(
	private val memberService: MemberService,
) {
	/**
	 * lateinint - Null일 경우 UninitializedPropertyAccessException 발생
	 */
	@Value("\${spring.security.oauth2.client.provider.cognito.user-name-attribute}")
	private lateinit var userIdentifier: String

	/**
	 * 들어온 User가 null이거나 userId가 null일 경우를 모두 check한다.
	 */
	fun getCognitoId(user: OAuth2User?): String? {
		if (user == null) {
			return null
		}
		/*
		로그인 되어있지 않은 상태라면 NULL
		 */
		val userId = user.attributes[userIdentifier] as? String ?: return null
		// DB에 저장되었는지 확인
		memberService.findAndSaveMember(userId)
		return userId
	}
}
