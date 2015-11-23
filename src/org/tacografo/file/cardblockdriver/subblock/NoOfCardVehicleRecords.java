/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.Number;

/**
 * 2.76. NoOfCardVehicleRecords
 * Número de registros sobre vehículos usados que puede almacenar una tarjeta de conductor o una tarjeta del centro de
 * ensayo.
 * NoOfCardVehicleRecords ::= INTEGER(0..216-1)
 * Asignación de valor: min->84 max->200
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class NoOfCardVehicleRecords {
	
	private short noOfCardVehicleRecords;

	
	public NoOfCardVehicleRecords() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public NoOfCardVehicleRecords(byte[] datos) {
		this.noOfCardVehicleRecords=Number.getShort_16(datos);
	}

	/**
	 * Obtiene Número de registros sobre vehículos usados que puede almacenar una tarjeta de conductor o una tarjeta del centro de
	 * ensayo.
	 * @return the noOfCardVehicleRecords
	 */
	public short getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Asigna Número de registros sobre vehículos usados que puede almacenar una tarjeta de conductor o una tarjeta del centro de
	 * ensayo.
	 * @param noOfCardVehicleRecords the noOfCardVehicleRecords to set
	 */
	public void setNoOfCardVehicleRecords(short noOfCardVehicleRecords) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
	}

	
}
