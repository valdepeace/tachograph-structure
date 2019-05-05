/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import com.tachographStructure.helpers.BCDString;

/**
 * 2.46. DailyPresenceCounter
 * 
 * Contador que est� almacenado en una tarjeta de conductor o en una tarjeta del 
 * centro de ensayo y que se incrementa en una unidad por cada d�a civil que se haya insertado la tarjeta en una VU. 
 * Este tipo de datos est� relacionado con los requisitos 199 y 219.
 *
 * DailyPresenceCounter::= BCDString(SIZE(2))
 * 
 * Asignaci�n de valor: n�mero consecutivo con un valor m�ximo de 9999, y que vuelve a comenzar desde 0.
 * La primera vez que se expide la tarjeta, el n�mero se pone a 0.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class DailyPresenceCounter {
	private String dailyPresenceCounter;
	
	
	public DailyPresenceCounter() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public DailyPresenceCounter(byte[] bytes){
		
		this.dailyPresenceCounter= BCDString.BCDtoString(bytes);
	}

	/**
	 * Obtiene  * Contador que est� almacenado en una tarjeta de conductor o en una tarjeta del 
	 * centro de ensayo y que se incrementa en una unidad por cada d�a civil que se haya insertado la tarjeta en una VU.
	 * @return the dailyPresenceCounter
	 */
	public String getDailyPresenceCounter() {
		return dailyPresenceCounter;
	}

	/**
	 * Asigna  * Contador que est� almacenado en una tarjeta de conductor o en una tarjeta del 
	 * centro de ensayo y que se incrementa en una unidad por cada d�a civil que se haya insertado la tarjeta en una VU.
	 * @param dailyPresenceCounter the dailyPresenceCounter to set
	 */
	public void setDailyPresenceCounter(String dailyPresenceCounter) {
		this.dailyPresenceCounter = dailyPresenceCounter;
	}
	
}
