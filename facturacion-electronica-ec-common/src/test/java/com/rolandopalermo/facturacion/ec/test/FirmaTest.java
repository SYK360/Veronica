package com.rolandopalermo.facturacion.ec.test;

import org.junit.Test;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.common.helper.FirmadorHelper;

public class FirmaTest {

	@Test
	public void test() throws NegocioException {
		FirmadorHelper firmador = new FirmadorHelper();
		firmador.setRutaDocumentoAFirmar("factura-dummy.xml");
		firmador.setRutaDocumentoFirmado("factura-signed.xml");
		firmador.setPkcs12_resource("D:\\Privado\\1791261151001.p12");
		firmador.setPkcs12_pasword("MAte2910");
		firmador.firmar();
	}

}