package com.api.computerscience.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.persistence")
@EnableJpaRepositories(basePackages = "com.persistence")
public class JpaConfig {
}
