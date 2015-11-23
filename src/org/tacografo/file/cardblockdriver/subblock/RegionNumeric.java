/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.OctetString;


/**
 * 2.88. RegionNumeric
 *
 * Referencia numérica a una región perteneciente a un país especificado.
 *
 * RegionNumeric::= OCTET STRING (SIZE(1))
 * 
 * Asignación de valor:
 *
 *'00'H No hay información disponible,
 * España:
 * '01'H Andalucía,
 *'02'H Aragón,
 * '03'H Asturias,
 * '04'H Cantabria,
 * '05'H Cataluña,
 * '06'H Castilla-León,
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
 * '11'H País Vasco
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
			case 0x01: return "Andalucía";
			case 0x02: return "Aragón";
			case 0x03: return "Asturias";
			case 0x04: return "Cantabria";
			case 0x05: return "Cataluña";
			case 0x06: return "Castilla-León";
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
			case 0x11: return "País Vasco";
		}
		return null;
	}
}
