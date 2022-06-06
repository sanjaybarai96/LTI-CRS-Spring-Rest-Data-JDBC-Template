package com.lt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.lt.*")
@EnableAutoConfiguration
@EnableWebMvc
public class LtiCrsSpringRestDataJdbcTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtiCrsSpringRestDataJdbcTemplateApplication.class, args);
	}

}
