package com.thingtrack.parse;

import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.CardVehicleRecord;
import org.tacografo.file.vublock.Resumen;

public class Vehicle {
	
	private String registration;
	private String description;
	private boolean active;
	private int consumption=0;
	
	public Vehicle(){
		
	}

	public Vehicle(Resumen resumen) {
		this.registration = resumen.getVehicleRegistrationIdentification().getVehicleRegistrationNumber();
		this.description = "Create vehicle";
		this.active = true;
	}

	public Vehicle(CardVehicleRecord cvr) {
		this.registration=cvr.getVehicleRegistration().getVehicleRegistrationNumber();
		this.description = "Create vehicle";
		this.active = true;
	}

	/**
	 * @return the registration
	 */
	public String getRegistration() {
		return registration;
	}

	/**
	 * @param registration the registration to set
	 */
	public void setRegistration(String registration) {
		this.registration = registration;
	}


	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the consumption
	 */
	public int getConsumption() {
		return consumption;
	}

	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	
	
}
