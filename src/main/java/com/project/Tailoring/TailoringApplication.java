package com.project.Tailoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class TailoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TailoringApplication.class, args);
	}

}
