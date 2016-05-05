/**
 * 
 */
package com.thingtrack.parse;

import java.util.Date;

/**
 * @author negrero
 *
 */
public class Event {
	private String coDriverSlotBegin; 
    private String coDriverSlotEnd;
    private String driverSlotBegin;
    private String driverSlotEnd;
    private Date eventBegin;
    private Date eventEnd;
    private int eventRecordPurpose;
    private String eventType;
    private int similarEventsNumber;
	/**
	 * 
	 */
	public Event() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the coDriverSlotBegin
	 */
	public String getCoDriverSlotBegin() {
		return coDriverSlotBegin;
	}
	/**
	 * @param coDriverSlotBegin the coDriverSlotBegin to set
	 */
	public void setCoDriverSlotBegin(String coDriverSlotBegin) {
		this.coDriverSlotBegin = coDriverSlotBegin;
	}
	/**
	 * @return the coDriverSlotEnd
	 */
	public String getCoDriverSlotEnd() {
		return coDriverSlotEnd;
	}
	/**
	 * @param coDriverSlotEnd the coDriverSlotEnd to set
	 */
	public void setCoDriverSlotEnd(String coDriverSlotEnd) {
		this.coDriverSlotEnd = coDriverSlotEnd;
	}
	/**
	 * @return the driverSlotBegin
	 */
	public String getDriverSlotBegin() {
		return driverSlotBegin;
	}
	/**
	 * @param driverSlotBegin the driverSlotBegin to set
	 */
	public void setDriverSlotBegin(String driverSlotBegin) {
		this.driverSlotBegin = driverSlotBegin;
	}
	/**
	 * @return the driverSlotEnd
	 */
	public String getDriverSlotEnd() {
		return driverSlotEnd;
	}
	/**
	 * @param driverSlotEnd the driverSlotEnd to set
	 */
	public void setDriverSlotEnd(String driverSlotEnd) {
		this.driverSlotEnd = driverSlotEnd;
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
	 * @return the eventRecordPurpose
	 */
	public int getEventRecordPurpose() {
		return eventRecordPurpose;
	}
	/**
	 * @param eventRecordPurpose the eventRecordPurpose to set
	 */
	public void setEventRecordPurpose(int eventRecordPurpose) {
		this.eventRecordPurpose = eventRecordPurpose;
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
