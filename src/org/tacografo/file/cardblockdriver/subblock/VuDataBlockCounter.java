/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.BCDString;

/**
 * 
 * 2.126. VuDataBlockCounter
 * 
 * Contador, almacenado en una tarjeta, que identifica secuencialmente los ciclos de inserción/extracción de la tarjeta en unidades intravehiculares.
 *
 * VuDataBlockCounter::= BCDString(SIZE(2))
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class VuDataBlockCounter {
	private String vuDataBlockCounter;
	
	public VuDataBlockCounter(){}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public VuDataBlockCounter(byte[] datos){
		this.vuDataBlockCounter=BCDString.BCDtoString(datos).replace("0", "");
	}

	/**
	 * Obtiene el contador almacenado en una tarjeta.
	 * @return the vuDataBlockCounter
	 */
	public String getVuDataBlockCounter() {
		return vuDataBlockCounter;
	}

	/**
	 * Asigna el contador almacenado en una tarjeta.
	 * @param vuDataBlockCounter the vuDataBlockCounter to set
	 */
	public void setVuDataBlockCounter(String vuDataBlockCounter) {
		this.vuDataBlockCounter = vuDataBlockCounter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuDataBlockCounter [vuDataBlockCounter=" + vuDataBlockCounter
				+ "]";
	}
	
}
