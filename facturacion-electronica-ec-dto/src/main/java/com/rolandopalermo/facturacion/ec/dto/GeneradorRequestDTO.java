package com.rolandopalermo.facturacion.ec.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneradorRequestDTO<T> {

	private T comprobante;

}