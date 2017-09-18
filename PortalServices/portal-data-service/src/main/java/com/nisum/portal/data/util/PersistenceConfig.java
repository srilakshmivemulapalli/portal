package com.nisum.portal.data.util;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.nisum.portal.data.domain"})
@EnableJpaRepositories(basePackages = {"com.nisum.portal.data"})
@EnableTransactionManagement
public class PersistenceConfig {
	
	
	
}
