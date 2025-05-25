package com.example.fitHerWay;

import com.example.fitHerWay.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
@EnableAsync
public class FitHerWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitHerWayApplication.class, args);
	}

}
