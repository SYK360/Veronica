/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.factura;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
@XmlType(propOrder = { "codigo", "codigoPorcentaje", "baseImponible", "tarifa", "valor" })
public class TotalImpuesto {

	protected String codigo;
	protected String codigoPorcentaje;
	protected BigDecimal baseImponible;
	protected BigDecimal tarifa;
	protected BigDecimal valor;

}
