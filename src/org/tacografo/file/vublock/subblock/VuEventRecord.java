package org.tacografo.file.vublock.subblock;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.FullCardNumber;
import org.tacografo.file.vublock.Sizes;
import org.tacografo.tiposdatos.RealTime;
import org.tacografo.tiposdatos.Number;

/**
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 
 * 2.132.   VuEventRecord
 * Información almacenada en una unidad intravehicular y relativa a un incidente (requisito 094, salvo el incidente de exceso de velocidad).
 * VuEventRecord ::= SEQUENCE {
 * eventType EventFaultType,
 * eventRecordPurpose EventFaultRecordPurpose,
 * eventBeginTime TimeReal,
 * eventEndTime TimeReal,
 * cardNumberDriverSlotBegin FullCardNumber,
 * cardNumberCodriverSlotBegin FullCardNumber,
 * cardNumberDriverSlotEnd FullCardNumber,
 * cardNumberCodriverSlotEnd FullCardNumber,
 * similarEventsNumber SimilarEventsNumber
 * }
 * eventType es el tipo de incidente.
 * eventRecordPurpose es el propósito con que se ha registrado ese incidente.
 * eventBeginTime es la fecha y la hora de comienzo del incidente.
 * eventEndTime es la fecha y la hora en que termina el incidente.
 * cardNumberDriverSlotBegin identifica la tarjeta que estaba insertada en la ranura del conductor en el momento en que comenzó el incidente.
 * cardNumberCodriverSlotBegin identifica la tarjeta que estaba insertada en la ranura del segundo conductor en el momento en que comenzó el incidente.
 * cardNumberDriverSlotEnd identifica la tarjeta que estaba insertada en la ranura del conductor en el momento en que finalizó el incidente.
 * cardNumberCodriverSlotEnd identifica la tarjeta que estaba insertada en la ranura del segundo conductor en el momento en que finalizó el incidente.
 * similarEventsNumber es el número de incidentes similares ocuridos ese día.
 * Esta secuencia puede utilizarse para todos los incidentes, excepto los de exceso de velocidad.
 *
 */
public class VuEventRecord {
	
	private String eventType;
	private String eventRecordPurpose;
	private Date eventBeginTime;
	private Date eventEndTime;
	private FullCardNumber cardNumberDriverSlotBegin;
	private FullCardNumber cardNumberCoDriverSlotBegin;
	private FullCardNumber cardNumberDriverSlotEnd;
	private FullCardNumber cardNumberCoDriverSlotEnd;
	private int similarEventsNumber;

	public VuEventRecord(byte[] bytes) throws UnsupportedEncodingException {
		int start=0;
		this.eventType=EventFaulType.getEventFaultType(bytes[start]);
		start+=Sizes.EVENTTYPE.getSize();
		this.eventRecordPurpose=EventFaultRecordPurpose.getEventFaultRecordPurpose(bytes[start]);
		start+=Sizes.EVENTRECORDPURPOSE.getSize();
		this.eventBeginTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.EVENTBEGINTIME.getSize()));
		this.eventEndTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.EVENTENDTIME.getSize()));
		this.cardNumberDriverSlotBegin=new FullCardNumber(Arrays.copyOfRange(bytes,start, start+=Sizes.CARDNUMBERDRIVERSLOTBEGIN_EVENT.getSize()));
		this.cardNumberCoDriverSlotBegin=new FullCardNumber(Arrays.copyOfRange(bytes,start, start+=Sizes.CARDNUMBERCODRIVERSLOTBEGIN_EVENT.getSize()));
		this.cardNumberDriverSlotEnd=new FullCardNumber(Arrays.copyOfRange(bytes,start, start+=Sizes.CARDNUMBERDRIVERSLOTEND_EVENT.getSize()));
		this.cardNumberCoDriverSlotEnd=new FullCardNumber(Arrays.copyOfRange(bytes,start, start+=Sizes.CARDNUMBERCODRIVERSLOTEND_EVENT.getSize()));
		this.similarEventsNumber=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.SIMILAREVENTSNUMBER.getSize()));
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventRecordPurpose
	 */
	public String getEventRecordPurpose() {
		return eventRecordPurpose;
	}

	/**
	 * @param eventRecordPurpose the eventRecordPurpose to set
	 */
	public void setEventRecordPurpose(String eventRecordPurpose) {
		this.eventRecordPurpose = eventRecordPurpose;
	}

	/**
	 * @return the eventBeginTime
	 */
	public Date getEventBeginTime() {
		return eventBeginTime;
	}

	/**
	 * @param eventBeginTime the eventBeginTime to set
	 */
	public void setEventBeginTime(Date eventBeginTime) {
		this.eventBeginTime = eventBeginTime;
	}

	/**
	 * @return the eventEndTime
	 */
	public Date getEventEndTime() {
		return eventEndTime;
	}

	/**
	 * @param eventEndTime the eventEndTime to set
	 */
	public void setEventEndTime(Date eventEndTime) {
		this.eventEndTime = eventEndTime;
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

	/**
	 * @return the similarEventsNumber
	 */
	public int getSimilarEventsNumber() {
		return similarEventsNumber;
	}

	/**
	 * @param similarEventsNumber the similarEventsNumber to set
	 */
	public void setSimilarEventsNumber(int similarEventsNumber) {
		this.similarEventsNumber = similarEventsNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuEventRecord [eventType=" + eventType + ", eventRecordPurpose=" + eventRecordPurpose
				+ ", eventBeginTime=" + eventBeginTime + ", eventEndTime=" + eventEndTime
				+ ", cardNumberDriverSlotBegin=" + cardNumberDriverSlotBegin + ", cardNumberCoDriverSlotBegin="
				+ cardNumberCoDriverSlotBegin + ", cardNumberDriverSlotEnd=" + cardNumberDriverSlotEnd
				+ ", cardNumberCoDriverSlotEnd=" + cardNumberCoDriverSlotEnd + ", similarEventsNumber="
				+ similarEventsNumber + "]";
	}
	
	
		

}
