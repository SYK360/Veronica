package com.rolandopalermo.facturacion.ec.web.controller;

import java.io.File;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolandopalermo.facturacion.ec.bo.SriBO;
import com.rolandopalermo.facturacion.ec.common.exception.BadRequestException;
import com.rolandopalermo.facturacion.ec.common.exception.InternalServerException;
import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.common.exception.ResourceNotFoundException;
import com.rolandopalermo.facturacion.ec.dto.AutorizacionRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.RecepcionRequestDTO;
import com.rolandopalermo.facturacion.ec.modelo.ComprobanteElectronico;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.guia.GuiaRemision;
import com.rolandopalermo.facturacion.ec.modelo.notacredito.NotaCredito;
import com.rolandopalermo.facturacion.ec.modelo.notadebito.NotaDebito;
import com.rolandopalermo.facturacion.ec.modelo.retencion.ComprobanteRetencion;

import autorizacion.ws.sri.gob.ec.RespuestaComprobante;
import io.swagger.annotations.Api;
import recepcion.ws.sri.gob.ec.RespuestaSolicitud;

@RestController
@RequestMapping(value = "/sri")
@Api(description = "Permite enviar o autorizar un comprobante electrónico.")
public class SRIController {

	private static final Logger logger = Logger.getLogger(SRIController.class);

	@Autowired
	private SriBO sriBO;

	@Value("${pkcs12.certificado.ruta}")
	private String rutaArchivoPkcs12;

	@Value("${pkcs12.certificado.clave}")
	private String claveArchivopkcs12;

	@Value("${sri.wsdl.recepcion}")
	private String wsdlRecepcion;

	@Value("${sri.wsdl.autorizacion}")
	private String wsdlAutorizacion;

	@PostMapping(value = "/enviar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaSolicitud> enviarComprobante(@RequestBody RecepcionRequestDTO request) {
		if (!new File(rutaArchivoPkcs12).exists()) {
			throw new ResourceNotFoundException("No se pudo encontrar el certificado de firma digital.");
		}
		try {
			return new ResponseEntity<RespuestaSolicitud>(
					sriBO.enviarComprobante(request.getContenido(), wsdlRecepcion), HttpStatus.OK);
		} catch (NegocioException e) {
			logger.error("enviarComprobante", e);
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			logger.error("enviarComprobante", e);
			throw new InternalServerException(e.getMessage());
		}
	}

	@PostMapping(value = "/autorizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaComprobante> autorizarComprobante(@RequestBody AutorizacionRequestDTO request) {
		try {
			return new ResponseEntity<RespuestaComprobante>(
					sriBO.autorizarComprobante(request.getClaveAcceso(), wsdlAutorizacion), HttpStatus.OK);
		} catch (NegocioException e) {
			logger.error("autorizarComprobante", e);
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			logger.error("autorizarComprobante", e);
			throw new InternalServerException(e.getMessage());
		}
	}

	@PostMapping(value = "/emitir/factura", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaComprobante> emitirFactura(@Valid @RequestBody Factura request) {
		return emitirDocumentoElectronico(request);
	}

	@PostMapping(value = "/emitir/guia-remision", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaComprobante> emitirGuiaRemision(@Valid @RequestBody GuiaRemision request) {
		return emitirDocumentoElectronico(request);
	}

	@PostMapping(value = "/emitir/nota-credito", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaComprobante> emitirNotaCredito(@Valid @RequestBody NotaCredito request) {
		return emitirDocumentoElectronico(request);
	}

	@PostMapping(value = "/emitir/nota-debito", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaComprobante> emitirNotaDebito(@Valid @RequestBody NotaDebito request) {
		return emitirDocumentoElectronico(request);
	}

	@PostMapping(value = "/emitir/comprobante-retencion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaComprobante> emitirComprobanteRetencion(
			@Valid @RequestBody ComprobanteRetencion request) {
		return emitirDocumentoElectronico(request);
	}

	private ResponseEntity<RespuestaComprobante> emitirDocumentoElectronico(ComprobanteElectronico request) {
		try {
			return new ResponseEntity<RespuestaComprobante>(sriBO.emitirComprobante(request, rutaArchivoPkcs12,
					claveArchivopkcs12, wsdlRecepcion, wsdlAutorizacion), HttpStatus.OK);
		} catch (NegocioException e) {
			logger.error("emitirDocumentoElectronico", e);
			throw new BadRequestException(e.getMessage());
		} catch (Exception e) {
			logger.error("emitirDocumentoElectronico", e);
			throw new InternalServerException(e.getMessage());
		}
	}

}