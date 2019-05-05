/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import com.tachographStructure.helpers.OctetString;


/**
 * 2.88. RegionNumeric
 *
 * Referencia num�rica a una regi�n perteneciente a un pa�s especificado.
 *
 * RegionNumeric::= OCTET STRING (SIZE(1))
 * 
 * Asignaci�n de valor:
 *
 *'00'H No hay informaci�n disponible,
 * Espa�a:
 * '01'H Andaluc�a,
 *'02'H Arag�n,
 * '03'H Asturias,
 * '04'H Cantabria,
 * '05'H Catalu�a,
 * '06'H Castilla-Le�n,
 * '07'H Castilla-La-Mancha,
 * '08'H Valencia,
 * '09'H Extremadura,
 * '0A'H Galicia,
 * '0B'H Baleares, 
 * '0C'H Canarias,
 * '0D'H La Rioja,
 * '0E'H Madrid,
 * '0F'H Murcia,
 * '10'H Navarra,
 * '11'H Pa�s Vasco
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */

public class RegionNumeric {
	
	
	@SuppressWarnings("unused")
	private OctetString regionNumeric;
	
	

	public RegionNumeric() {}


	/**
	 * Obtiene un string de la region segun la referencia numerica pasada como parametro
	 * @param num
	 * @return String region
	 */
	public static String getRegionNumeric(int num){
		switch(num){
			case 0x01: return "Andaluc�a";
			case 0x02: return "Arag�n";
			case 0x03: return "Asturias";
			case 0x04: return "Cantabria";
			case 0x05: return "Catalu�a";
			case 0x06: return "Castilla-Le�n";
			case 0x07: return "Castilla-La-Mancha";
			case 0x08: return "Valencia";
			case 0x09: return "Extremadura";
			case 0x0A: return "Galicia";
			case 0x0B: return "Baleares"; 
			case 0x0C: return "Canarias";
			case 0x0D: return "La Rioja";
			case 0x0E: return "Madrid";
			case 0x0F: return "Murcia";
			case 0x10: return "Navarra";
			case 0x11: return "Pa�s Vasco";
		}
		return null;
	}
}
