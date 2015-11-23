/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.Arrays;

import org.tacografo.file.cardblockdriver.Sizes;

/**
 * 
 * @author Andres
 * 2.113. VehicleRegistrationIdentification
 * 
 * Identificación de un vehículo, exclusiva para Europa (VRN y Estado miembro).
 * 
 * VehicleRegistrationIdentification::= SEQUENCE {
 * 
 * vehicleRegistrationNation NationNumeric,
 * 
 * vehicleRegistrationNumber VehicleRegistrationNumber
 * 
 * }
 * 
 * vehicleRegistrationNation es la nación donde se matriculó el vehículo.
 * 
 * vehicleRegistrationNumber es el número de matrícula del vehículo (VRN).
 *
 * 
 *
 */
public class VehicleRegistrationIdentification {
	
	
	private String vehicleRegistrationNation;
	private String vehicleRegistrationNumber;
	
	/**
	 * 
	 */
	public VehicleRegistrationIdentification() {
		// TODO Auto-generated constructor stub
	}


	public VehicleRegistrationIdentification(byte[] bytes) {
		int start=0;
		
		NationNumeric nn=new NationNumeric(Arrays.copyOfRange(bytes, start, start+=Sizes.VEHICLEREGISTRATIONNATION.getMax()));
		this.vehicleRegistrationNation=nn.getNationNumeric();
		
		VehicleRegistrationNumber vrn=new VehicleRegistrationNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.VEHICLEREGISTRATIONNUMBER.getMax()));
		this.vehicleRegistrationNumber=vrn.getVehicleRegNumber();
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nVehicleRegistrationIdentification [vehicleRegistrationNation="
				+ vehicleRegistrationNation + ", vehicleRegistrationNumber="
				+ vehicleRegistrationNumber + "]";
	}


	/**
	 * @return the vehicleRegistrationNation
	 */
	public String getVehicleRegistrationNation() {
		return vehicleRegistrationNation;
	}

	/**
	 * @param vehicleRegistrationNation the vehicleRegistrationNation to set
	 */
	public void setVehicleRegistrationNation(String vehicleRegistrationNation) {
		this.vehicleRegistrationNation = vehicleRegistrationNation;
	}

	/**
	 * @return the vehicleRegistrationNumber
	 */
	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	/**
	 * @param vehicleRegistrationNumber the vehicleRegistrationNumber to set
	 */
	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}
	

}
