package com.thingtrack.parse;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActivityChangeInfo {

	private String type;
    private String slot;
    private String driverSystem;
    private String cardStatus;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT")
    private Date fromTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT")
    private Date toTime;
    
	public ActivityChangeInfo(){
		
	}
	public ActivityChangeInfo(org.tacografo.file.cardblockdriver.subblock.ActivityChangeInfo aci) {
		this.type=aci.getC();
		this.slot=aci.getS();
		this.driverSystem=aci.getAa();
		this.cardStatus=aci.getP();
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the slot
	 */
	public String getSlot() {
		return slot;
	}
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}
	/**
	 * @return the driverSystem
	 */
	public String getDriverSystem() {
		return driverSystem;
	}
	/**
	 * @param driverSystem the driverSystem to set
	 */
	public void setDriverSystem(String driverSystem) {
		this.driverSystem = driverSystem;
	}
	/**
	 * @return the cardStatus
	 */
	public String getCardStatus() {
		return cardStatus;
	}
	/**
	 * @param cardStatus the cardStatus to set
	 */
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	/**
	 * @return the fromTime
	 */
	public Date getFromTime() {
		return fromTime;
	}
	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	/**
	 * @return the toTime
	 */
	public Date getToTime() {
		return toTime;
	}
	/**
	 * @param toTime the toTime to set
	 */
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	

}
