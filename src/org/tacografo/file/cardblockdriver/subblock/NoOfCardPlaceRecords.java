/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

/**
 * 2.75. NoOfCardPlaceRecords
 * Número de registros de lugares que puede almacenar una tarjeta de conductor o una tarjeta del centro de ensayo.
 * NoOfCardPlaceRecords ::= INTEGER(0..255)
 * Asignación de valor: min->84 max->112
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class NoOfCardPlaceRecords {
	
	private short noOfCardPlaceRecords;


	public NoOfCardPlaceRecords() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public NoOfCardPlaceRecords(byte[] datos) {
		this.noOfCardPlaceRecords=(short) datos[0];
	}


	/**
	 * Obtiene el número de registros de lugares que puede almacenar una tarjeta de conductor o una tarjeta del centro de ensayo.
	 * @return the noOfCardPlaceRecords
	 */
	public short getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}


	/**
	 * Asigna el número de registros de lugares que puede almacenar una tarjeta de conductor o una tarjeta del centro de ensayo.
	 * @param noOfCardPlaceRecords the noOfCardPlaceRecords to set
	 */
	public void setNoOfCardPlaceRecords(short noOfCardPlaceRecords) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	}
	
}
	
