/**
 * 
 */
package org.tacografo.tiposdatos;

import java.nio.charset.Charset;

/**
 * Cadena de caracteres ASCII
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */

public class OctetString {
	
	private String octetString="";
	
	protected OctetString() {
	}
	/**
	 * Constructor que asigna el byte que le corresponda a la propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param b
	 */	
	public OctetString(byte b){
		if (Character.isValidCodePoint(b))
		this.octetString=String.format("%c", b);	
	}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param  b
	 */
	public OctetString (byte[] b){
		
		for (byte bite:b){
			String hex = Integer.toString( ( bite & 0xff ) + 0x100, 16).substring( 1 );
			int num=Integer.parseInt(hex,16);
			//ver tabla utf8
			if (num>=0x30 && num<=0x7a){
				this.octetString+=String.format("%c", bite);
			}else{
				this.octetString+=" " + Integer.toString( ( bite & 0xff ) + 0x100, 16).substring( 1 );
			}			
		}
	}
	
	/**
	 * Devuelve el array de bytes segun "ISO-8859-"+codePage pasado.
	 * @param datos
	 * @param codePage
	 */
	public OctetString(byte[] datos, short codePage) {
		if (codePage>0 && codePage<16){
			Charset cs=Charset.forName("ISO-8859-"+codePage);
			String so=new String(datos,cs);
			this.octetString=so.toString();
		}
	}

	/**
	 * Obtiene el octetstring  
	 * @return the octetString
	 */
	public String getOctetString() {
		return octetString;
	}

	/**
	 * Asigna el octetstring
	 * @param octetString the octetString to set
	 */
	public void setOctetString(String octetString) {
		this.octetString = octetString;
	}
	
	public static String getHexString(byte[] b) throws Exception {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
}
