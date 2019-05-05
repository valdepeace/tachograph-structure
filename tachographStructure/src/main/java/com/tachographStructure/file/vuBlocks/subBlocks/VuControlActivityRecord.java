/**
 * 
 */
package com.tachographStructure.file.vuBlocks.subBlocks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import com.tachographStructure.file.driverCardBlock.subBlocks.ControlType;
import com.tachographStructure.file.driverCardBlock.subBlocks.FullCardNumber;
import com.tachographStructure.file.vuBlocks.VuSizes;
import com.tachographStructure.helpers.RealTime;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */

/**
 * 
 * 2.125. VuControlActivityRecord
 *
 * Informaci�n almacenada en una unidad intravehicular y relativa a un control efectuado con dicha VU (requisito 102).
 *
 * VuControlActivityRecord::= SEQUENCE {
 *
 * controlType ControlType,
 *
 * controlTime TimeReal,
 * 
 * controlCardNumber FullCardNumber,
 * 
 * downloadPeriodBeginTime TimeReal,
 * 
 * downloadPeriodEndTime TimeReal
 *
 * }
 *
 * controlType es el tipo de control.
 *
 * controlTime es la fecha y la hora del control.
 *
 * ControlCardNumber identifica la tarjeta de control empleada para el control.
 * 
 * downloadPeriodBeginTime es la hora de comienzo del per�odo cuyos datos se transfieren, en caso de transferencia.
 *
 * downloadPeriodEndTime es la hora de conclusi�n del per�odo cuyos datos se transfieren, en caso de transferencia.
 *
 */

public class VuControlActivityRecord {
	
	private ControlType controlType;
	private Date controlTime;
	private FullCardNumber controlCardNumber;
	private Date downloadPeriodBeginTime;
	private Date downloadPeriodEndTime;
	
	
	public VuControlActivityRecord(byte[] bytes) throws UnsupportedEncodingException{
		int start=0;
		this.controlType=new ControlType(Arrays.copyOfRange(bytes, start, start+=VuSizes.CONTROLTYPE.getSize()));
		this.controlTime= RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=VuSizes.CONTROLTIME.getSize()));
		this.controlCardNumber= new FullCardNumber(Arrays.copyOfRange(bytes, start, start+=VuSizes.FULLCARDNUMBER.getSize()));
		this.downloadPeriodBeginTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=VuSizes.DOWNLOADPERIODBEGINTIME.getSize()));
		this.downloadPeriodEndTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=VuSizes.DOWNLOADPERIODENDTIME.getSize()));
	}


	/**
	 * @return the controlType
	 */
	public ControlType getControlType() {
		return controlType;
	}


	/**
	 * @param controlType the controlType to set
	 */
	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}


	/**
	 * @return the controlTime
	 */
	public Date getControlTime() {
		return controlTime;
	}


	/**
	 * @param controlTime the controlTime to set
	 */
	public void setControlTime(Date controlTime) {
		this.controlTime = controlTime;
	}


	/**
	 * @return the controlCardNumber
	 */
	public FullCardNumber getControlCardNumber() {
		return controlCardNumber;
	}


	/**
	 * @param controlCardNumber the controlCardNumber to set
	 */
	public void setControlCardNumber(FullCardNumber controlCardNumber) {
		this.controlCardNumber = controlCardNumber;
	}


	/**
	 * @return the downloadPeriodBeginTime
	 */
	public Date getDownloadPeriodBeginTime() {
		return downloadPeriodBeginTime;
	}


	/**
	 * @param downloadPeriodBeginTime the downloadPeriodBeginTime to set
	 */
	public void setDownloadPeriodBeginTime(Date downloadPeriodBeginTime) {
		this.downloadPeriodBeginTime = downloadPeriodBeginTime;
	}


	/**
	 * @return the downloadPeriodEndTime
	 */
	public Date getDownloadPeriodEndTime() {
		return downloadPeriodEndTime;
	}


	/**
	 * @param downloadPeriodEndTime the downloadPeriodEndTime to set
	 */
	public void setDownloadPeriodEndTime(Date downloadPeriodEndTime) {
		this.downloadPeriodEndTime = downloadPeriodEndTime;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuControlActivityRecord [controlType=" + controlType + ", controlTime=" + controlTime
				+ ", controlCardNumber=" + controlCardNumber + ", downloadPeriodBeginTime=" + downloadPeriodBeginTime
				+ ", downloadPeriodEndTime=" + downloadPeriodEndTime + "]";
	}
	

}
