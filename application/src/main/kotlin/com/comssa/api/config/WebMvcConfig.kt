package com.comssa.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
	override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
		registry.addResourceHandler("/robots.txt").addResourceLocations("classpath:/static/robots.txt")
		registry.addResourceHandler("/sitemap.xml").addResourceLocations("classpath:/static/sitemap.xml")
	}

	@Bean
	fun restTemplate(): RestTemplate = RestTemplate()
}
