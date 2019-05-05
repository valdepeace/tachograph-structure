/**
 * 
 */
package com.tachograph;

/**
 * Identificador de los ficheros elementales como propiedad a cada identificador 
 * tiene asociado su fid en hexadecimal
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public enum Fid {
	
	MF("3f,00"),  //MF Archivo principal (DF ra�z)
		EF_IC("00,02"), // EF Archivo elemental
		EF_ICC("00,05"),
		DF_TACHOGRAPF("05,00"),  //DF Archivo dedicado. Un DF puede contener otros archivos (EF o DF)
			EF_APPLICATION_IDENTIFICATION("05,01"),  // signature
			EF_CARD_CERTIFICATE("C1,00"),
			EF_CA_CERTIFICATE("C1,08"),
			EF_IDENTIFICATION("05,20"),			// signature
			EF_CARD_DOWNLOAD("05,0E"),
			EF_DRIVING_LICENSE_INFO("05,21"),	//signature
			EF_EVENTS_DATA("05,02"),			//signature
			EF_FAULTS_DATA("05,03"),			//signature
			EF_DRIVER_ACTIVITY_DATA("05,04"),	//signature
			EF_VEHICLES_USED("05,05"),			//signature
			EF_PLACES("05,06"),					//signature
			EF_CURRENT_USAGE("05,07"),			//signature
			EF_CONTROL_ACTIVITY_DATA("05,08"),	//signature
			EF_SPECIFIC_CONDITIONS("05,22");	//signature
	
	private final String id;
	
	Fid(String id){
		this.id = id;
	}
	
	/**
	 * Obtiene el identificador del fichero en hexadecimal
	 * @return hex
	 */
	public String getId(){return this.id;}
}
