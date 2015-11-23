/**
 * 
 */
package org.tacografo.file.vublock;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.FullCardNumber;
import org.tacografo.file.vublock.subblock.EventFaulType;
import org.tacografo.file.vublock.subblock.EventFaultRecordPurpose;
import org.tacografo.tiposdatos.RealTime;

/**
 * @author Andres Carmona Gil
 * 
 * 2.134.   VuFaultRecord
 * Información almacenada en una unidad intravehicular y relativa a un fallo (requisito 096).
 * VuFaultRecord ::= SEQUENCE {
 * faultType EventFaultType,
 * faultRecordPurpose EventFaultRecordPurpose,
 * faultBeginTime TimeReal,
 * faultEndTime TimeReal,
 * cardNumberDriverSlotBegin FullCardNumber,
 * cardNumberCodriverSlotBegin FullCardNumber,
 * cardNumberDriverSlotEnd FullCardNumber,
 * cardNumberCodriverSlotEnd FullCardNumber
 * }
 * faultType es el tipo de fallo del aparato de control.
 * faultRecordPurpose es el propósito con que se ha registrado ese fallo.
 * faultBeginTime es la fecha y la hora de comienzo del fallo.
 * faultEndTime es la fecha y la hora en que termina el fallo.
 * cardNumberDriverSlotBegin identifica la tarjeta que estaba insertada en la ranura del conductor en el momento en que comenzó el fallo.
 * cardNumberCodriverSlotBegin identifica la tarjeta que estaba insertada en la ranura del segundo conductor en el momento en que comenzó el fallo.
 * cardNumberDriverSlotEnd identifica la tarjeta que estaba insertada en la ranura del conductor en el momento en que terminó el fallo.
 * cardNumberCodriverSlotEnd identifica la tarjeta que estaba insertada en la ranura del segundo conductor en el momento en que terminó el fallo.
 *
 */
public class VuFaultRecord {

