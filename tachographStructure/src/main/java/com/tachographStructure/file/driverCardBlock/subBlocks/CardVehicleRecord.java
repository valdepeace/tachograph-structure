/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import java.util.Arrays;
import java.util.Date;

import com.tachographStructure.file.driverCardBlock.DriverCardSizes;
import com.tachographStructure.file.vuBlocks.subBlocks.VuDataBlockCounter;
import com.tachographStructure.helpers.OdometerShort;
import com.tachographStructure.helpers.RealTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 2.30. CardVehicleRecord
 * Informaci�n almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a un per�odo de
 * uso de un veh�culo durante un d�a civil (requisitos 197 y 217).
 * CardVehicleRecord ::= SEQUENCE {
 * vehicleOdometerBegin OdometerShort,
 * vehicleOdometerEnd OdometerShort,
 * vehicleFirstUse TimeReal,
 * vehicleLastUse TimeReal,
 * vehicleRegistration VehicleRegistrationIdentification,
 * vuDataBlockCounter VuDataBlockCounter
 * }
 * vehicleOdometerBegin es la lectura del cuentakil�metros del veh�culo al comenzar el per�odo de uso del veh�culo.
 * vehicleOdometerEnd es la lectura del cuentakil�metros del veh�culo al terminar el per�odo de uso del veh�culo.
 * vehicleFirstUse es la fecha y la hora en que comienza el per�odo de uso del veh�culo.
 * vehicleLastUse es la fecha y la hora en que termina el per�odo de uso del veh�culo.
 * vehicleRegistration es el VRN y el Estado miembro donde se ha matriculado el veh�culo.
 * vuDataBlockCounter es el valor del VuDataBlockCounter en el momento de extraer la tarjeta por �ltima vez en el
 * per�odo de uso del vehículo
 * 
 * @author Negrero
 *
 */
public class CardVehicleRecord{
	
	
	private int vehicleOdometerBegin;
	private int vehicleOdometerEnd;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
	private Date vehicleFirstUse; 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
	private Date vehicleLastUse;
	private VehicleRegistrationIdentification vehicleRegistration ;
	private Integer vuDataBlockCounter;
	
	
	
	public CardVehicleRecord() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public CardVehicleRecord(byte[] bytes) {
		int start=0;
		this.vehicleOdometerBegin= OdometerShort.getOdometerShort(Arrays.copyOfRange(bytes, start, start+= DriverCardSizes.VEHICLEODOMETERBEGIN.getMax()));
		this.vehicleOdometerEnd=OdometerShort.getOdometerShort(Arrays.copyOfRange(bytes, start, start+=DriverCardSizes.VEHICLEODOMETEREND.getMax()));
		this.vehicleFirstUse= RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=DriverCardSizes.VEHICLEFIRSTUSE.getMax()));
		this.vehicleLastUse=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=DriverCardSizes.VEHICLELASTUSE.getMax()));
		this.vehicleRegistration=new VehicleRegistrationIdentification(Arrays.copyOfRange(bytes, start, start+=DriverCardSizes.VEHICLEREGISTRATION.getMax()));
		VuDataBlockCounter vudatablockcounter = new VuDataBlockCounter(Arrays.copyOfRange(bytes, start, start+=DriverCardSizes.VUDATABLOCKCOUNTER.getMax()));
		this.vuDataBlockCounter=Integer.valueOf(vudatablockcounter.getVuDataBlockCounter());
		
	}


	/**
	 * Obtiene la lectura del cuentakil�metros del veh�culo al comenzar el per�odo de uso del veh�culo.
	 * @return the vehicleOdometerBegin
	 */
	public int getVehicleOdometerBegin() {
		return vehicleOdometerBegin;
	}


	/**
	 * Asigna la lectura del cuentakil�metros del veh�culo al comenzar el per�odo de uso del veh�culo.
	 * @param vehicleOdometerBegin the vehicleOdometerBegin to set
	 */
	public void setVehicleOdometerBegin(int vehicleOdometerBegin) {
		this.vehicleOdometerBegin = vehicleOdometerBegin;
	}


	/**
	 * Obtiene la lectura del cuentakil�metros del veh�culo al terminar el per�odo de uso del veh�culo.
	 * @return the vehicleOdometerEnd
	 */
	public int getVehicleOdometerEnd() {
		return vehicleOdometerEnd;
	}


	/**
	 * Asigna la lectura del cuentakil�metros del veh�culo al terminar el per�odo de uso del veh�culo.
	 * @param vehicleOdometerEnd the vehicleOdometerEnd to set
	 */
	public void setVehicleOdometerEnd(int vehicleOdometerEnd) {
		this.vehicleOdometerEnd = vehicleOdometerEnd;
	}


	/**
	 * Obtiene la fecha y la hora en que comienza el per�odo de uso del veh�culo.
	 * @return the vehicleFirstUse
	 */
	public Date getVehicleFirstUse() {
		return vehicleFirstUse;
	}


	/**
	 * Asigna la fecha y la hora en que comienza el per�odo de uso del veh�culo.
	 * @param vehicleFirstUse the vehicleFirstUse to set
	 */
	public void setVehicleFirstUse(Date vehicleFirstUse) {
		this.vehicleFirstUse = vehicleFirstUse;
	}


	/**
	 * Obtiene la fecha y la hora en que termina el per�odo de uso del veh�culo.
	 * @return the vehicleLastUse
	 */
	public Date getVehicleLastUse() {
		return vehicleLastUse;
	}


	/**
	 * Asigna la fecha y la hora en que termina el per�odo de uso del veh�culo.
	 * @param vehicleLastUse the vehicleLastUse to set
	 */
	public void setVehicleLastUse(Date vehicleLastUse) {
		this.vehicleLastUse = vehicleLastUse;
	}


	/**
	 * Obtiene el VRN y el Estado miembro donde se ha matriculado el veh�culo.
	 * @return the vehicleRegistration
	 */
	public VehicleRegistrationIdentification getVehicleRegistration() {
		return vehicleRegistration;
	}


	/**
	 * Asigna el VRN y el Estado miembro donde se ha matriculado el veh�culo.
	 * @param vehicleRegistration the vehicleRegistration to set
	 */
	public void setVehicleRegistration(
			VehicleRegistrationIdentification vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}


	/**
	 * Obtiene el valor del VuDataBlockCounter en el momento de extraer la tarjeta por �ltima vez en el
	 * per�odo de uso del veh�culo
	 * @return the vuDataBlockCounter
	 */
	public Integer getVuDataBlockCounter() {
		return vuDataBlockCounter;
	}


	/**
	 * Asigna el valor del VuDataBlockCounter en el momento de extraer la tarjeta por �ltima vez en el
	 * per�odo de uso del veh�culo
	 * @param vuDataBlockCounter the vuDataBlockCounter to set
	 */
	public void setVuDataBlockCounter(Integer vuDataBlockCounter) {
		this.vuDataBlockCounter = vuDataBlockCounter;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardVehicleRecord [vehicleOdometerBegin="
				+ vehicleOdometerBegin + ", vehicleOdometerEnd="
				+ vehicleOdometerEnd + ", vehicleFirstUse=" + vehicleFirstUse
				+ ", vehicleLastUse=" + vehicleLastUse
				+ ", vehicleRegistration=" + vehicleRegistration
				+ ", vuDataBlockCounter=" + vuDataBlockCounter + "]";
	}
	

}
