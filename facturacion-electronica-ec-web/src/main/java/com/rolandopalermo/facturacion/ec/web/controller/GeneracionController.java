package com.rolandopalermo.facturacion.ec.web.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rolandopalermo.facturacion.ec.bo.GeneradorBO;
import com.rolandopalermo.facturacion.ec.common.exception.BadRequestException;
import com.rolandopalermo.facturacion.ec.common.exception.InternalServerException;
import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.dto.GeneradorRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.GenericResponse;
import com.rolandopalermo.facturacion.ec.modelo.ComprobanteElectronico;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.NotaCredito;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.NotaDebito;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;

@RestController
@RequestMapping(value = "/generar")
public class GeneracionController {

	private static final Logger logger = Logger.getLogger(GeneracionController.class);

	@Autowired
	private GeneradorBO generadorBO;

	@RequestMapping(value = "/factura", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<byte[]>> generarFactura(
			@Valid @RequestBody Factura request) {
		return generarDocumentoElectronico(request);
	}

	@RequestMapping(value = "/guia-remision", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<byte[]>> generarGuiaRemision(
			@Valid @RequestBody GeneradorRequestDTO<GuiaRemision> request) {
		return generarDocumentoElectronico(request.getContenido());
	}

	@RequestMapping(value = "/nota-credito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<byte[]>> generarNotaCredito(
			@Valid @RequestBody GeneradorRequestDTO<NotaCredito> request) {
		return generarDocumentoElectronico(request.getContenido());
	}

	@RequestMapping(value = "/nota-debito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<byte[]>> generarNotaDebito(
			@Valid @RequestBody GeneradorRequestDTO<NotaDebito> request) {
		return generarDocumentoElectronico(request.getContenido());
	}

	@RequestMapping(value = "/comprobante-retencion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse<byte[]>> generarComprobanteRetencion(
			@Valid @RequestBody GeneradorRequestDTO<ComprobanteRetencion> request) {
		return generarDocumentoElectronico(request.getContenido());
	}

	private ResponseEntity<GenericResponse<byte[]>> generarDocumentoElectronico(ComprobanteElectronico request) {
		try {
			byte[] content = generadorBO.generarXMLDocumentoElectronico(request);
			return new ResponseEntity<GenericResponse<byte[]>>(new GenericResponse<byte[]>(content), HttpStatus.OK);
		} catch (NegocioException e) {
			logger.error("generarDocumentoElectronico", e);
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			logger.error("generarDocumentoElectronico", e);
			throw new InternalServerException(e.getMessage());
		}

	}

}