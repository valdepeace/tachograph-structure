/**
 * 
 */
package com.thingtrack.parse;

import java.util.Date;

/**
 * @author negrero
 *
 */
public class Fault {

	
	private String coDriverSlotBegin; 
    private String coDriverSlotEnd;
    private String driverSlotBegin;
    private String driverSlotEnd;
    private Date faultBegin;
    private Date faultEnd;
    private int faultecordPurpose;
    private String faultType;
    
	/**
	 * 
	 */
	public Fault() {
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
	 * @return the faultBegin
	 */
	public Date getFaultBegin() {
		return faultBegin;
	}

	/**
	 * @param faultBegin the faultBegin to set
	 */
	public void setFaultBegin(Date faultBegin) {
		this.faultBegin = faultBegin;
	}

	/**
	 * @return the faultEnd
	 */
	public Date getFaultEnd() {
		return faultEnd;
	}

	/**
	 * @param faultEnd the faultEnd to set
	 */
	public void setFaultEnd(Date faultEnd) {
		this.faultEnd = faultEnd;
	}

	/**
	 * @return the faultecordPurpose
	 */
	public int getFaultecordPurpose() {
		return faultecordPurpose;
	}

	/**
	 * @param faultecordPurpose the faultecordPurpose to set
	 */
	public void setFaultecordPurpose(int faultecordPurpose) {
		this.faultecordPurpose = faultecordPurpose;
	}

	/**
	 * @return the faultType
	 */
	public String getFaultType() {
		return faultType;
	}

	/**
	 * @param faultType the faultType to set
	 */
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

}
