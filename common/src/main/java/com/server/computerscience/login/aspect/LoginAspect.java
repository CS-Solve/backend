package com.server.computerscience.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class LoginAspect {
    /**
     * 특정 메서드에 @AddLoginStatusAttribute 어노테이션이 있으면
     * 메서드 실행 전후에 이 advice가 실행됩니다.
     */
    @Around("@annotation(com.server.computerscience.login.aspect.AddLoginStatusAttribute)")
    public Object addLoginStatusAttribute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 현재 메서드의 파라미터 가져오기
        Object[] args = joinPoint.getArgs();

        // Model 파라미터 찾기
        for (Object arg : args) {
            if (arg instanceof Model) {
                Model model = (Model) arg;
                // 인증 상태 확인 및 isLogin 속성 추가
                // cognito 요청을 하면 자동으로 저장 됨.
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                boolean isAuthenticated =
                        auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
                model.addAttribute("isLogin", isAuthenticated);
            }
        }

        // 메서드 실행
        return joinPoint.proceed(args);
    }
}
