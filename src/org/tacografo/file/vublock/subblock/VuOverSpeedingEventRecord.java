/**
 * 
 */
package org.tacografo.file.vublock.subblock;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;


import org.tacografo.file.cardblockdriver.subblock.FullCardNumber;
import org.tacografo.file.vublock.subblock.EventFaulType;
import org.tacografo.file.vublock.Sizes;
import org.tacografo.tiposdatos.RealTime;
import org.tacografo.tiposdatos.Number;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 2.141.   VuOverSpeedingEventRecord
 * 
 * Información almacenada en una unidad intravehicular y relativa a incidentes de exceso de velocidad (requisito 094).
 * VuOverSpeedingEventRecord ::= SEQUENCE {
 * eventType EventFaultType,
 * eventRecordPurpose EventFaultRecordPurpose,
 * eventBeginTime TimeReal,
 * eventEndTime TimeReal,
 * maxSpeedValue SpeedMax,
 * averageSpeedValue SpeedAverage,
 * cardNumberDriverSlotBegin FullCardNumber,
 * similarEventsNumber SimilarEventsNumber
 * }
 * eventType es el tipo de incidente.
 * eventRecordPurpose es el propósito con que se ha registrado ese incidente.
 * eventBeginTime es la fecha y la hora de comienzo del incidente.
 * eventEndTime es la fecha y la hora en que termina el incidente.
 * maxSpeedValue es la velocidad máxima medida durante el incidente.
 * averageSpeedValue es la media aritmética de las velocidades medidas durante el incidente.
 * cardNumberDriverSlotBegin identifica la tarjeta que estaba insertada en la ranura del conductor en el momento en que comenzó el incidente.
 * similarEventsNumber es el número de incidentes similares ocurridos ese día.
 */
public class VuOverSpeedingEventRecord {
	
	private String eventType; //EventFaultType
	private String eventRecordPurpose ;//EventFaultRecordPurpose
	private Date eventBeginTime;// TimeReal
	private Date eventEndTime;// TimeReal
	private int maxSpeedValue;// SpeedMax
	private int averageSpeedValue; //SpeedAverage
	private FullCardNumber cardNumberDriverSlotBegin;
	private int similarEventsNumber;// SimilarEventsNumber
	
	public VuOverSpeedingEventRecord(byte[] bytes) throws UnsupportedEncodingException {
		int start=0;
		
		this.eventType=EventFaulType.getEventFaultType(bytes[start]);  
		start+=Sizes.EVENTTYPE.getSize();
		this.eventRecordPurpose=EventFaultRecordPurpose.getEventFaultRecordPurpose(bytes[start]);
		start+=Sizes.EVENTRECORDPURPOSE.getSize();
		this.eventBeginTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.EVENTBEGINTIME_VUOVERSPEED.getSize()));
		this.eventEndTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.EVENTENDTIME_VUOVERSPEED.getSize()));
		this.maxSpeedValue=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.MAXSPEEDVALUE.getSize()));
		this.averageSpeedValue=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.AVARAGESPEEDVALUE.getSize()));
		this.cardNumberDriverSlotBegin=new FullCardNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.CARDNUMBERDRIVERSLOTBEGIN_VUOVERSPEED.getSize()));
		this.similarEventsNumber=Number.getNumber(Arrays.copyOfRange(bytes,start, start+=Sizes.SIMILAREVENTSNUMBER.getSize()));
		
		
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
	 * @return the maxSpeedValue
	 */
	public int getMaxSpeedValue() {
		return maxSpeedValue;
	}

	/**
	 * @param maxSpeedValue the maxSpeedValue to set
	 */
	public void setMaxSpeedValue(int maxSpeedValue) {
		this.maxSpeedValue = maxSpeedValue;
	}

	/**
	 * @return the averageSpeedValue
	 */
	public int getAverageSpeedValue() {
		return averageSpeedValue;
	}

	/**
	 * @param averageSpeedValue the averageSpeedValue to set
	 */
	public void setAverageSpeedValue(int averageSpeedValue) {
		this.averageSpeedValue = averageSpeedValue;
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
		return "VuOverSpeedingEventRecord [eventType=" + eventType + ", eventRecordPurpose=" + eventRecordPurpose
				+ ", eventBeginTime=" + eventBeginTime + ", eventEndTime=" + eventEndTime + ", maxSpeedValue="
				+ maxSpeedValue + ", averageSpeedValue=" + averageSpeedValue + ", cardNumberDriverSlotBegin="
				+ cardNumberDriverSlotBegin + ", similarEventsNumber=" + similarEventsNumber + "]";
	}
	
	
	

}
