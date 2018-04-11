package com.rolandopalermo.facturacion.ec.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.modelo.DocumentoElectronico;

public class ArchivoUtil {
	
	public static byte[] convertirXMLAByteArray(DocumentoElectronico doc) throws NegocioException {
		try {
			String nombreArchivo = doc.getInfoTributaria().getClaveAcceso() + UUID.randomUUID().toString();
			File temp = File.createTempFile(nombreArchivo, ".xml");
			nombreArchivo = temp.getAbsolutePath();
			MarshallerUtil.marshall(doc, nombreArchivo);
			Path path = Paths.get(nombreArchivo);
			byte[] data = Files.readAllBytes(path);
			if(temp.delete()) {
				//loggear
			}
			return data;
		} catch (Exception e) {
			throw new NegocioException("No se pudo realizar el proceso de Marshalling.");
		}
		
	}

	public static byte[] convertirArchivoAByteArray(File file) throws IOException {
		byte[] buffer = new byte[(int) file.length()];
		InputStream ios = null;
		try {
			ios = new FileInputStream(file);
			if (ios.read(buffer) == -1) {
				throw new IOException("EOF reached while trying to read the whole file");
			}
		} catch (Exception ex) {
			buffer = null;
		} finally {
			try {
				if (ios != null) {
					ios.close();
				}
			} catch (IOException e) {
			}
		}
		return buffer;
	}

}
