/**
 * 
 */
package com.tachographStructure.file.vuBlocks.subBlocks;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 
 * 2.4.   CalibrationPurpose
 * 
 * Código que explica por qué se registró un conjunto de parámetros de calibrado. Este tipo de datos está relacionado con los requisitos 097 y 098.
 * CalibrationPurpose ::= OCTET STRING (SIZE(1))
 * Asignación de valor:
 * ′00′H valor reservado,
 * ′01′H activación: registro de los parámetros de calibrado conocidos en el momento de la activación de la VU,
 * ′02′H primera instalación: primer calibrado de la VU después de su activación,
 * ′03′H instalación: primer calibrado de la VU en el vehículo actual,
 * ′04′H control periódico.
 *
 */
public class CalibrationPurpose {

	
	public static final String getCalibrationPurpose(byte[] bytes){
		String str="";
		switch (bytes[0]) {
		case 0x00:str="valor reservado";break;
		case 0x01:str="activación: registro de los parámetros de calibrado conocidos en el momento de la activación de la VU";break;
		case 0x02:str="primera instalación: primer calibrado de la VU después de su activación";break;
		case 0x03:str="instalación: primer calibrado de la VU en el vehículo actual";break;
		case 0x04:str="control periódico";break;
		default:
			break;
		}
		return str;
	}
}
