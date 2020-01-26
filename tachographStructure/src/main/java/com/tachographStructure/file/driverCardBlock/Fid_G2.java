/**
 * 
 */
package com.tachographStructure.file.driverCardBlock;

/**
 * Identificador de los ficheros elementales como propiedad a cada identificador 
 * tiene asociado su fid en hexadecimal
 * 
 * @author Andres Carmona Gil
 * @version 0.0.2
 *
 */
public enum Fid_G2 {

	MF(0x3F00),  //MF Main File (DF root)
		EF_IC(0x0002), // EF Elemental File
		EF_ICC(0x0005),
		DF_TACHOGRAPF(0x0500),  //DF Dedicate File. A DF can to have other files (EF o DF)
			EF_APPLICATION_IDENTIFICATION(0x0501),
			EF_CARDMA_CERTIFICATE(0xC100),
			EF_CARDSIGNCERTIFICATE(0x101),
			EF_CA_CERTIFICATE(0xC108),
			EF_LINK_CERTIFICATE(0X109),
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
			EF_SPECIFIC_CONDITIONS(0x522),
			EF_VEHICLEUNITS_USED(0x523),
			EF_GNSS_PLACES(0x524);

	private final int id;

	Fid_G2(int id){
		this.id=id;
	}
	
	/**
	 * Obtiene el identificador del fichero en hexadecimal
	 * @return hex
	 */
	public int getId(){return this.id;}
}
