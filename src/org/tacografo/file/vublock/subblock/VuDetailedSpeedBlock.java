/**
 * 
 */
package org.tacografo.file.vublock.subblock;

import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.vublock.Sizes;
import org.tacografo.tiposdatos.RealTime;
import org.tacografo.tiposdatos.Number;

/**
 * @author Andres Carmona Gil
 * 
 * 2.127.   VuDetailedSpeedBlock
 * 
 * Información pormenorizada almacenada en una unidad intravehicular y relativa a la velocidad del vehículo durante un minuto que haya estado en movimiento (requisito 093).
 * VuDetailedSpeedBlock ::= SEQUENCE {
 * speedBlockBeginDate TimeReal,
 * speedsPerSecond SEQUENCE SIZE(60) OF Speed
 * }
 * speedBlockBeginDate es la fecha y la hora del primer valor de velocidad comprendido en ese bloque.
 * speedsPerSecond es la secuencia cronológica de las velocidades medidas cada segundo de ese minuto, empezando desde speedBlockBeginDate (inclusive).
 *
 */

public class VuDetailedSpeedBlock {

	private Date speedBlockBeginDate;
	private int[] speedsPerSecond;
	
	public VuDetailedSpeedBlock(byte[] bytes){
		int start=0;
		this.speedBlockBeginDate=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.SPEEDBLOCKBEGINDATE.getSize()));
		for(int i=start;i<bytes.length;i++){
			this.speedsPerSecond[i]=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=1));
		}
	}

	/**
	 * @return the speedBlockBeginDate
	 */
	public Date getSpeedBlockBeginDate() {
		return speedBlockBeginDate;
	}

	/**
	 * @param speedBlockBeginDate the speedBlockBeginDate to set
	 */
	public void setSpeedBlockBeginDate(Date speedBlockBeginDate) {
		this.speedBlockBeginDate = speedBlockBeginDate;
	}

	/**
	 * @return the speedsPerSecond
	 */
	public int[] getSpeedsPerSecond() {
		return speedsPerSecond;
	}

	/**
	 * @param speedsPerSecond the speedsPerSecond to set
	 */
	public void setSpeedsPerSecond(int[] speedsPerSecond) {
		this.speedsPerSecond = speedsPerSecond;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuDetailedSpeedBlock [speedBlockBeginDate=" + speedBlockBeginDate + ", speedsPerSecond="
				+ Arrays.toString(speedsPerSecond) + "]";
	}
	
	
}
