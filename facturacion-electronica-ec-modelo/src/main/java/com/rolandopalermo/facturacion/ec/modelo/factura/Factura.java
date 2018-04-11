/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rolandopalermo.facturacion.ec.modelo.factura;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.rolandopalermo.facturacion.ec.modelo.CampoAdicional;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rolando
 */
@Getter
@Setter
@XmlRootElement(name = "factura")
@XmlType(propOrder = { "id", "version", "infoTributaria", "infoFactura", "detalle", "campoAdicional" })
public class Factura {

	protected String id;
	protected String version;
	protected InfoTributaria infoTributaria;
	protected InfoFactura infoFactura;
	private List<FacturaDetalle> detalle;
	private List<CampoAdicional> campoAdicional;

	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}

	@XmlElementWrapper(name = "detalles")
	public List<FacturaDetalle> getDetalle() {
		return detalle;
	}

	@XmlElementWrapper(name = "infoAdicional")
	public List<CampoAdicional> getCampoAdicional() {
		return campoAdicional;
	}

	@XmlAttribute(name = "version")
	public String getVersion() {
		return version;
	}

}
