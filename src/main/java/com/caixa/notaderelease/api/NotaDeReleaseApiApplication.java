package com.caixa.notaderelease.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.caixa.notaderelease.api.config.property.NotaDeReleaseApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(NotaDeReleaseApiProperty.class)
public class NotaDeReleaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotaDeReleaseApiApplication.class, args);
	}
	
}
