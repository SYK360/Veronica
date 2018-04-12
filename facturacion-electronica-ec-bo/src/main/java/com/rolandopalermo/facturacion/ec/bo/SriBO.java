package com.rolandopalermo.facturacion.ec.bo;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.common.util.ArchivoUtil;
import com.rolandopalermo.facturacion.ec.common.util.MarshallerUtil;
import com.rolandopalermo.facturacion.ec.modelo.ComprobanteElectronico;
import com.rolandopalermo.facturacion.ec.soap.client.AutorizacionComprobanteProxy;
import com.rolandopalermo.facturacion.ec.soap.client.EnvioComprobantesProxy;

import autorizacion.ws.sri.gob.ec.RespuestaComprobante;
import recepcion.ws.sri.gob.ec.RespuestaSolicitud;

@Service
public class SriBO {

	@Value("${sri.wsdl.recepcion}")
	private String wsdlRecepcion;

	@Value("${sri.wsdl.autorizacion}")
	private String wsdlAutorizacion;

	@Autowired
	private FirmadorBO firmadorBO;

	@Autowired
	private SriBO sriBO;

	public RespuestaSolicitud enviarComprobante(byte[] xml) throws NegocioException {
		try {
			EnvioComprobantesProxy proxy = new EnvioComprobantesProxy(wsdlRecepcion);
			return proxy.enviarComprobante(xml);
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			throw new NegocioException("No se pudo enviar el comprobante electrónico al SRI.");
		}
	}

	public RespuestaComprobante autorizarComprobante(String claveAcceso) throws NegocioException {
		try {
			AutorizacionComprobanteProxy proxy = new AutorizacionComprobanteProxy(wsdlAutorizacion);
			return proxy.autorizacionIndividual(claveAcceso);
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			throw new NegocioException(
					String.format("No se pudo autorizar el comprobante electrónico [%s].", claveAcceso));
		}
	}

	public RespuestaComprobante emitirComprobante(ComprobanteElectronico comprobante) throws NegocioException {
		try {
			// 1.- Crear archivo temporal para el xml
			String rutaArchivoXML = UUID.randomUUID().toString();
			File temp = File.createTempFile(rutaArchivoXML, ".xml");
			rutaArchivoXML = temp.getAbsolutePath();
			// 2.- Ejecutar Marshalling
			MarshallerUtil.marshall(comprobante, rutaArchivoXML);
			// 3.- Firmar el archivo
			byte[] xml = firmadorBO.firmarComprobanteElectronico(ArchivoUtil.convertirArchivoAByteArray(temp));
			// 4.- Enviar a Recepción
			RespuestaSolicitud respuestaSolicitud = sriBO.enviarComprobante(xml);
			if (respuestaSolicitud != null && respuestaSolicitud.getEstado() != null
					&& respuestaSolicitud.getEstado().toUpperCase().compareTo("RECIBIDA") == 0) {
				//5.- Autorizar comprobante
				String claveAcceso = comprobante.getInfoTributaria().getClaveAcceso();
				RespuestaComprobante respuestaComprobante = sriBO.autorizarComprobante(claveAcceso);
				return respuestaComprobante;
			} else {
				//loguear error
				throw new NegocioException("No se pudo autorizar el comprobante electrónico.");
			}
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			throw new NegocioException("No se pudo autorizar el comprobante electrónico.");
		}
	}

}
