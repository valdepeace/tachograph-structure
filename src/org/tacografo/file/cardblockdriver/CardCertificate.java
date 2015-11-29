package org.tacografo.file.cardblockdriver;

import org.tacografo.file.Block;
import org.tacografo.file.cardblockdriver.subblock.Certificate;


/**
 * 
 * 2.8. CardCertificate
 * Certificado de la clave p�blica de una tarjeta.
 * CardCertificate ::= Certificate
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */


public class CardCertificate extends Block implements CardBlock {
	
	
	private Certificate certificate;
	
	
	public CardCertificate() {
		
	}
	/**
	 * Constructor encargado de crear el certificado a partir de los bytes de datos
	 * pasado
	 * @param datos
	 */
	public CardCertificate(byte[] datos){
		this.certificate= new Certificate(datos);
	}
	
	public String toString(){
		String cadena;
		cadena="certificado de la tarjeta"+this.certificate.getCertificate().getOctetString();
		return cadena;
	}

	/**
	 * Devuelve el certificado
	 * @return the certificate
	 */
	public Certificate getCertificate() {
		return certificate;
	}

	/**
	 * Asignamos el certificado
	 * @param certificate the certificate to set
	 */
	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

}
