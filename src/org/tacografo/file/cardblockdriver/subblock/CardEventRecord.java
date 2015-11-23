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
 * 2.16. CardEventRecord
 * Información almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a un incidente asociado
 * al titular de la tarjeta (requisitos 205 y 223).
 * CardEventRecord ::= SEQUENCE {
 * eventType EventFaultType,
 * eventBeginTime TimeReal,
 * eventEndTime TimeReal,
 * eventVehicleRegistration VehicleRegistrationIdentification
 * }
 * eventType es el tipo de incidente.
 * eventBeginTime es la fecha y la hora en que comenzó el incidente.
 * eventEndTime es la fecha y la hora en que terminó el incidente.
 * eventVehicleRegistration es el VRN y el nombre del Estado miembro donde se matriculó el vehículo en el que se produjo
 * el incidente.
 * 
 * @author Negrero
 *
 */

public class CardEventRecord  {
	
	
	private String eventType;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date eventBeginTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date eventEndTime;
	private VehicleRegistrationIdentification eventVehicleRegistration;
	
	
	public CardEventRecord() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public CardEventRecord(byte[] bytes) {
		int start=0;
		EventFaultType eft= new EventFaultType(Arrays.copyOfRange(bytes,start, start+=Sizes.EVENTTYPE.getMax()));
		this.eventType=eft.getEventFaultType();
		
		this.eventBeginTime=RealTime.getRealTime(Arrays.copyOfRange(bytes,start,start+=Sizes.EVENTBEGINTIME.getMax()));
		this.eventEndTime=RealTime.getRealTime(Arrays.copyOfRange(bytes,start,start+=Sizes.EVENTENDTIME.getMax()));
		
		@SuppressWarnings("unused")
		VehicleRegistrationIdentification vri=new VehicleRegistrationIdentification(Arrays.copyOfRange(bytes,start,start+=Sizes.EVENTVEHICLEREGISTRATION.getMax()));
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardEventRecord [eventType=" + eventType + ", eventBeginTime="
				+ eventBeginTime + ", eventEndTime=" + eventEndTime
				+ ", eventVehicleRegistration=" + eventVehicleRegistration
				+ "]";
	}


	/**
	 * Obtiene el tipo de incidente.
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Asigna el tipo de incidente.
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * Obtiene la fecha y la hora en que comenzó el incidente.
	 * @return the eventBeginTime
	 */
	public Date getEventBeginTime() {
		return eventBeginTime;
	}

	/**
	 * Asigna la fecha y la hora en que comenzó el incidente.
	 * @param eventBeginTime the eventBeginTime to set
	 */
	public void setEventBeginTime(Date eventBeginTime) {
		this.eventBeginTime = eventBeginTime;
	}

	/**
	 * Obtiene la fecha y la hora en que terminó el incidente.
	 * @return the eventEndTime
	 */
	public Date getEventEndTime() {
		return eventEndTime;
	}

	/**
	 * Asigna la fecha y la hora en que terminó el incidente.
	 * @param eventEndTime the eventEndTime to set
	 */
	public void setEventEndTime(Date eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	/**
	 * Obtiene el VRN y el nombre del Estado miembro donde se matriculó el vehículo en el que se produjo
	 * el incidente.
	 * @return the eventVehicleRegistration
	 */
	public VehicleRegistrationIdentification getEventVehicleRegistration() {
		return eventVehicleRegistration;
	}

	/**
	 * Asigna el VRN y el nombre del Estado miembro donde se matriculó el vehículo en el que se produjo
	 * el incidente.
	 * @param eventVehicleRegistration the eventVehicleRegistration to set
	 */
	public void setEventVehicleRegistration(
			VehicleRegistrationIdentification eventVehicleRegistration) {
		this.eventVehicleRegistration = eventVehicleRegistration;
	}
	
}
