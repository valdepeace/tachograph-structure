/**
 * 
 */
package org.tacografo.tiposdatos;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 	BCD = binary code decimal el cual representa por cada byte un
 *         numero entre 0-9 por cada 4 bit por lo tanto representa por cada byte
 *         dos numeros
 */
public class BCDString {
	/*
	 * metodos copiados de https://gist.github.com/neuro-sys/953548
	 */
	/**
	 * Obtiene 2 numeros por cada un byte (4 bits + 4 bits)
	 * (referencia https://gist.github.com/neuro-sys/953548)
	 * @param bcd
	 * @return String
	 */
	public static String BCDtoString(byte bcd) {
		StringBuffer sb = new StringBuffer();

		// elimino 4 ultimos bits
		byte high = (byte) (bcd & 0xf0);
		// desplazo los 4 bits hacia la derecha
		high >>>= (byte) 4;		
		// pongo a cero los cuatro primeros bits
		high = (byte) (high & 0x0f);
		// elimino los 4 primeros bits
		byte low = (byte) (bcd & 0x0f);

		sb.append(high);
		sb.append(low);

		return sb.toString();
	}

	/*
	 * metodos copiados de https://gist.github.com/neuro-sys/953548
	 */
	/**
	 * Obtiene n numeros del array de bytes, donde cada byte representa 2 numeros(4 bits + 4 bits)
	 * @param bcd
	 * @return String
	 */
	public static String BCDtoString(byte[] bcd) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < bcd.length; i++) {
			sb.append(BCDtoString(bcd[i]));
		}

		return sb.toString();
	}
}
