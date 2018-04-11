package com.rolandopalermo.facturacion.ec.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.common.sri.Firmador;

@Service
@PropertySource("classpath:data.properties")
public class FirmadorBO {

	@Value("${pkcs12.ruta.certificado}")
	private String rutaArchivoPkcs12;

	@Value("${pkcs12.password.certificado}")
	private String claveArchivopkcs12;

	public byte[] firmarComprobanteElectronico(byte[] comprobanteElectronico) throws NegocioException {
		if (comprobanteElectronico == null || comprobanteElectronico.length == 0) {
			throw new NegocioException("El documento XML no tiene una estructura válida.");
		}
		try {
			// 1.- Generar archivo temporal con el contenido XML
			String rutaArchivoXML = UUID.randomUUID().toString();
			File temp = File.createTempFile(rutaArchivoXML, ".xml");
			rutaArchivoXML = temp.getAbsolutePath();
			// --
			String rutaArchivoXMLFirmado = UUID.randomUUID().toString();
			File tempFirmado = File.createTempFile(rutaArchivoXML, ".xml");
			rutaArchivoXMLFirmado = tempFirmado.getAbsolutePath();
			// 3.- Guardar datos en archivo xml
			try (FileOutputStream fos = new FileOutputStream(rutaArchivoXML)) {
				fos.write(comprobanteElectronico);
			}
			// 2.- Firmar el archivo xml creado temporalmente
			Firmador firmador = new Firmador(rutaArchivoXML, rutaArchivoXMLFirmado, rutaArchivoPkcs12,
					claveArchivopkcs12);
			firmador.firmar();
			// 4.- Obtener el contenido del archivo XML
			Path path = Paths.get(rutaArchivoXMLFirmado);
			byte[] data = Files.readAllBytes(path);
			if (temp.delete() || tempFirmado.delete()) {
				// loggear
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Ocurrió un error al generar el comprobante electrónico.");
		}
	}

}