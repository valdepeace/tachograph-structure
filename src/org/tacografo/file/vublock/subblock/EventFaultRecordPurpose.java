/**
 * 
 */
package org.tacografo.file.vublock.subblock;

/**
 * @author Andres Carmona Gil
 * version 0.0.1
 *
 * 2.55.   EventFaultRecordPurpose
 * Código que explica por qué se ha registrado un incidente o fallo.
 * EventFaultRecordPurpose ::= OCTET STRING (SIZE(1))
 * Asignación de valor:
 * ′00′H uno de los 10 incidentes o fallos más recientes (o de los 10 últimos)
 * ′01′H el incidente de más duración ocurrido en uno de los 10 últimos días en que se hayan producido incidentes de este tipo
 * ′02′H uno de los 5 incidentes de más duración ocurridos en los últimos 365 días
 * ′03′H el último incidente ocurrido en uno de los 10 últimos días en que se hayan producido incidentes de este tipo
 * ′04′H el incidente más grave en uno de los últimos días en que se hayan producido incidentes de este tipo
 * ′05′H uno de los 5 incidentes más graves ocurridos en los últimos 365 días
 * ′06′H el primer incidente o fallo ocurrido tras el último calibrado
 * ′07′H un incidente o fallo activo/en curso
 * ′08′H .. ′7F′H RFU
 * ′80′H .. ′FF′H específicos del fabricante
 */
public class EventFaultRecordPurpose {

	public EventFaultRecordPurpose(){
		
	}
	
	public static final String getEventFaultRecordPurpose(byte bite){
		String str="";
		switch (bite) {
		case 0x00:str="uno de los 10 incidentes o fallos más recientes (o de los 10 últimos)";break;
		case 0x01:str="el incidente de más duración ocurrido en uno de los 10 últimos días en que se hayan producido incidentes de este tipo";break;
		case 0x02:str=" uno de los 5 incidentes de más duración ocurridos en los últimos 365 días";break;
		case 0x03:str="el último incidente ocurrido en uno de los 10 últimos días en que se hayan producido incidentes de este tipo";break;
		case 0x04:str="el incidente más grave en uno de los últimos días en que se hayan producido incidentes de este tipo";break;
		case 0x05:str="uno de los 5 incidentes más graves ocurridos en los últimos 365 días";break;
		case 0x06:str="el primer incidente o fallo ocurrido tras el último calibrado";break;
		case 0x07:str="un incidente o fallo activo/en curso";break;
		default:
			str="RFU";
			break;
		}
		return str;
	}
	
}
