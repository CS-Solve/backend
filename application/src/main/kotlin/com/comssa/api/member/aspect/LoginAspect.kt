package com.comssa.api.member.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.ui.Model

@Aspect
@Component
class LoginAspect {
	/**
	 * 특정 메서드에 @AddLoginStatusAttribute 어노테이션이 있으면
	 * 메서드 실행 전후에 이 advice가 실행됩니다.
	 * Model에 login 상태 부여
	 */
	@Around("@annotation(com.comssa.api.member.aspect.AddLoginStatusAttributeToView)")
	@Throws(Throwable::class)
	fun addLoginStatusAttribute(joinPoint: ProceedingJoinPoint): Any {
		// 현재 메서드의 파라미터 가져오기
		val args = joinPoint.args

		// Model 파라미터 찾기
		for (arg in args) {
			if (arg is Model) {
				// 인증 상태 확인 및 isLogin 속성 추가

				// cognito 요청을 하면 자동으로 저장 됨.
				val auth = SecurityContextHolder.getContext().authentication

				// 시큐리티 컨텍스트에 유저 관련 정보가 저장되어있다면
				val isAuthenticated =
					auth != null && auth.isAuthenticated && auth !is AnonymousAuthenticationToken
				arg.addAttribute("isLogin", isAuthenticated)
			}
		}
		// 메서드 실행
		return joinPoint.proceed(args)
	}
}
