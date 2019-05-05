/**
 * 
 */
package com.tachographStructure.file.vuBlocks.subBlocks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.tachographStructure.file.driverCardBlock.subBlocks.FullCardNumber;
import com.tachographStructure.file.driverCardBlock.subBlocks.PlaceRecord;
import com.tachographStructure.file.vuBlocks.VuSizes;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 */

/**
 *  2.144. VuPlaceDailyWorkPeriodRecord
 *  Información almacenada en una unidad intravehicular y relativa a un lugar donde un conductor comienza o termina un período de trabajo diario (requisito 087).
 *  VuPlaceDailyWorkPeriodRecord::= SEQUENCE {
 *  fullCardNumber FullCardNumber,
 *  placeRecord PlaceRecord
 *  }
 *  fullCardNumber es el tipo de tarjeta del conductor, el Estado miembro que la ha expedido y el número de tarjeta.
 *  placeRecord contiene la información relativa al lugar introducido.
 *
 */
public class VuPlaceDailyWorkPeriodRecord {
	
	private FullCardNumber fullCardNumber;
	private PlaceRecord placerecord;

	public VuPlaceDailyWorkPeriodRecord(byte[] bytes) throws UnsupportedEncodingException {
		int start = 0;
		this.fullCardNumber = new FullCardNumber(Arrays.copyOfRange(bytes, start, start += VuSizes.FULLCARDNUMBER.getSize()));
		this.placerecord = new PlaceRecord(Arrays.copyOfRange(bytes, start, start += VuSizes.PLACERECORD.getSize()));
	}

	/**
	 * @return the fullCardNumber
	 */
	public FullCardNumber getFullCardNumber() {
		return fullCardNumber;
	}

	/**
	 * @param fullCardNumber the fullCardNumber to set
	 */
	public void setFullCardNumber(FullCardNumber fullCardNumber) {
		this.fullCardNumber = fullCardNumber;
	}

	/**
	 * @return the placerecord
	 */
	public PlaceRecord getPlacerecord() {
		return placerecord;
	}

	/**
	 * @param placerecord the placerecord to set
	 */
	public void setPlacerecord(PlaceRecord placerecord) {
		this.placerecord = placerecord;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuPlaceDailyWorkPeriodRecord [fullCardNumber=" + fullCardNumber + ", placerecord=" + placerecord + "]";
	}
	
	

}
