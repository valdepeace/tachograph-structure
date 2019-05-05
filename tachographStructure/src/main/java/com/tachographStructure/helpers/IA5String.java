/**
 * 
 */
package com.tachographStructure.helpers;

import java.io.UnsupportedEncodingException;


/**
 * ITU-T recommendation T.50 specifies the International Reference Alphabet (IRA, formerly International Alphabet No. 5, IA5), 
 * a character set encoding. ASCII is the U.S. variant of that character set.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class IA5String {
	
	private String iA5String="";

	
	@SuppressWarnings("unused")
	public IA5String(byte[] bytes) throws UnsupportedEncodingException{
		int num;
		
		for (byte bite:bytes){
			if(bite>0x20 && bite!=0xff && bite!='?' ){				
				this.iA5String+=String.format("%c", bite);	
			}
						
		}
	}
	
	
	/**
	 * Obtiene caracter de un byte.
	 * @return the iA5String
	 */
	public String getiA5String() {
		return iA5String;
	}

	/**
	 * Asigna caracter.
	 * @param iA5String the iA5String to set
	 */
	public void setiA5String(String iA5String) {
		this.iA5String = iA5String;
	}
	
	
	
}
