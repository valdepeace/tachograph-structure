/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Clase abstracta de los bloques de datos de un fichero de conductor. Todo bloque de datos se resumen
 * en tag(fid)-logitud(size)-value(datos)
 *  
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
include=JsonTypeInfo.As.PROPERTY,
property="type")
@JsonSubTypes({	
@Type(value=CardIccIdentification.class, name="icc"),
@Type(value=CardChipIdentification.class, name="ic"),
@Type(value=DriverCardApplicationIdentification.class, name="application_identification"),
@Type(value=CardCertificate.class, name="card_certificate"),
@Type(value= MemberStateCertificate.class, name="ca_certificate"),
@Type(value= CardIdentification.class, name="identification"),
@Type(value= LastCardDownload.class, name="card_download"),
@Type(value= CardDrivingLicenceInformation.class, name="driving_lincense_info"),
@Type(value= CardEventData.class, name="event_data"),
@Type(value= CardFaultData.class, name="fault_data"),
@Type(value= CardDriverActivity.class, name="driver_activity_data"),
@Type(value= CardVehiclesUsed.class, name="vehicles_used"),
@Type(value= CardPlaceDailyWorkPeriod.class, name="places"),
@Type(value= CardCurrentUse.class, name="current_usage"),
@Type(value= CardControlActivityDataRecord.class, name="control_activity_data"),
@Type(value= SpecificConditionRecord.class, name="specific_conditions")

})

public abstract class CardBlockDriver {
	
	
	private String FID;	
	private int size;			
	@JsonIgnore
	private byte[] datos;

	/** 
	 *  Identificador del bloque segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISIÓN
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @return the fID
	 */
	public String getFID() {
		return FID;
	}

	/**
	 * Asigna Identificador del bloque segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISIÓN
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @param fID the fID to set
	 */
	public void setFID(String fID) {
		FID = fID;
	}



	/**
	 * Tamaño de cada bloque y sus propiedades segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISIÓN
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Asigna tamaño de cada bloque y sus propiedades segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISIÓN
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Retorna array de bytes de los datos del bloque
	 * @return the datos
	 */
	public byte[] getDatos() {
		return datos;
	}

	/**
	 * Asignamos el array de bytes al bloque
	 * @param datos the datos to set
	 */
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}
	
	/**
	 * Imprime por consola los datos en binario usado para el debug
	 */
	public void toBinaryString(){
		String s;
		for (Byte bite:this.datos){
			s = String.format("%8s", Integer.toBinaryString(bite & 0xFF)).replace(' ', '0');
			System.out.println(s);
		}
		//String s = String.format("%8s", Integer.toBinaryString(bite [0] & 0xFF)).replace(' ', '0');
		
	}
	/**
	 * Imprime por consola los datos en hexadecimal usado para el debug
	 */
	public void toHexString(){
		for (Byte bite:this.datos){
			System.out.println(Integer.toHexString(bite));
		}
	}
	/**
	 * Devuelve el bloque mapeado a json
	 * @return json
	 */
	public String toJson(){
		ObjectMapper mapper=new ObjectMapper();
		String str="";
		try {
			str=mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return str;
	}
	
	
}
