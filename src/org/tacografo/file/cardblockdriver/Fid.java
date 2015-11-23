/**
 * 
 */
package org.tacografo.file.cardblockdriver;

/**
 * Identificador de los ficheros elementales como propiedad a cada identificador 
 * tiene asociado su fid en hexadecimal
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public enum Fid {
	
	MF(0x3F00),  //MF Archivo principal (DF raíz)
		EF_IC(0x0002), // EF Archivo elemental
		EF_ICC(0x0005),
		DF_TACHOGRAPF(0x0500),  //DF Archivo dedicado. Un DF puede contener otros archivos (EF o DF)
			EF_APPLICATION_IDENTIFICATION(0x0501),
			EF_CARD_CERTIFICATE(0xC100),
			EF_CA_CERTIFICATE(0xC108),
			EF_IDENTIFICATION(0x0520),
			EF_CARD_DOWNLOAD(0x050E),
			EF_DRIVING_LICENSE_INFO(0x0521),
			EF_EVENTS_DATA(0x0502),
			EF_FAULTS_DATA(0x0503),
			EF_DRIVER_ACTIVITY_DATA(0x0504),
			EF_VEHICLES_USED(0x0505),
			EF_PLACES(0x0506),
			EF_CURRENT_USAGE(0x0507),
			EF_CONTROL_ACTIVITY_DATA(0x0508),
			EF_SPECIFIC_CONDITIONS(0x522);
	
	private final int id;
	
	Fid(int id){
		this.id=id;
	}
	
	/**
	 * Obtiene el identificador del fichero en hexadecimal
	 * @return hex
	 */
	public int getId(){return this.id;}
}
