/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.cardblockdriver.Sizes;
import org.tacografo.tiposdatos.RealTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 2.18. CardFaultRecord
 * Información almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a un fallo asociado
 * al titular de la tarjeta (requisitos 208 y 223).
 * CardFaultRecord ::= SEQUENCE {
 * faultType EventFaultType,
 * faultBeginTime TimeReal,
 * faultEndTime TimeReal,
 * faultVehicleRegistration VehicleRegistrationIdentification
 * }
 * faultType es el tipo de fallo.
 * faultBeginTime es la fecha y la hora de comienzo del fallo.
 * faultEndTime es la fecha y la hora en que termina el fallo.
 * faultVehicleRegistration es el VRN y el nombre del Estado miembro donde se matriculó el vehículo en el que ocurrió
 * el fallo
 *
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class CardFaultRecord {
	
	
	private String faultType ; // size 1
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date faultBeginTime ; // size 4
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date faultEndTime;	//size 4
	private VehicleRegistrationIdentification faultVehicleRegistration ; //size 15 (1+14)
	
	
	public CardFaultRecord() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public CardFaultRecord(byte[] bytes) {
		
		int start=0;
		
		EventFaultType eft= new EventFaultType(Arrays.copyOfRange(bytes, start, start+=Sizes.EVENTTYPE.getMax()));
		this.faultType=eft.getEventFaultType();
		this.faultBeginTime= RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.FAULTBEGINTIME.getMax()));
		this.faultEndTime= RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.FAULTENDTIME.getMax()));
		this.faultVehicleRegistration = new VehicleRegistrationIdentification(Arrays.copyOfRange(bytes, start, start+=Sizes.FAULTVEHICLEREGISTRATION.getMax()));
		
	}

	/**
	 * Obtiene el tipo de fallo.
	 * @return the faultType
	 */
	public String getFaultType() {
		return faultType;
	}

	/**
	 * Asigna el tipo de fallo.
	 * @param faultType the faultType to set
	 */
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	/**
	 * Obtiene la fecha y la hora de comienzo del fallo.
	 * @return the faultBeginTime
	 */
	public Date getFaultBeginTime() {
		return faultBeginTime;
	}

	/**
	 * Asigna la fecha y la hora de comienzo del fallo.
	 * @param faultBeginTime the faultBeginTime to set
	 */
	public void setFaultBeginTime(Date faultBeginTime) {
		this.faultBeginTime = faultBeginTime;
	}

	/**
	 * Obtiene la fecha y la hora en que termina el fallo.
	 * @return the faultEndTime
	 */
	public Date getFaultEndTime() {
		return faultEndTime;
	}

	/**
	 * Asigna la fecha y la hora en que termina el fallo.
	 * @param faultEndTime the faultEndTime to set
	 */
	public void setFaultEndTime(Date faultEndTime) {
		this.faultEndTime = faultEndTime;
	}

	/**
	 * Obtiene el VRN y el nombre del Estado miembro donde se matriculó el vehículo en el que ocurrió
	 * el fallo.
	 * @return the faultVehicleRegistration
	 */
	public VehicleRegistrationIdentification getFaultVehicleRegistration() {
		return faultVehicleRegistration;
	}

	/**
	 * Asigna el VRN y el nombre del Estado miembro donde se matriculó el vehículo en el que ocurrió
	 * el fallo.
	 * @param faultVehicleRegistration the faultVehicleRegistration to set
	 */
	public void setFaultVehicleRegistration(
			VehicleRegistrationIdentification faultVehicleRegistration) {
		this.faultVehicleRegistration = faultVehicleRegistration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardFaultRecord [faultType=" + faultType + ", faultBeginTime="
				+ faultBeginTime + ", faultEndTime=" + faultEndTime
				+ ", faultVehicleRegistration=" + faultVehicleRegistration
				+ "]";
	}
	
	

}
