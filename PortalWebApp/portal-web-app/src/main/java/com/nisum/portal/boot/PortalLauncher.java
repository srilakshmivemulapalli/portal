package com.nisum.portal.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@ComponentScan({"com.nisum"})
@EnableAutoConfiguration
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class PortalLauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(PortalLauncher.class, args);
	}
}
