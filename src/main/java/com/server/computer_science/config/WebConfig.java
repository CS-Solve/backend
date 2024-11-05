package com.server.computer_science.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/robots.txt").addResourceLocations("classpath:/static/robots.txt");
		registry.addResourceHandler("/sitemap.xml").addResourceLocations("classpath:/static/sitemap.xml");
	}
}
