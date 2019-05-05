/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import com.tachographStructure.helpers.OctetString;

/**
 * 
 * 2.29. CardStructureVersion
 * 
 * C�digo que indica la versi�n de la estructura empleada en una tarjeta de tac�grafo.
 * 
 * CardStructureVersion ::= OCTET STRING (SIZE(2))
 * Asignaci�n de valor: �aabb�H:
 * �aa�H �ndice para cambios de la estructura,
 * �bb�H �ndice para cambios relativos al uso de los elementos de datos definidos para la estructura que viene dada
 * por el byte alto.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class CardStructureVersion {

	private String cardStructureVersion;
	
	public CardStructureVersion(){}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardStructureVersion(byte[] datos) {
	
		OctetString os=new OctetString(datos);
		
		this.cardStructureVersion=os.getOctetString();
	}

	/**
	 * Obtiene el c�digo que indica la versi�n de la estructura empleada en una tarjeta de tac�grafo.
	 * @return the cardStructureVersion
	 */
	public String getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Asigna el c�digo que indica la versi�n de la estructura empleada en una tarjeta de tac�grafo.
	 * @param cardStructureVersion the cardStructureVersion to set
	 */
	public void setCardStructureVersion(String cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}

	
}
