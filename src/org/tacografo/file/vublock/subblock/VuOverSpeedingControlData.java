package org.tacografo.file.vublock.subblock;

import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.vublock.Sizes;
import org.tacografo.tiposdatos.RealTime;
import org.tacografo.tiposdatos.Number;

/**
 * 
 * @author Andres Carmona Gil
 * 
 * 2.139.   VuOverSpeedingControlData
 * 
 * Información almacenada en una unidad intravehicular y relativa a incidentes de exceso de velocidad ocurridos desde el último control del exceso de velocidad (requisito 095).
 * VuOverSpeedingControlData ::= SEQUENCE {
 * lastOverspeedControlTime TimeReal,
 * firstOverspeedSince TimeReal,
 * numberOfOverspeedSince OverspeedNumber
 * }
 * lastOverspeedControlTime es la fecha y la hora del último control del exceso de velocidad.
 * firstOverspeedSince es la fecha y la hora del primer exceso de velocidad ocurrido tras este control.
 * numberOfOverspeedSince es el número de incidentes de exceso de velocidad ocurridos después del último control del exceso de velocidad.
 * 
 * 2.83.   OverspeedNumber
 * Número de incidentes de exceso de velocidad ocurridos desde el último control del exceso de velocidad.
 * OverspeedNumber ::= INTEGER(0..255)
 * Asignación de valor: 0 significa que no se ha producido ningún incidente de exceso de velocidad desde el último control,
 * 1 significa que se ha producido un incidente de exceso de velocidad desde el último control … 255 significa que se han producido
 * 255 o más incidentes de exceso de velocidad desde el último control.
 *
 */

public class VuOverSpeedingControlData {
	
	private Date lastOverspeedControlTime;
	private Date firstOverspeedSince;
	private int numberOfOverspeedSince;

	public VuOverSpeedingControlData(byte[] bytes) {
		int start=0;
		this.lastOverspeedControlTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.LASTOVERSPEEDCONTROLTIME.getSize()));
		this.firstOverspeedSince=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.FIRSTOVERSPEEDSINCE.getSize()));
		this.numberOfOverspeedSince=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.NUMBEROFOVERSPEEDSINCE.getSize()));
	}

	/**
	 * @return the lastOverspeedControlTime
	 */
	public Date getLastOverspeedControlTime() {
		return lastOverspeedControlTime;
	}

	/**
	 * @param lastOverspeedControlTime the lastOverspeedControlTime to set
	 */
	public void setLastOverspeedControlTime(Date lastOverspeedControlTime) {
		this.lastOverspeedControlTime = lastOverspeedControlTime;
	}

	/**
	 * @return the firstOverspeedSince
	 */
	public Date getFirstOverspeedSince() {
		return firstOverspeedSince;
	}

	/**
	 * @param firstOverspeedSince the firstOverspeedSince to set
	 */
	public void setFirstOverspeedSince(Date firstOverspeedSince) {
		this.firstOverspeedSince = firstOverspeedSince;
	}

	/**
	 * @return the numberOfOverspeedSince
	 */
	public int getNumberOfOverspeedSince() {
		return numberOfOverspeedSince;
	}

	/**
	 * @param numberOfOverspeedSince the numberOfOverspeedSince to set
	 */
	public void setNumberOfOverspeedSince(int numberOfOverspeedSince) {
		this.numberOfOverspeedSince = numberOfOverspeedSince;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuOverSpeedingControlData [lastOverspeedControlTime=" + lastOverspeedControlTime
				+ ", firstOverspeedSince=" + firstOverspeedSince + ", numberOfOverspeedSince=" + numberOfOverspeedSince
				+ "]";
	}
	
	

}
