/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo;

import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
@XmlType(propOrder = { "ambiente", "tipoEmision", "razonSocial", "nombreComercial", "ruc", "claveAcceso", "codDoc",
		"estab", "ptoEmi", "secuencial", "dirMatriz" })
public class InfoTributaria {

	protected String ambiente;
	protected String tipoEmision;
	protected String razonSocial;
	protected String nombreComercial;
	protected String ruc;
	protected String claveAcceso;
	protected String codDoc;
	protected String estab;
	protected String ptoEmi;
	protected String secuencial;
	protected String dirMatriz;

}
