package org.tacografo.tiposdatos;

/**
 * Clase que devuelve un numero contenido a partir de un array de bytes
 * @author Andres Carmona Gil
 * @version 0.0.2
 */


public class Number {

	
	public Number(){
		
	}
	/**
	 * Obtenemos integer a partir de un array de 4 bytes.
	 * @param bytes
	 * @return integer
	 */	
	public static int getInteger_32(byte[] bytes){
		int i= (bytes[0]<<24)&0xff000000|
			       (bytes[1]<<16)&0x00ff0000|
			       (bytes[2]<< 8)&0x0000ff00|
			       (bytes[3]<< 0)&0x000000ff;
		return i;
	}
	/**
	 * Obtenemos integer a partir de un array de 3 bytes.
	 * @param bytes
	 * @return integer
	 */
	public static int getInteger_24(byte[] bytes){
		int i= (bytes[0]<<16)&0xff0000|
			       (bytes[1]<< 8)&0x00ff00|
			       (bytes[2]<< 0)&0x0000ff;
			       
		return i;
	}
	/**
	 * Obtenemos un short a partir de 2 bytes.
	 * @param bytes
	 * @return short
	 */
	public static short getShort_16(byte[] bytes){
		short i=(short) ((bytes[0]<< 8)&0x0000ff00|
			       (bytes[1]<< 0)&0x000000ff);
		return i;
	}
	/**
	 * Obtenemos un short a partir de 1 byte.
	 * @param bytes
	 * @return short
	 */
	public static short getShort_8(byte[] bytes){
		short i=(short) ((bytes[0]<< 0)&0x000000ff);
		return i;
	}
	
	public static int getNumber(byte[] bytes){
		int n=0;
		switch (bytes.length) {
		case 1:
			n=getShort_8(bytes);
			break;
		case 2:
			n=getInteger_24(bytes);
			break;
		case 3:
			n=getInteger_32(bytes);
			break;
		case 4:
			n=getShort_8(bytes);
			break;
		default:
			n=0;
			break;
		}
		return n;
	}
}
