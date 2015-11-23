/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import org.tacografo.file.cardblockdriver.subblock.Certificate;

/**
 * 
 * 2.68. MemberStateCertificate
 * El certificado de la clave pública de un Estado miembro, expedido por la autoridad de certificación europea.
 * MemberStateCertificate ::= Certificate
 * @author Negrero
 *
 */
public class MemberStateCertificate extends CardBlockDriver implements
		CardBlock {
	
		private Certificate certificate;
		
		
		public MemberStateCertificate() {}
		/**
		 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
		 * segun  el tipo de propiedad.
		 * @param datos array de bytes
		 */
		public MemberStateCertificate(byte[] datos) {
			this.certificate=new Certificate(datos);
		}
		
		public String toString(){
			String cadena="";
			cadena=" certificado member state Certificate:"+this.certificate.getCertificate().getOctetString();
			return cadena;
		}

		/**
		 * Obtiene el certificado de la clave pública de un Estado miembro, expedido por la autoridad de certificación europea.
		 * @return the certificate
		 */
		public Certificate getCertificate() {
			return certificate;
		}

		/**
		 * Asigna el certificado de la clave pública de un Estado miembro, expedido por la autoridad de certificación europea.
		 * @param certificate the certificate to set
		 */
		public void setCertificate(Certificate certificate) {
			this.certificate = certificate;
		}
		

}
