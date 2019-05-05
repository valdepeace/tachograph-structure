/**
 * 
 */
package com.tachographStructure.file.driverCardBlock;

import java.util.Arrays;
import java.util.Date;
import com.tachographStructure.file.Block;
import com.tachographStructure.file.driverCardBlock.subBlocks.VehicleRegistrationIdentification;
import com.tachographStructure.helpers.RealTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 2.12. CardCurrentUse
 * Informaci�n acerca del uso actual de la tarjeta (requisito 212).
 * CardCurrentUse ::= SEQUENCE {
 * sessionOpenTime TimeReal,
 * sessionOpenVehicle VehicleRegistrationIdentification
 * }
 * 
 * sessionOpenTime es la hora en que se inserta la tarjeta para el uso actual. Este elemento se pone a cero al extraer la
 * tarjeta.
 * 
 * sessionOpenVehicle es la identificaci�n del veh�culo que se est� utilizando actualmente. Este elemento se configura al
 * insertar la tarjeta y se pone a cero al extraer la tarjeta.
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class CardCurrentUse extends Block implements CardBlock {
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
	private Date sessionOpenTime; 
	private VehicleRegistrationIdentification sessionOpenVehicle;
	
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardCurrentUse(byte[] datos) {
		int start=0;
		this.sessionOpenTime= RealTime.getRealTime(Arrays.copyOfRange(datos, start, start+= DriverCardSizes.SESSIONOPENTIME.getMax()));
		this.sessionOpenVehicle=new VehicleRegistrationIdentification(Arrays.copyOfRange(datos, start, start+= DriverCardSizes.SESSIONOPENVEHICLE.getMax()));
	}

	/**
	 * Obtenemos la hora en que se inserta la tarjeta para el uso actual.
	 * @return the sessionOpenTime
	 */
	public Date getSessionOpenTime() {
		return sessionOpenTime;
	}

	/**
	 * Asignamos es la hora en que se inserta la tarjeta para el uso actual.
	 * @param sessionOpenTime the sessionOpenTime to set
	 */
	public void setSessionOpenTime(Date sessionOpenTime) {
		this.sessionOpenTime = sessionOpenTime;
	}

	/**
	 * Obtenemos  la identificaci�n del veh�culo que se est� utilizando actualmente
	 * @return the sessionOpenVehicle
	 */
	public VehicleRegistrationIdentification getSessionOpenVehicle() {
		return sessionOpenVehicle;
	}

	/**
	 * Asignamos  la identificaci�n del veh�culo que se est� utilizando actualmente
	 * @param sessionOpenVehicle the sessionOpenVehicle to set
	 */
	public void setSessionOpenVehicle(
			VehicleRegistrationIdentification sessionOpenVehicle) {
		this.sessionOpenVehicle = sessionOpenVehicle;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardCurrentUse [sessionOpenTime=" + sessionOpenTime
				+ ", sessionOpenVehicle=" + sessionOpenVehicle.toString() + "]";
	}
	
}
