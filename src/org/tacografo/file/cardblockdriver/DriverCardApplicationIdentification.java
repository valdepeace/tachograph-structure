/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import java.util.Arrays;

import org.tacografo.file.cardblockdriver.subblock.CardActivityLengthRange;
import org.tacografo.file.cardblockdriver.subblock.CardStructureVersion;
import org.tacografo.file.cardblockdriver.subblock.EquipmentType;
import org.tacografo.file.cardblockdriver.subblock.NoOfCardPlaceRecords;
import org.tacografo.file.cardblockdriver.subblock.NoOfCardVehicleRecords;
import org.tacografo.file.cardblockdriver.subblock.NoOfEventsPerType;
import org.tacografo.file.cardblockdriver.subblock.NoOfFaultsPerType;

/**
 *	2.49. DriverCardApplicationIdentification
 *
 *	Información almacenada en una tarjeta de conductor y relativa a la identificación de la aplicación de la tarjeta (requisito
 *	190).
 *
 *	DriverCardApplicationIdentification ::= SEQUENCE {
 *	typeOfTachographCardId EquipmentType,
 *	cardStructureVersion CardStructureVersion,
 *	noOfEventsPerType NoOfEventsPerType,
 *	noOfFaultsPerType NoOfFaultsPerType,
 *	activityStructureLength CardActivityLengthRange,
 *	noOfCardVehicleRecords NoOfCardVehicleRecords,
 *	noOfCardPlaceRecords NoOfCardPlaceRecords
 *	}
 *
 *	typeOfTachographCardId especifica el tipo de tarjeta utilizado(conductor,empresa,....).
 *	cardStructureVersion especifica la versión de la estructura que se utiliza en la tarjeta.
 *	noOfEventsPerType es el número de incidentes de cada tipo que puede registrar la tarjeta.
 *	noOfFaultsPerType es el número de fallos de cada tipo que puede registrar la tarjeta.
 *	activityStructureLength indica el número de bytes disponibles para almacenar registros de actividad.
 *	noOfCardVehicleRecords es el número de registros del vehículo que caben en la tarjeta.
 *	noOfCardPlaceRecords es el número de lugares que puede registrar la tarjeta.
 * 	}
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class DriverCardApplicationIdentification extends CardBlockDriver
		implements CardBlock {
	
	private EquipmentType typeOfTachographCardId ;
	private CardStructureVersion cardStructureVersion ;
	private NoOfEventsPerType noOfEventsPerType ;
	private NoOfFaultsPerType noOfFaultsPerType ;
	private CardActivityLengthRange activityStructureLength;
	private NoOfCardVehicleRecords noOfCardVehicleRecords;
	private NoOfCardPlaceRecords noOfCardPlaceRecords;
	
	
	public DriverCardApplicationIdentification(){}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public DriverCardApplicationIdentification(byte[] datos){
		int start=0;
		this.typeOfTachographCardId=new EquipmentType(Arrays.copyOfRange(datos, start, start+=Sizes.TYPEOFTACHOGRAPHCARDID.getMax()));
		this.cardStructureVersion=new CardStructureVersion(Arrays.copyOfRange(datos, start, start+=Sizes.CARDSTRUCTUREVERSION.getMax()));
		this.noOfEventsPerType=new NoOfEventsPerType(Arrays.copyOfRange(datos, start, start+=Sizes.NOOFEVENTSPERTYPE.getMax()));
		this.noOfFaultsPerType=new NoOfFaultsPerType(Arrays.copyOfRange(datos,start,start+=Sizes.NOOFFAULTSPERTYPE.getMax()));
		this.activityStructureLength=new CardActivityLengthRange(Arrays.copyOfRange(datos, start, start+=Sizes.ACTIVITYSTRUCTURELENGTH.getMax()));
		this.noOfCardVehicleRecords=new NoOfCardVehicleRecords(Arrays.copyOfRange(datos, start, start+=Sizes.NOOFCARDVEHICLERECORDS.getMax()));
		this.noOfCardPlaceRecords=new NoOfCardPlaceRecords(Arrays.copyOfRange(datos,start, start+=Sizes.NOOFCARDPLACERECORDS.getMax()));
	}
	public String toString(){
		String cadena="";
		cadena = "\ntypeoftachograghcardid: "+this.typeOfTachographCardId.getEquipmentType()+
				"\ncardstructureVersion: "+this.cardStructureVersion.getCardStructureVersion()+
				" \nnofoeventspertype: "+this.noOfEventsPerType.getNoOfEventsPerType()+
				"\nooffaultspertype:"+this.noOfFaultsPerType.getNoOfFaultsPerType()+
				"\nacitivitystructurelength:"+this.activityStructureLength.getCardActivityLengthRange()+
				"\nnoofcardvehiclerecords: "+this.noOfCardVehicleRecords.getNoOfCardVehicleRecords()+
				"\nnofocardplacerecords:"+this.noOfCardPlaceRecords.getNoOfCardPlaceRecords();
				
		return cadena;
	}
	/**
	 * Obtiene el tipo de tarjeta utilizado(conductor,empresa,....).
	 * @return the typeOfTachographCardId
	 */
	public EquipmentType getTypeOfTachographCardId() {
		return typeOfTachographCardId;
	}
	/**
	 * Asigna el tipo de tarjeta utilizado(conductor,empresa,....).
	 * @param typeOfTachographCardId the typeOfTachographCardId to set
	 */
	public void setTypeOfTachographCardId(EquipmentType typeOfTachographCardId) {
		this.typeOfTachographCardId = typeOfTachographCardId;
	}
	/**
	 * Obtiene la versión de la estructura que se utiliza en la tarjeta.
	 * @return the cardStructureVersion
	 */
	public CardStructureVersion getCardStructureVersion() {
		return cardStructureVersion;
	}
	/**
	 * Asigna la versión de la estructura que se utiliza en la tarjeta.
	 * @param cardStructureVersion the cardStructureVersion to set
	 */
	public void setCardStructureVersion(CardStructureVersion cardStructureVersion) {
		this.cardStructureVersion = cardStructureVersion;
	}
	/**
	 * Obtiene el número de incidentes de cada tipo que puede registrar la tarjeta.
	 * @return the noOfEventsPerType
	 */
	public NoOfEventsPerType getNoOfEventsPerType() {
		return noOfEventsPerType;
	}
	/**
	 * Asigna el número de incidentes de cada tipo que puede registrar la tarjeta.
	 * @param noOfEventsPerType the noOfEventsPerType to set
	 */
	public void setNoOfEventsPerType(NoOfEventsPerType noOfEventsPerType) {
		this.noOfEventsPerType = noOfEventsPerType;
	}
	/**
	 * Obtiene el número de fallos de cada tipo que puede registrar la tarjeta.
	 * @return the noOfFaultsPerType
	 */
	public NoOfFaultsPerType getNoOfFaultsPerType() {
		return noOfFaultsPerType;
	}
	/**
	 * Asigna el número de fallos de cada tipo que puede registrar la tarjeta.
	 * @param noOfFaultsPerType the noOfFaultsPerType to set
	 */
	public void setNoOfFaultsPerType(NoOfFaultsPerType noOfFaultsPerType) {
		this.noOfFaultsPerType = noOfFaultsPerType;
	}
	/**
	 * Obtiene indica el número de bytes disponibles para almacenar registros de actividad.
	 * @return the activityStructureLength
	 */
	public CardActivityLengthRange getActivityStructureLength() {
		return activityStructureLength;
	}
	/**
	 * Asigna indica el número de bytes disponibles para almacenar registros de actividad.
	 * @param activityStructureLength the activityStructureLength to set
	 */
	public void setActivityStructureLength(
			CardActivityLengthRange activityStructureLength) {
		this.activityStructureLength = activityStructureLength;
	}
	/**
	 * Obtiene es el número de registros del vehículo que caben en la tarjeta.
	 * @return the noOfCardVehicleRecords
	 */
	public NoOfCardVehicleRecords getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}
	/**
	 * Asigna es el número de registros del vehículo que caben en la tarjeta.
	 * @param noOfCardVehicleRecords the noOfCardVehicleRecords to set
	 */
	public void setNoOfCardVehicleRecords(
			NoOfCardVehicleRecords noOfCardVehicleRecords) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
	}
	/**
	 * Obtiene el número de lugares que puede registrar la tarjeta.
	 * @return the noOfCardPlaceRecords
	 */
	public NoOfCardPlaceRecords getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}
	/**
	 * Asigna el número de lugares que puede registrar la tarjeta.
	 * @param noOfCardPlaceRecords the noOfCardPlaceRecords to set
	 */
	public void setNoOfCardPlaceRecords(NoOfCardPlaceRecords noOfCardPlaceRecords) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	} 
	
	
	
}
