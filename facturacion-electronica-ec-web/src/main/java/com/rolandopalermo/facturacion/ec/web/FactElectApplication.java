package com.rolandopalermo.facturacion.ec.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.mangofactory.swagger.plugin.EnableSwagger;

@SpringBootApplication
@ComponentScan({"com.rolandopalermo.facturacion.ec"})
@PropertySource("classpath:data.properties")
@EnableSwagger
public class FactElectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactElectApplication.class, args);
	}

}