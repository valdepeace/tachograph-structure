/**
 * 
 */
package com.thingtrack.parse;

import java.util.Date;

/**
 * @author negrero
 *
 */
public class OverSpeed {

	 private int avarageSpeedValue;
     private String cardNumber;
     private Date eventBegin;
     private Date eventEnd;
     private String eventType;
     private int maxSpeedValue;
     private int similarEventsNumber;
	/**
	 * 
	 */
	public OverSpeed() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the avarageSpeedValue
	 */
	public int getAvarageSpeedValue() {
		return avarageSpeedValue;
	}
	/**
	 * @param avarageSpeedValue the avarageSpeedValue to set
	 */
	public void setAvarageSpeedValue(int avarageSpeedValue) {
		this.avarageSpeedValue = avarageSpeedValue;
	}
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the eventBegin
	 */
	public Date getEventBegin() {
		return eventBegin;
	}
	/**
	 * @param eventBegin the eventBegin to set
	 */
	public void setEventBegin(Date eventBegin) {
		this.eventBegin = eventBegin;
	}
	/**
	 * @return the eventEnd
	 */
	public Date getEventEnd() {
		return eventEnd;
	}
	/**
	 * @param eventEnd the eventEnd to set
	 */
	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
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

}
