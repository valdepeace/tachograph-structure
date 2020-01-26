/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import com.tachographStructure.helpers.OctetString;

/**
 *
 * The certificate of a public key issued by a Certification Authority.
 * Generation 1:
 * Value assignment: digital signature with partial recovery of a CertificateContent according to Appendix 11
 * common security mechanisms: Signature (128 bytes) || Public Key remainder (58 bytes) || Certification Authority
 * Reference (8 bytes).
 * Generation 2:
 * Value assignment: See Appendix 11
 *
 * @author Andres Carmona Gil
 * @version 0.0.2
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
