package com.rolandopalermo.facturacion.ec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rolandopalermo.facturacion.ec.bo.GeneradorBO;
import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.dto.GeneradorRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.GenericResponseDTO;
import com.rolandopalermo.facturacion.ec.modelo.ComprobanteElectronico;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.NotaCredito;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.NotaDebito;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;

@RestController
@RequestMapping(value = "/generacion")
public class GeneracionController {

	@Autowired
	private GeneradorBO generadorBO;

	@RequestMapping(value = "/factura", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<byte[]> generarFactura(@RequestBody GeneradorRequestDTO<Factura> request) {
		return generarDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/guia-remision", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<byte[]> generarGuiaRemision(@RequestBody GeneradorRequestDTO<GuiaRemision> request) {
		return generarDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/nota-credito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<byte[]> generarNotaCredito(@RequestBody GeneradorRequestDTO<NotaCredito> request) {
		return generarDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/nota-debito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<byte[]> generarNotaDebito(@RequestBody GeneradorRequestDTO<NotaDebito> request) {
		return generarDocumentoElectronico(request.getComprobante());
	}

	@RequestMapping(value = "/comprobante-retencion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<byte[]> generarComprobanteRetencion(
			@RequestBody GeneradorRequestDTO<ComprobanteRetencion> request) {
		return generarDocumentoElectronico(request.getComprobante());
	}

	private GenericResponseDTO<byte[]> generarDocumentoElectronico(ComprobanteElectronico request) {
		GenericResponseDTO<byte[]> response = new GenericResponseDTO<byte[]>();
		try {
			response.setCodigo("0");
			response.setContenido(generadorBO.generarXMLDocumentoElectronico(request));
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