/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.Number;

/**
 * 2.6. CardActivityLengthRange
 * N�mero de bytes disponibles en una tarjeta de conductor o en una tarjeta del centro de ensayo para almacenar registros
 * sobre las actividades del conductor.
 * 
 * CardActivityLengthRange ::= INTEGER(0..2^16-1)
 * 
 * Asignaci�n de valor: min->5544 bytes(28 d�as, 93 cambios de actividad cada d�a)
 * 						max->13776 bytes(28 d�as, 240 cambios de actividad cada d�a)
 * @author Negrero
 *
 */
public class CardActivityLengthRange {
	
	private int cardActivityLengthRange;


	
	public CardActivityLengthRange() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardActivityLengthRange(byte[] datos) {
		this.cardActivityLengthRange= Number.getShort_16(datos);
	}


	/**
	 * Obtiene n�mero de bytes disponibles en una tarjeta de conductor o en una tarjeta del centro de ensayo para almacenar registros
	 * sobre las actividades del conductor.
	 * @return the cardActivityLengthRange
	 */
	public int getCardActivityLengthRange() {
		return cardActivityLengthRange;
	}


	/**
	 * Asigna n�mero de bytes disponibles en una tarjeta de conductor o en una tarjeta del centro de ensayo para almacenar registros
	 * sobre las actividades del conductor.
	 * @param cardActivityLengthRange the cardActivityLengthRange to set
	 */
	public void setCardActivityLengthRange(short cardActivityLengthRange) {
		this.cardActivityLengthRange = cardActivityLengthRange;
	}		

	
}
