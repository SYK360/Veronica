package com.rolandopalermo.facturacion.ec.common.sri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.rolandopalermo.facturacion.ec.common.exception.NegocioException;

import es.mityc.firmaJava.libreria.utilidades.UtilidadTratarNodo;
import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.FirmaXML;
import es.mityc.javasign.pkstore.CertStoreException;
import es.mityc.javasign.pkstore.IPKStoreManager;
import es.mityc.javasign.pkstore.keystore.KSStore;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Clase base que deberían extender los diferentes ejemplos para realizar firmas
 * XML.
 * </p>
 * 
 * @author Ministerio de Industria, Turismo y Comercio
 * @version 1.0
 */
@Setter
@Getter
public abstract class GenericXMLSignature {

    /**
     * <p>
     * Almacén PKCS12 con el que se desea realizar la firma
     * </p>
     */
//    public final static String PKCS12_RESOURCE = "/examples/usr0052.p12";
    private String pkcs12_resource = "";
    /**
     * <p>
     * Constraseña de acceso a la clave privada del usuario
     * </p>
     */
    public String pkcs12_pasword = "";
    /**
     * <p>
     * Directorio donde se almacenará el resultado de la firma
     * </p>
     */
//    public final static String OUTPUT_DIRECTORY = "";

    /**
     * <p>
     * Ejecución del ejemplo. La ejecución consistirá en la firma de los datos
     * creados por el método abstracto <code>createDataToSign</code> mediante el
     * certificado declarado en la constante <code>PKCS12_FILE</code>. El
     * resultado del proceso de firma será almacenado en un fichero XML en el
     * directorio correspondiente a la constante <code>OUTPUT_DIRECTORY</code>
     * del usuario bajo el nombre devuelto por el método abstracto
     * <code>getSignFileName</code>
     * </p>
     */
    protected void execute() throws NegocioException {
        IPKStoreManager storeManager = getPKStoreManager();
        if (storeManager == null) {
            throw new NegocioException("No se pudo obtener el gestor de claves.");
        }
        X509Certificate certificate = getFirstCertificate(storeManager);
        if (certificate == null) {
        	throw new NegocioException("No se pudo obtener el certificado para firmar.");
        }
        PrivateKey privateKey;
        try {
            privateKey = storeManager.getPrivateKey(certificate);
        } catch (CertStoreException e) {
        	throw new NegocioException("No se pudo obtener la clave privada asociada al certificado.");
		}
        Provider provider = storeManager.getProvider(certificate);
        DataToSign dataToSign = createDataToSign();
        Document docSigned = null;
        try {
            FirmaXML firma = createFirmaXML();
            Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
            docSigned = (Document) res[0];
        } catch (Exception ex) {
        	throw new NegocioException("No se pudo obtener objeto encargado de realizar la firma.");
        }
        saveDocumentToFile(docSigned, getSignatureFileName());
    }

    /**
     * <p>
     * Crea el objeto DataToSign que contiene toda la información de la firma
     * que se desea realizar. Todas las implementaciones deberán proporcionar
     * una implementación de este método
     * </p>
     * 
     * @return El objeto DataToSign que contiene toda la información de la firma
     *         a realizar
     */
    protected abstract DataToSign createDataToSign() throws NegocioException;

    /**
     * <p>
     * Nombre del fichero donde se desea guardar la firma generada. Todas las
     * implementaciones deberán proporcionar este nombre.
     * </p>
     * 
     * @return El nombre donde se desea guardar la firma generada
     */
    protected abstract String getSignatureFileName();

    /**
     * <p>
     * Crea el objeto <code>FirmaXML</code> con las configuraciones necesarias
     * que se encargará de realizar la firma del documento.
     * </p>
     * <p>
     * En el caso más simple no es necesaria ninguna configuración específica.
     * En otros casos podría ser necesario por lo que las implementaciones
     * concretas de las diferentes firmas deberían sobreescribir este método
     * (por ejemplo para añadir una autoridad de sello de tiempo en aquellas
     * firmas en las que sea necesario)
     * <p>
     * 
     * 
     * @return firmaXML Objeto <code>FirmaXML</code> configurado listo para
     *         usarse
     */
    protected FirmaXML createFirmaXML() {
        return new FirmaXML();
    }

