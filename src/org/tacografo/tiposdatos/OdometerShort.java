/**
 * 
 */
package org.tacografo.tiposdatos;



/**
 * 2.82. OdometerShort
 * 
 * Lectura del cuentakilómetros del vehículo en forma abreviada.
 *
 * OdometerShort::= INTEGER(0..224-1)
 *
 * Asignación de valor: número binario sin signo. Valor en km en el intervalo operativo de 0 a 9999999 km.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */

public class OdometerShort {
	@SuppressWarnings("unused")
	private Integer OdometerShort; 
	
	/**
	 * Obtiene integer que corresponde a los kilometros.
	 * @param bytes
	 * @return integer
	 */
	static public int getOdometerShort(byte[] bytes){

		int num;
		num=Number.getInteger_24(bytes);
		return num;
		
	}
}
