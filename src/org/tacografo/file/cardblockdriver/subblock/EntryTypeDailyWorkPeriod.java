/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.Number;


/**
 * 2.51. EntryTypeDailyWorkPeriod
 * 
 * Código para distinguir entre el comienzo y el final cuando se introduce un período diario de trabajo, el lugar y la condición de la entrada.
 *
 * EntryTypeDailyWorkPeriod::= INTEGER
 *
 * Begin, related time = card insertion time or time of entry (0),
 *
 * End, related time = card withdrawal time or time of entry (1),
 *
 * Begin, related time manually entered (start time) (2),
 *
 * End, related time manually entered (end of work period) (3),
 *
 * Begin, related time assumed by VU (4),
 *
 * End, related time assumed by VU (5)
 *
 * }
 *
 *Asignación de valor: con arreglo a la norma ISO/IEC8824-1.
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 */

public class EntryTypeDailyWorkPeriod {
	
	private String entryTypeDailyWorkPeriod;
	
	
	public EntryTypeDailyWorkPeriod() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public EntryTypeDailyWorkPeriod(byte[] bytes){
		this.entryTypeDailyWorkPeriod="";
		switch (Number.getShort_8(bytes)){
			case 0: this.entryTypeDailyWorkPeriod="Begin:card insertion time or time of entry ";break; 
			case 1: this.entryTypeDailyWorkPeriod="End:card with drawal time or time of entry ";break;
			case 2: this.entryTypeDailyWorkPeriod="Begin:card withdrawal time or time of entry ";break;
			case 3: this.entryTypeDailyWorkPeriod="End:related time manually entered (end of work period) ";break;
			case 4: this.entryTypeDailyWorkPeriod="Begin:related time assumed by VU";break;
			case 5: this.entryTypeDailyWorkPeriod="related time assumed by VU";break;
			default: this.entryTypeDailyWorkPeriod=String.valueOf(Number.getShort_8(bytes));
		}
	}

	/**
	 * Obtiene un periodo diario de trabajo, lugar y condición de la entrada:
	 * 
	 * Begin, related time = card insertion time or time of entry (0),
	 *
	 * End, related time = card withdrawal time or time of entry (1),
	 *
	 * Begin, related time manually entered (start time) (2),
	 *
	 * End, related time manually entered (end of work period) (3),
	 *
	 * Begin, related time assumed by VU (4),
	 *
	 * End, related time assumed by VU (5)
	 * @return the entryTypeDailyWorkPeriod
	 */
	public String getEntryTypeDailyWorkPeriod() {
		return entryTypeDailyWorkPeriod;
	}

	/**
	 * Asigna un periodo diario de trabajo, lugar y condición de la entrada:
	 *  * 
	 * Begin, related time = card insertion time or time of entry (0),
	 *
	 * End, related time = card withdrawal time or time of entry (1),
	 *
	 * Begin, related time manually entered (start time) (2),
	 *
	 * End, related time manually entered (end of work period) (3),
	 *
	 * Begin, related time assumed by VU (4),
	 *
	 * End, related time assumed by VU (5)
	 * @param entryTypeDailyWorkPeriod the entryTypeDailyWorkPeriod to set
	 */
	public void setEntryTypeDailyWorkPeriod(String entryTypeDailyWorkPeriod) {
		this.entryTypeDailyWorkPeriod = entryTypeDailyWorkPeriod;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntryTypeDailyWorkPeriod [entryTypeDailyWorkPeriod="
				+ entryTypeDailyWorkPeriod + "]";
	}
	

}
