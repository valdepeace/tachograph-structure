/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.Arrays;

import org.tacografo.tiposdatos.Number;

/**
 * 
 * 2.103. SpecificConditionType
 * 
 * Código que identifica una condición específica (requisitos 050b, 105a, 212a y 230a).
 * 
 * SpecificConditionType::= INTEGER(0..255)
 *
 * Asignación de valor:
 * 
 * '00'H RFU
 * 
 * '01'H Fuera de ámbito - Comienzo
 * 
 * '02'H Fuera de ámbito - Final
 * 
 * '03'H Puente/Paso a nivel
 * 
 * '04'H.. 'FF'H RFU
 * 
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class SpecificConditionType {
	
	private Integer specificConditionType;
	

	public SpecificConditionType() {}
	
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public SpecificConditionType(byte[] bytes){
		this.specificConditionType=(int) Number.getShort_8(Arrays.copyOfRange(bytes, 0, 1));
	}
	/**
	 * Obtenemos una cadena con el tipo de condicion.
	 * @return the SpecificConditionType
	 */
	public String getSpecificConditionType(){
		String cadena="";
		switch(this.specificConditionType){
			case 00: cadena="RFU"; break;
			case 01: cadena="Fuera de ámbito-Comienzo";break;
			case 02: cadena="Fuera de ámbito-Final";break;
			case 03: cadena="Puente/Paso a nivel";break;
			default: cadena="RFU";
		}
		return cadena;
	}
	
	
}
