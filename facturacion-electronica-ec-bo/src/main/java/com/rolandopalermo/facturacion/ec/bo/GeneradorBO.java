package com.rolandopalermo.facturacion.ec.bo;

import org.springframework.stereotype.Service;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.common.util.ArchivoUtil;
import com.rolandopalermo.facturacion.ec.modelo.ComprobanteElectronico;

@Service
public class GeneradorBO {

	public byte[] generarXMLDocumentoElectronico(ComprobanteElectronico documento) throws NegocioException {
		if (documento == null) {
			throw new NegocioException("El documento XML no tiene una estructura válida.");
		}
		if (documento.getInfoTributaria() == null) {
			throw new NegocioException("La información tributaria no tiene una estructura válida.");
		}
		try {
			return ArchivoUtil.convertirXMLAByteArray(documento);
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			throw new NegocioException("Ocurrió un error al generar el comprobante electrónico.");
		}
	}

}
