package com.server.computerscience.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/robots.txt").addResourceLocations("classpath:/static/robots.txt");
		registry.addResourceHandler("/sitemap.xml").addResourceLocations("classpath:/static/sitemap.xml");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
			.allowedOrigins("http://localhost") // 허용할 출처 설정
			.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
			.allowCredentials(true); // 자격 증명을 사용할 경우 true 설정
	}
}
