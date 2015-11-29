/**
 * 
 */
package org.tacografo.file.vublock.subblock;

import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.Block;
import org.tacografo.file.cardblockdriver.subblock.VehicleRegistrationIdentification;
import org.tacografo.file.vublock.Sizes;
import org.tacografo.tiposdatos.RealTime;

/**
 * @author Andres Carmona Gil
 * @verion 0.0.1
 *
 * 2.85.   PreviousVehicleInfo
 * Información relativa al vehículo que utilizara previamente un conductor, cuando inserta su tarjeta en una unidad intravehicular (requisito 081).
 * PreviousVehicleInfo ::= SEQUENCE {
 * vehicleRegistrationIdentification VehicleRegistrationIdentification,
 * cardWithdrawalTime TimeReal
 * }
 * vehicleRegistrationIdentification es el VRN y el nombre del Estado miembro donde se matriculara el vehículo.
 * cardWithdrawalTime es la fecha y la hora de extracción de la tarjeta.
 */
public class PreviousVehicleInfo extends Block{
	
	
	
	private VehicleRegistrationIdentification vehicleRegistrationIdentification;
	
	private Date cardWithdrawalTime;

	public PreviousVehicleInfo(byte[] bytes) {
		int start=0;
		this.vehicleRegistrationIdentification=new VehicleRegistrationIdentification(Arrays.copyOfRange(bytes, start, start+=Sizes.VEHICLEREGISTRATIONIDENTIFICATION_TREP2.getSize()));
		this.cardWithdrawalTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.CARDWITHDRAWALTIME_VUCARDWDATA.getSize()));
	}
	
	

}
