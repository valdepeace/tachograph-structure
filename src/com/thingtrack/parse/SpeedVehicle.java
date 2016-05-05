/**
 * 
 */
package com.thingtrack.parse;

import java.util.Date;

/**
 * @author negrero
 *
 */
public class SpeedVehicle {
	
	private Date time;
	private int velocity;
	/**
	 * 
	 */
	public SpeedVehicle() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * @return the velocity
	 */
	public int getVelocity() {
		return velocity;
	}
	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

}