	private String fualtType;
	private String faultRecordPurpose;
	private Date faultBeginTime;
	private Date faultEndTime;
	private FullCardNumber cardNumberDriverSlotBegin;
	private FullCardNumber cardNumberCoDriverSlotBegin;
	private FullCardNumber cardNumberDriverSlotEnd;
	private FullCardNumber cardNumberCoDriverSlotEnd;
	
	
	public VuFaultRecord(byte[] bytes) throws UnsupportedEncodingException {
		int start=0;
		
		this.fualtType= EventFaulType.getEventFaultType(bytes[start]);
		start+=Sizes.FAULTTYPE.getSize();
		this.faultRecordPurpose=EventFaultRecordPurpose.getEventFaultRecordPurpose(bytes[start]);
		start+=Sizes.FAULTRECORDPURPOSE.getSize();
		this.faultBeginTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, Sizes.FAULTBEGINTIME.getSize()));
		this.faultEndTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, Sizes.FAULTENDTIME.getSize()));
		FullCardNumber fcn;
		this.cardNumberDriverSlotBegin=new FullCardNumber(Arrays.copyOfRange(bytes, start, Sizes.CARDNUMBERDRIVERSLOTBEGIN_FAULT.getSize()));
		this.cardNumberCoDriverSlotBegin=new FullCardNumber(Arrays.copyOfRange(bytes, start, Sizes.CARDNUMBERCODRIVERSLOTBEGIN_FAULT.getSize()));
		this.cardNumberDriverSlotEnd=new FullCardNumber(Arrays.copyOfRange(bytes, start, Sizes.CARDNUMBERDRIVERSLOTEND_FAULT.getSize()));
		this.cardNumberCoDriverSlotEnd=new FullCardNumber(Arrays.copyOfRange(bytes, start, Sizes.CARDNUMBERCODRIVERSLOTEND_FAULT.getSize()));
	}


	/**
	 * @return the fualtType
	 */
	public String getFualtType() {
		return fualtType;
	}


	/**
	 * @param fualtType the fualtType to set
	 */
	public void setFualtType(String fualtType) {
		this.fualtType = fualtType;
	}


	/**
	 * @return the faultRecordPurpose
	 */
	public String getFaultRecordPurpose() {
		return faultRecordPurpose;
	}


	/**
	 * @param faultRecordPurpose the faultRecordPurpose to set
	 */
	public void setFaultRecordPurpose(String faultRecordPurpose) {
		this.faultRecordPurpose = faultRecordPurpose;
	}


	/**
	 * @return the faultBeginTime
	 */
	public Date getFaultBeginTime() {
		return faultBeginTime;
	}


	/**
	 * @param faultBeginTime the faultBeginTime to set
	 */
	public void setFaultBeginTime(Date faultBeginTime) {
		this.faultBeginTime = faultBeginTime;
	}


	/**
	 * @return the faultEndTime
	 */
	public Date getFaultEndTime() {
		return faultEndTime;
	}


	/**
	 * @param faultEndTime the faultEndTime to set
	 */
	public void setFaultEndTime(Date faultEndTime) {
		this.faultEndTime = faultEndTime;
	}


	/**
	 * @return the cardNumberDriverSlotBegin
	 */
	public FullCardNumber getCardNumberDriverSlotBegin() {
		return cardNumberDriverSlotBegin;
	}


	/**
	 * @param cardNumberDriverSlotBegin the cardNumberDriverSlotBegin to set
	 */
	public void setCardNumberDriverSlotBegin(FullCardNumber cardNumberDriverSlotBegin) {
		this.cardNumberDriverSlotBegin = cardNumberDriverSlotBegin;
	}


	/**
	 * @return the cardNumberCoDriverSlotBegin
	 */
	public FullCardNumber getCardNumberCoDriverSlotBegin() {
		return cardNumberCoDriverSlotBegin;
	}


	/**
	 * @param cardNumberCoDriverSlotBegin the cardNumberCoDriverSlotBegin to set
	 */
	public void setCardNumberCoDriverSlotBegin(FullCardNumber cardNumberCoDriverSlotBegin) {
		this.cardNumberCoDriverSlotBegin = cardNumberCoDriverSlotBegin;
	}


	/**
	 * @return the cardNumberDriverSlotEnd
	 */
	public FullCardNumber getCardNumberDriverSlotEnd() {
		return cardNumberDriverSlotEnd;
	}


	/**
	 * @param cardNumberDriverSlotEnd the cardNumberDriverSlotEnd to set
	 */
	public void setCardNumberDriverSlotEnd(FullCardNumber cardNumberDriverSlotEnd) {
		this.cardNumberDriverSlotEnd = cardNumberDriverSlotEnd;
	}


	/**
	 * @return the cardNumberCoDriverSlotEnd
	 */
	public FullCardNumber getCardNumberCoDriverSlotEnd() {
		return cardNumberCoDriverSlotEnd;
	}


	/**
	 * @param cardNumberCoDriverSlotEnd the cardNumberCoDriverSlotEnd to set
	 */
	public void setCardNumberCoDriverSlotEnd(FullCardNumber cardNumberCoDriverSlotEnd) {
		this.cardNumberCoDriverSlotEnd = cardNumberCoDriverSlotEnd;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuFaultRecord [fualtType=" + fualtType + ", faultRecordPurpose=" + faultRecordPurpose
				+ ", faultBeginTime=" + faultBeginTime + ", faultEndTime=" + faultEndTime
				+ ", cardNumberDriverSlotBegin=" + cardNumberDriverSlotBegin + ", cardNumberCoDriverSlotBegin="
				+ cardNumberCoDriverSlotBegin + ", cardNumberDriverSlotEnd=" + cardNumberDriverSlotEnd
				+ ", cardNumberCoDriverSlotEnd=" + cardNumberCoDriverSlotEnd + "]";
	}
	
	

}
