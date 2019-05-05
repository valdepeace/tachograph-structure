
package com.tachographStructure.file.driverCardBlock.subBlocks;

/**
 * 
 * 2.79. NoOfEventsPerType
 * N�mero de incidentes de cada tipo que puede almacenar una tarjeta.
 * NoOfEventsPerType ::= INTEGER(0..255)
 * Asignaci�n de valor: min->6 max->12
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class NoOfEventsPerType {
	
	private short noOfEventsPerType;

	
	public NoOfEventsPerType() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public NoOfEventsPerType(byte[] datos) {
		this.noOfEventsPerType= (short) datos[0];
	}

	/**
	 * Obtiene n�mero de incidentes de cada tipo que puede almacenar una tarjeta.
	 * @return the noOfEventsPerType
	 */
	public short getNoOfEventsPerType() {
		return noOfEventsPerType;
	}

	/**
	 * Asigna n�mero de incidentes de cada tipo que puede almacenar una tarjeta.
	 * @param noOfEventsPerType the noOfEventsPerType to set
	 */
	public void setNoOfEventsPerType(short noOfEventsPerType) {
		this.noOfEventsPerType = noOfEventsPerType;
	}
	

}