    /**
     * <p>
     * Escribe el documento a un fichero.
     * </p>
     * 
     * @param document
     *            El documento a imprmir
     * @param pathfile
     *            El path del fichero donde se quiere escribir.
     */
    private void saveDocumentToFile(Document document, String pathfile) throws NegocioException {
        try {
            FileOutputStream fos = new FileOutputStream(pathfile);
            UtilidadTratarNodo.saveDocumentToOutputStream(document, fos, true);
        } catch (FileNotFoundException e) {
        	throw new NegocioException("No se pudo guardar el documento firmado.");
        }
    }

    /**
     * <p>
     * Escribe el documento a un fichero. Esta implementacion es insegura ya que
     * dependiendo del gestor de transformadas el contenido podría ser alterado,
     * con lo que el XML escrito no sería correcto desde el punto de vista de
     * validez de la firma.
     * </p>
     * 
     * @param document
     *            El documento a imprmir
     * @param pathfile
     *            El path del fichero donde se quiere escribir.
     */
    @SuppressWarnings("unused")
    private void saveDocumentToFileUnsafeMode(Document document, String pathfile) throws NegocioException {
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer serializer;
        try {
            serializer = tfactory.newTransformer();
            serializer.transform(new DOMSource(document), new StreamResult(new File(pathfile)));
        } catch (TransformerException e) {
        	throw new NegocioException("No se pudo guardar el documento firmado.");
        }
    }

    /**
     * <p>
     * Devuelve el <code>Document</code> correspondiente al
     * <code>resource</code> pasado como parámetro
     * </p>
     * 
     * @param resource
     *            El recurso que se desea obtener
     * @return El <code>Document</code> asociado al <code>resource</code>
     */
    protected Document getDocument(String resource) throws NegocioException {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try {
            doc = dbf.newDocumentBuilder().parse(new File(resource));
        } catch (Exception ex) {
        	throw new NegocioException("No se pudo procesar el documento.");
        }
        return doc;
    }

    /**
     * <p>
     * Devuelve el contenido del documento XML
     * correspondiente al <code>resource</code> pasado como parámetro
     * </p> como un <code>String</code>
     * 
     * @param resource
     *            El recurso que se desea obtener
     * @return El contenido del documento XML como un <code>String</code>
     */
    protected String getDocumentAsString(String resource) throws NegocioException {
        Document doc = getDocument(resource);
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer serializer;
        StringWriter stringWriter = new StringWriter();
        try {
            serializer = tfactory.newTransformer();
            serializer.transform(new DOMSource(doc), new StreamResult(stringWriter));
        } catch (TransformerException e) {
        	throw new NegocioException("No se pudo obtener contenido XML del documento.");
        }

        return stringWriter.toString();
    }

    /**
     * <p>
     * Devuelve el gestor de claves que se va a utilizar
     * </p>
     * 
     * @return El gestor de claves que se va a utilizar</p>
     */
    private IPKStoreManager getPKStoreManager() throws NegocioException {
        IPKStoreManager storeManager = null;
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new java.io.FileInputStream(pkcs12_resource), pkcs12_pasword.toCharArray());
            storeManager = new KSStore(ks, new PassStoreKS(pkcs12_pasword));
        } catch (Exception ex) {
        	ex.printStackTrace();
        	throw new NegocioException("No se pudo procesar el documento.");
        }
        return storeManager;
    }

    /**
     * <p>
     * Recupera el primero de los certificados del almacén.
     * </p>
     * 
     * @param storeManager
     *            Interfaz de acceso al almacén
     * @return Primer certificado disponible en el almacén
     */
    private X509Certificate getFirstCertificate(
            final IPKStoreManager storeManager) throws NegocioException {
        List<X509Certificate> certs = null;
        try {
            certs = storeManager.getSignCertificates();
        } catch (CertStoreException ex) {
        	throw new NegocioException("No se pudo obtener listado de certificados.");
        }
        if ((certs == null) || (certs.size() == 0)) {
        	throw new NegocioException("La lista de certificados se encuentra vacía.");
        }

        X509Certificate certificate = certs.get(0);
        return certificate;
    }
}