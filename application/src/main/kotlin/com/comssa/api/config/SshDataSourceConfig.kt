package com.comssa.api.config

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Profile("local")
@Configuration
class SshDataSourceConfig(
	val initializer: SshTunnelingInitializer,
) {
	val log = KotlinLogging.logger {}

	@Bean("dataSource")
	@Primary
	fun dataSource(properties: DataSourceProperties): DataSource {
		val forwardedPort = initializer.buildSshConnection()
		val url = properties.url.replace("[forwardedPort]", forwardedPort.toString())
		log.info(url)
		return DataSourceBuilder
			.create()
			.url(url)
			.username(properties.username)
			.password(properties.password)
			.driverClassName(properties.driverClassName)
			.build()
	}
}
