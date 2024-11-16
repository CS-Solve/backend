package com.comssa.api;

import com.comssa.api.config.JpaConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Import({JpaConfig.class})
public class ServiceIntegrationTest {

}
