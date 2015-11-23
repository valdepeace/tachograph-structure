/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.Number;

/**
 * 2.6. CardActivityLengthRange
 * Número de bytes disponibles en una tarjeta de conductor o en una tarjeta del centro de ensayo para almacenar registros
 * sobre las actividades del conductor.
 * 
 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
 * 
 * Asignación de valor: min->5544 bytes(28 días, 93 cambios de actividad cada día)
 * 						max->13776 bytes(28 días, 240 cambios de actividad cada día)
 * @author Negrero
 *
 */
public class CardActivityLengthRange {
	
	private short cardActivityLengthRange;


	
	public CardActivityLengthRange() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardActivityLengthRange(byte[] datos) {
		this.cardActivityLengthRange=Number.getShort_16(datos);
	}


	/**
	 * Obtiene número de bytes disponibles en una tarjeta de conductor o en una tarjeta del centro de ensayo para almacenar registros
	 * sobre las actividades del conductor.
	 * @return the cardActivityLengthRange
	 */
	public short getCardActivityLengthRange() {
		return cardActivityLengthRange;
	}


	/**
	 * Asigna número de bytes disponibles en una tarjeta de conductor o en una tarjeta del centro de ensayo para almacenar registros
	 * sobre las actividades del conductor.
	 * @param cardActivityLengthRange the cardActivityLengthRange to set
	 */
	public void setCardActivityLengthRange(short cardActivityLengthRange) {
		this.cardActivityLengthRange = cardActivityLengthRange;
	}		

	
}
