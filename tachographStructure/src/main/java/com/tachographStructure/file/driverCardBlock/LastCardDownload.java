/**
 * 
 */
package com.tachographStructure.file.driverCardBlock;

import java.util.Date;
import com.tachographStructure.file.Block;
import com.tachographStructure.helpers.RealTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Fecha y hora, almacenadas en la tarjeta del conductor, de la �ltima transferencia de los datos de la tarjeta (para fines distintos
 * de los de control). Esta fecha puede ser actualizada por una VU o por cualquier lector de tarjetas.
 * LastCardDownload ::= TimeReal
 * Asignaci�n de valor: no hay m�s especificaciones.
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class LastCardDownload extends Block implements CardBlock {

		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
		private Date lastcarddownload;
		
		/**
		 * 
		 */
		public LastCardDownload() {	}


		/**
		 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
		 * segun  el tipo de propiedad.
		 * @param datos array de bytes
		 */
		public LastCardDownload(byte[] datos) {
			this.lastcarddownload= RealTime.getRealTime(datos);
		}

		
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "LastCardDownload [lastcarddownload=" + lastcarddownload
					+ "]";
		}



		/**
		 * Obtiene Fecha y hora, almacenadas en la tarjeta del conductor, de la �ltima transferencia de los datos de la tarjeta (para fines distintos
		 * de los de control).
		 * @return the lastcarddownload
		 */
		public Date getLastcarddownload() {
			return lastcarddownload;
		}

		/**
		 * Asigna Fecha y hora, almacenadas en la tarjeta del conductor, de la �ltima transferencia de los datos de la tarjeta (para fines distintos
		 * de los de control).
		 * @param lastcarddownload the lastcarddownload to set
		 */
		public void setLastcarddownload(Date lastcarddownload) {
			this.lastcarddownload = lastcarddownload;
		}
		
}
