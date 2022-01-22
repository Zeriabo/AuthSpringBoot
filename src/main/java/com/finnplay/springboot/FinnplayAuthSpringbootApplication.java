package com.finnplay.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.finnplay.springboot.repository", "com.finnplay.springboot.model","com.finnplay.controller"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FinnplayAuthSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinnplayAuthSpringbootApplication.class, args);
	}

}
