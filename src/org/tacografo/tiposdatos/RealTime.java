/**
 * 
 */
package org.tacografo.tiposdatos;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
;

/**
 * @author Negrero
 * 
 * 2.110. TimeReal
 *
 * Código para un campo combinado de fecha y hora, donde ambos parámetros se expresan como los segundos transcurridos desde las 00h.00m.00s. del 1 de enero de 1970, tiempo medio de Greenwich.
 *
 * TimeReal{INTEGER:TimeRealRange}::= INTEGER(0..TimeRealRange)
 *
 * Asignación de valor - Alineación de octeto: número de segundos transcurridos a partir de la medianoche del día 1 de enero de 1970, tiempo medio de Greenwich.
 * 
 * La fecha/hora máxima posible es en el año 2106.
 *
 */

public class RealTime {

	
	@SuppressWarnings("unused")
	private Date fecha;
	
	protected RealTime() {
	}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param  bytes
	 */
	public RealTime(byte[] bytes){
		this.fecha=new Date((long) ByteBuffer.wrap(Arrays.copyOfRange(bytes, 61, 65)).getInt()*1000);
	}
	/**
	 * Obtenemos fecha y hora a partir de los bytes pasados.
	 * @param bytes
	 * @return the date
	 */
	public static Date getRealTime(byte[] bytes){
		return new Date((long) ByteBuffer.wrap(bytes).getInt()*1000);
	}
}
