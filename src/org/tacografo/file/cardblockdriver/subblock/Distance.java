/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import org.tacografo.tiposdatos.Number;

/**
 * 2.48. Distance
 * 
 * Una distancia recorrida (resultado de calcular la diferencia en kilómetros entre dos lecturas del 
 * cuentakilómetros del vehículo).
 *
 * Distance::= INTEGER(0..216-1)
 *
 * Asignación de valor: número binario sin signo. Valor en km en el intervalo operativo de 0 a 9999 km.
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class Distance {
	
	
	private short distance;
	
	
	public Distance() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public Distance(byte[] bytes){
		distance=Number.getShort_16(bytes);				
	}

	/**
	 * Obtiene una distancia recorrida.
	 * @return the distance
	 */
	public short getDistance() {
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
