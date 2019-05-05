/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import com.tachographStructure.helpers.OctetString;

/**
 * 
 * 2.32. Certificate
 * El certificado de una clave p�blica expedido por una autoridad de certificaci�n.
 * 
 * Certificate ::= OCTET STRING (SIZE(194))
 * 
 * Asignaci�n de valor: firma digital con recuperaci�n parcial del contenido del certificado, seg�n lo dispuesto en el
 * 
 * Ap�ndice 11 Mecanismos de seguridad comunes: firma (128 bytes) || resto de la clave p�blica (58 bytes) || referencia
 * a la autoridad de certificaci�n (8 bytes).
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class Certificate {
	

	private OctetString certificate;

	private byte[] bytes;

	public Certificate() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public Certificate(byte[] datos) {
		this.certificate = new OctetString(datos);
		this.bytes = datos;
	}

	/**
	 * Obtiene el certificado de una clave p�blica expedido por una autoridad de certificaci�n.
	 * @return the certificate
	 */
	public OctetString getCertificate() {
		return certificate;
	}

	/**
	 * Asigna el certificado de una clave p�blica expedido por una autoridad de certificaci�n.
	 * @param certificate the certificate to set
	 */
	public void setCertificate(OctetString certificate) {
		this.certificate = certificate;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
