package com.rolandopalermo.facturacion.ec.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "{\"resultado\" : 1}";
	}
}
