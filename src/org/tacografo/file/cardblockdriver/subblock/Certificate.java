/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.OctetString;

/**
 * 
 * 2.32. Certificate
 * El certificado de una clave pública expedido por una autoridad de certificación.
 * 
 * Certificate ::= OCTET STRING (SIZE(194))
 * 
 * Asignación de valor: firma digital con recuperación parcial del contenido del certificado, según lo dispuesto en el
 * 
 * Apéndice 11 Mecanismos de seguridad comunes: firma (128 bytes) || resto de la clave pública (58 bytes) || referencia
 * a la autoridad de certificación (8 bytes).
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class Certificate {
	

	private OctetString certificate;
	
	public Certificate() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public Certificate(byte[] datos) {
		this.certificate=new OctetString(datos);
	}

	/**
	 * Obtiene el certificado de una clave pública expedido por una autoridad de certificación.
	 * @return the certificate
	 */
	public OctetString getCertificate() {
		return certificate;
	}

	/**
	 * Asigna el certificado de una clave pública expedido por una autoridad de certificación.
	 * @param certificate the certificate to set
	 */
	public void setCertificate(OctetString certificate) {
		this.certificate = certificate;
	}
	
}
