package com.rolandopalermo.facturacion.ec.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.rolandopalermo.facturacion.ec"})
public class FactElectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactElectApplication.class, args);
	}

}
