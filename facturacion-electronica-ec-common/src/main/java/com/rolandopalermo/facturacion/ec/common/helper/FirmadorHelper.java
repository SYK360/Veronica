package com.rolandopalermo.facturacion.ec.common.helper;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;
import com.rolandopalermo.facturacion.ec.common.sri.GenericXMLSignature;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.EnumFormatoFirma;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rolando
 */
@Setter
@Getter
@Component
public class FirmadorHelper extends GenericXMLSignature {

    private String rutaDocumentoAFirmar;
    private String rutaDocumentoFirmado;
//    private String rutaArchivoFirma;

//    public FirmadorHelper(String rutaDocumentoAFirmar, String rutaDocumentoFirmado) {
//        this.rutaDocumentoAFirmar = rutaDocumentoAFirmar;
//        this.rutaDocumentoFirmado = rutaDocumentoFirmado;
//    }

    @Override
    protected DataToSign createDataToSign() throws NegocioException {
        DataToSign dataToSign = new DataToSign();
        try {
            dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
            dataToSign.setEsquema(XAdESSchemas.XAdES_132);
            dataToSign.setXMLEncoding("UTF-8");
            dataToSign.setEnveloped(true);
            dataToSign.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "Documento de ejemplo", null, "text/xml", null));
            Document docToSign = getDocument(getRutaDocumentoAFirmar());
            dataToSign.setDocument(docToSign);
        } catch (Exception ex) {
        	throw new NegocioException("No se pudo firmar el documento XML.");
        }
        return dataToSign;
    }

    @Override
    protected String getSignatureFileName() {
        return rutaDocumentoFirmado;
    }

    public void firmar() throws NegocioException {
        execute();
    }
    
}
