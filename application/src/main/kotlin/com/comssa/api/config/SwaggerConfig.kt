package com.comssa.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType
import org.springframework.boot.actuate.endpoint.ExposableEndpoint
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver
import org.springframework.boot.actuate.endpoint.web.EndpointMapping
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.util.StringUtils

@Configuration
class SwaggerConfig {
	private val title = "title"
	private val description = "description"
	private val version = "V1.0.0"

	@Bean
	fun api(): OpenAPI =
		OpenAPI()
			.info(
				Info()
					.title(title)
					.description(description)
					.version(version),
			)

	/**
	 * WebMvcEndpointHandlerMapping 빈을 생성합니다.
	 * 이 빈은 Spring Actuator의 웹 엔드포인트를 처리하는 데 사용됩니다.
	 *
	 * @param webEndpointsSupplier 웹 엔드포인트 공급자
	 * @param servletEndpointsSupplier 서블릿 엔드포인트 공급자
	 * @param controllerEndpointsSupplier 컨트롤러 엔드포인트 공급자
	 * @param endpointMediaTypes 엔드포인트 미디어 타입 설정
	 * @param corsProperties CORS 설정
	 * @param webEndpointProperties 웹 엔드포인트 설정
	 * @param environment 애플리케이션 환경 정보
	 * @return WebMvcEndpointHandlerMapping 객체
	 */
	@Bean
	fun webEndpointServletHandlerMapping(
		webEndpointsSupplier: WebEndpointsSupplier,
		servletEndpointsSupplier: ServletEndpointsSupplier,
		controllerEndpointsSupplier: ControllerEndpointsSupplier,
		endpointMediaTypes: EndpointMediaTypes?,
		corsProperties: CorsEndpointProperties,
		webEndpointProperties: WebEndpointProperties,
		environment: Environment,
	): WebMvcEndpointHandlerMapping {
		// 모든 엔드포인트를 수집할 리스트를 초기화합니다.
		val allEndpoints: MutableList<ExposableEndpoint<*>> = mutableListOf()

		// 웹 엔드포인트를 수집하고 리스트에 추가합니다.
		val webEndpoints = webEndpointsSupplier.endpoints
		allEndpoints.addAll(webEndpoints)

		// 서블릿 엔드포인트를 수집하고 리스트에 추가합니다.
		val servletEndpoints = servletEndpointsSupplier.endpoints
		allEndpoints.addAll(servletEndpoints)

		// 컨트롤러 엔드포인트를 수집하고 리스트에 추가합니다.
		val controllerEndpoints = controllerEndpointsSupplier.endpoints
		allEndpoints.addAll(controllerEndpoints)

		// 웹 엔드포인트의 기본 경로를 가져옵니다.
		val basePath = webEndpointProperties.basePath

		// 엔드포인트 매핑을 생성합니다.
		val endpointMapping = EndpointMapping(basePath)

		// 링크 매핑을 등록할지 여부를 결정합니다.
		val shouldRegisterLinksMapping = shouldRegisterLinksMapping(webEndpointProperties, environment, basePath)

		// WebMvcEndpointHandlerMapping 객체를 생성하여 반환합니다.
		return WebMvcEndpointHandlerMapping(
			endpointMapping, // 엔드포인트 매핑
			webEndpoints, // 웹 엔드포인트 컬렉션
			endpointMediaTypes, // 엔드포인트 미디어 타입
			corsProperties.toCorsConfiguration(), // CORS 설정
			EndpointLinksResolver(allEndpoints, basePath), // 엔드포인트 링크 리졸버
			shouldRegisterLinksMapping, // 링크 매핑 등록 여부
			null, // 추가적인 설정 (여기서는 null로 설정)
		)
	}

	/**
	 * 링크 매핑을 등록할지 여부를 결정하는 메서드입니다.
	 *
	 * @param webEndpointProperties 웹 엔드포인트 관련 설정
	 * @param environment 애플리케이션 환경 정보
	 * @param basePath 웹 엔드포인트의 기본 경로
	 * @return 링크 매핑을 등록해야 하면 true, 아니면 false
	 */
	private fun shouldRegisterLinksMapping(
		webEndpointProperties: WebEndpointProperties,
		environment: Environment,
		basePath: String,
	): Boolean =
		webEndpointProperties.discovery.isEnabled &&
			(
				StringUtils.hasText(basePath) ||
					ManagementPortType.get(environment) == ManagementPortType.DIFFERENT
			)
}
