package com.rolandopalermo.facturacion.ec.soap.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;

import autorizacion.ws.sri.gob.ec.AutorizacionComprobantesOffline;
import autorizacion.ws.sri.gob.ec.AutorizacionComprobantesOfflineService;
import autorizacion.ws.sri.gob.ec.RespuestaComprobante;

public class AutorizacionComprobanteProxy {

	private AutorizacionComprobantesOfflineService service;
	private AutorizacionComprobantesOffline port;

	public AutorizacionComprobanteProxy(String wsdlLocation) throws NegocioException {
		try {
			service = new AutorizacionComprobantesOfflineService(new URL(wsdlLocation),
					new QName("http://ec.gob.sri.ws.autorizacion", "AutorizacionComprobantesOfflineService"));
			port = service.getAutorizacionComprobantesOfflinePort();
			((BindingProvider) port).getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", 10000);
			((BindingProvider) port).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", 10000);
		} catch (Exception ex) {
			throw new NegocioException("No se pudo inicilizar el proxy para la autorización de comprobantes.");
		}
	}

	public RespuestaComprobante autorizacionIndividual(String claveDeAcceso) throws NegocioException {
		RespuestaComprobante response = null;
		try {
			response = port.autorizacionComprobante(claveDeAcceso);
		} catch (Exception e) {
			throw new NegocioException(String.format("No se pudo autorizar el comprobante [%s].", claveDeAcceso));
		}
		return response;
	}

}