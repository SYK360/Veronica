package com.rolandopalermo.facturacion.ec.soap.client;

import java.net.URL;

import javax.xml.namespace.QName;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;

import recepcion.ws.sri.gob.ec.RecepcionComprobantesOffline;
import recepcion.ws.sri.gob.ec.RecepcionComprobantesOfflineService;
import recepcion.ws.sri.gob.ec.RespuestaSolicitud;

public class EnvioComprobantesProxy {

	private RecepcionComprobantesOffline port;
	private static RecepcionComprobantesOfflineService service;

	public EnvioComprobantesProxy(String wsdlLocation) throws NegocioException {
		try {
			URL url = new URL(wsdlLocation);
			QName qname = new QName("http://ec.gob.sri.ws.recepcion", "RecepcionComprobantesOfflineService");
			service = new RecepcionComprobantesOfflineService(url, qname);
			port = service.getRecepcionComprobantesOfflinePort();
		} catch (Exception ex) {
			throw new NegocioException("No se pudo inicilizar el proxy para la autorización de comprobantes.");
		}
	}

	public RespuestaSolicitud enviarComprobante(byte[] archivoBytes) throws NegocioException {
		try {
			RespuestaSolicitud response = null;
			if (archivoBytes != null) {
				response = port.validarComprobante(archivoBytes);
			}
			return response;
		} catch (Exception ex) {
			throw new NegocioException("No se pudo enviar el comprobante.");
		}
	}

}