/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import com.tachographStructure.helpers.Number;

/**
 * 2.48. Distance
 * 
 * Una distancia recorrida (resultado de calcular la diferencia en kil�metros entre dos lecturas del 
 * cuentakil�metros del veh�culo).
 *
 * Distance::= INTEGER(0..216-1)
 *
 * Asignaci�n de valor: n�mero binario sin signo. Valor en km en el intervalo operativo de 0 a 9999 km.
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class Distance {
	
	
	private int distance;
	
	
	public Distance() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public Distance(byte[] bytes){
		distance= Number.getShort_16(bytes);
	}

	/**
	 * Obtiene una distancia recorrida.
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Asigna una distancia recorrida.
	 * @param distance the distance to set
	 */
	public void setDistance(short distance) {
		this.distance = distance;
	}
	
	

}
