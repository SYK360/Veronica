package com.rolandopalermo.facturacion.ec.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericResponseDTO<T> extends AbstractBaseDTO {

	private T contenido;

}