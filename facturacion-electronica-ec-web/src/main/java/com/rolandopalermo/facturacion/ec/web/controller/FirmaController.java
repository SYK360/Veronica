package com.rolandopalermo.facturacion.ec.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rolandopalermo.facturacion.ec.bo.FirmadorBO;
import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.dto.FirmadorRequestDTO;
import com.rolandopalermo.facturacion.ec.dto.GenericResponseDTO;

@RestController
@RequestMapping(value = "/firma")
public class FirmaController {

	@Autowired
	private FirmadorBO firmadorBO;

	@RequestMapping(value = "/comprobante-electronico", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GenericResponseDTO<byte[]> firmarComprobanteElectronico(@RequestBody FirmadorRequestDTO request) {
		GenericResponseDTO<byte[]> response = new GenericResponseDTO<byte[]>();
		try {
			response.setCodigo("0");
			response.setContenido(firmadorBO.firmarComprobanteElectronico(request.getContenidoXML()));
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