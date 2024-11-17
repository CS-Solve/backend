package com.comssa.core.authuser

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class AuthUserService {
	/**
	 * lateinint - Null일 경우 UninitializedPropertyAccessException 발생
	 */
	@Value("\${spring.security.oauth2.client.provider.cognito.user-name-attribute}")
	private lateinit var userIdentifier: String

	fun getCognitoId(user: OAuth2User): String? = user.attributes[userIdentifier] as? String?
}
