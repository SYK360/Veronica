/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo;

import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
public class DetAdicional {

	protected String nombre;
	protected String valor;

	@XmlAttribute(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	@XmlAttribute(name = "valor")
	public String getValor() {
		return valor;
	}

}
