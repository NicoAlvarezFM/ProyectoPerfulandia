package com.ProyectoPerfulandia.Perfulandia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.ProyectoPerfulandia.Perfulandia.model")
@SpringBootApplication
public class PerfulandiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfulandiaApplication.class, args);
	}

}