package com.example.coursespring;

import com.example.coursespring.keycloak.config.KeycloakInitializerConfigurationProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
 @SpringBootApplication()
 @EnableConfigurationProperties(KeycloakInitializerConfigurationProperties.class)

public class CourseSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseSpringApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
