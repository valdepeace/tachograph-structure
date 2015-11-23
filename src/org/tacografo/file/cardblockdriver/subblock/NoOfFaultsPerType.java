/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

/**
 * 
 * 2.80. NoOfFaultsPerType
 * 
 * Número de fallos de cada tipo que puede almacenar una tarjeta.
 * NoOfFaultsPerType ::= INTEGER(0..255)
 * Asignación de valor: min->12 max->24.
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class NoOfFaultsPerType {
	
	private short noOfFaultsPerType;
	
	
	public NoOfFaultsPerType() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public NoOfFaultsPerType(byte[] datos) {
		this.noOfFaultsPerType=(short) datos[0];
	}

	/**
	 * Obtiene el numero de fallos de cada tipo que puede almacenar una tarjeta.
	 * @return the noOfFaultsPerType
	 */
	public short getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}

	/**
	 * Asigna el numero de fallos de cada tipo que puede almacenar una tarjeta.
	 * @param noOfFaultsPerType the noOfFaultsPerType to set
	 */
	public void setNoOfFaultsPerType(short noOfFaultsPerType) {
		this.noOfFaultsPerType = noOfFaultsPerType;
	}

	
}
