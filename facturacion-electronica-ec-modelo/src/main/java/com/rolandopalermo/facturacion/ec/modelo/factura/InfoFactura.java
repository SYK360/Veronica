/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.factura;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
@XmlType(propOrder = { "fechaEmision", "dirEstablecimiento", "contribuyenteEspecial", "obligadoContabilidad",
		"tipoIdentificacionComprador", "guiaRemision", "razonSocialComprador", "identificacionComprador",
		"totalSinImpuestos", "totalDescuento", "totalImpuesto", "propina", "importeTotal", "moneda" })
public class InfoFactura {

	protected String fechaEmision;
	protected String dirEstablecimiento;
	protected String contribuyenteEspecial;
	protected String obligadoContabilidad;
	protected String tipoIdentificacionComprador;
	protected String guiaRemision;
	protected String razonSocialComprador;
	protected String identificacionComprador;
	protected BigDecimal totalSinImpuestos;
	protected BigDecimal totalDescuento;
	private List<TotalImpuesto> totalImpuesto;
	protected BigDecimal propina;
	protected BigDecimal importeTotal;
	protected String moneda;

	@XmlElementWrapper(name = "totalConImpuestos")
	public List<TotalImpuesto> getTotalImpuesto() {
		return totalImpuesto;
	}

}
