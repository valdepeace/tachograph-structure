/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import java.io.UnsupportedEncodingException;

import com.tachographStructure.helpers.IA5String;

/**
 * 
 * 2.64. Language
 *
 * C�digo que identifica un idioma.
 * 
 * Language::= IA5String(SIZE(2))
 * 
 * Asignaci�n de valor: codificaci�n mediante dos letras en min�sculas con arreglo a la norma ISO 639:
 * 
 *
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 *
 */
public class Language {
	
	

	private String language;
	
	
	public Language() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public Language(byte[] bytes) throws UnsupportedEncodingException {
		IA5String ia=new IA5String(bytes);
		this.language=ia.getiA5String();
		
	}

	/**
	 * Obtiene c�digo que identifica un idioma.
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Asigna c�digo que identifica un idioma.
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

}
