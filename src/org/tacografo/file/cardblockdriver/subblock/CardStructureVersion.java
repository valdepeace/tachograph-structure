/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.OctetString;

/**
 * 
 * 2.29. CardStructureVersion
 * 
 * Código que indica la versión de la estructura empleada en una tarjeta de tacógrafo.
 * 
 * CardStructureVersion ::= OCTET STRING (SIZE(2))
 * Asignación de valor: ¡aabb¡H:
 * ¡aa¡H índice para cambios de la estructura,
 * ¡bb¡H índice para cambios relativos al uso de los elementos de datos definidos para la estructura que viene dada
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
	 * Obtiene el código que indica la versión de la estructura empleada en una tarjeta de tacógrafo.
	 * @return the cardStructureVersion
	 */
	public String getCardStructureVersion() {
		return cardStructureVersion;
	}

	/**
	 * Asigna el código que indica la versión de la estructura empleada en una tarjeta de tacógrafo.
	 * @param cardStructureVersion the cardStructureVersion to set
	 */
	public void setCardStructureVersion(String cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}

	
}
