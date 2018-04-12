package com.rolandopalermo.facturacion.ec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rolandopalermo.facturacion.ec.bo.SriBO;
import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.dto.AutorizacionRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.GeneradorRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.GenericResponseDTO;
import com.rolandopalermo.facturacion.ec.dto.RecepcionRequestDTO;
import com.rolandopalermo.facturacion.ec.modelo.ComprobanteElectronico;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.NotaCredito;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.NotaDebito;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;

import autorizacion.ws.sri.gob.ec.RespuestaComprobante;
import recepcion.ws.sri.gob.ec.RespuestaSolicitud;

@RestController
@RequestMapping(value = "/sri")
public class SriController {

	@Autowired
	private SriBO sriBO;

	@RequestMapping(value = "/enviar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<RespuestaSolicitud> enviarComprobante(@RequestBody RecepcionRequestDTO request) {
		GenericResponseDTO<RespuestaSolicitud> response = new GenericResponseDTO<RespuestaSolicitud>();
		try {
			response.setCodigo("0");
			response.setContenido(sriBO.enviarComprobante(request.getContenidoXML()));
		} catch (NegocioException e) {
			response.setCodigo("99");
			response.setMensaje(e.getMessage());
		} catch (Exception ex) {
			response.setCodigo("500");
			response.setMensaje("Ocurrió un error interno");
		}
		return response;
	}

	@RequestMapping(value = "/autorizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<RespuestaComprobante> autorizarComprobante(@RequestBody AutorizacionRequestDTO request) {
		GenericResponseDTO<RespuestaComprobante> response = new GenericResponseDTO<RespuestaComprobante>();
		try {
			response.setCodigo("0");
			response.setContenido(sriBO.autorizarComprobante(request.getClaveAcceso()));
		} catch (NegocioException e) {
			response.setCodigo("99");
			response.setMensaje(e.getMessage());
		} catch (Exception ex) {
			response.setCodigo("500");
			response.setMensaje("Ocurrió un error interno");
		}
		return response;
	}

	@RequestMapping(value = "/emitir/factura", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<RespuestaComprobante> emitirFactura(@RequestBody GeneradorRequestDTO<Factura> request) {
		return emitirDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/emitir/guia-remision", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<RespuestaComprobante> emitirGuiaRemision(
			@RequestBody GeneradorRequestDTO<GuiaRemision> request) {
		return emitirDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/emitir/nota-credito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<RespuestaComprobante> emitirNotaCredito(
			@RequestBody GeneradorRequestDTO<NotaCredito> request) {
		return emitirDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/emitir/nota-debito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<RespuestaComprobante> emitirNotaDebito(
			@RequestBody GeneradorRequestDTO<NotaDebito> request) {
		return emitirDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/emitir/comprobante-retencion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<RespuestaComprobante> emitirComprobanteRetencion(
			@RequestBody GeneradorRequestDTO<ComprobanteRetencion> request) {
		return emitirDocumentoElectronico(request.getComprobante());
	}

	private GenericResponseDTO<RespuestaComprobante> emitirDocumentoElectronico(ComprobanteElectronico request) {
		GenericResponseDTO<RespuestaComprobante> response = new GenericResponseDTO<RespuestaComprobante>();
		try {
			response.setCodigo("0");
			response.setContenido(sriBO.emitirComprobante(request));
		} catch (NegocioException e) {
			response.setCodigo("99");
			response.setMensaje(e.getMessage());
		} catch (Exception ex) {
			response.setCodigo("500");
			response.setMensaje("Ocurrió un error interno");
		}
		return response;
	}

}