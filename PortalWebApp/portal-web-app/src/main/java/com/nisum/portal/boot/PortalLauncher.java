package com.nisum.portal.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.nisum"})
@EnableAutoConfiguration
public class PortalLauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(PortalLauncher.class, args);
	}
}
