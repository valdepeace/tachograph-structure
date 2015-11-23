/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.Arrays;

import org.tacografo.tiposdatos.Number;
import org.tacografo.tiposdatos.OctetString;

/**
 * 
 * 2.114. VehicleRegistrationNumber
 *
 * Número de matrícula del vehículo (VRN). El número de matrícula lo asigna la autoridad de matriculación de vehículos.
 * 
 * VehicleRegistrationNumber::= SEQUENCE {
 *
 * codePage INTEGER (0..255),
 *
 * vehicleRegNumber OCTET STRING (SIZE(13))
 *
 * }
 *
 * codePage especifica la parte de la norma ISO/IEC 8859 que se utiliza para codificar el vehicleRegNumber.
 *
 * vehicleRegNumber es un VRN codificado con arreglo a la norma ISO/IEC 8859-codePage.
 * 
 * Asignación de valor: específica de cada país.
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 */
public class VehicleRegistrationNumber {
	
	private short codePage;
	private String vehicleRegNumber;
	
	
	
	
	public VehicleRegistrationNumber() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public VehicleRegistrationNumber(byte[] bytes) {
		this.codePage=Number.getShort_8(Arrays.copyOfRange(bytes, 0, 1));
		
		OctetString os=new OctetString(Arrays.copyOfRange(bytes, 1, 14),this.codePage);
		this.vehicleRegNumber=os.getOctetString().trim();
		
	}

	/**
	 * Obtiene un VRN codificado con arreglo a la norma ISO/IEC 8859-codePage.
	 * (code-page pude ser de 1 a 15, segun ISO/IEC 8859).
	 * @return the vehicleRegNumber
	 */
	public String getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	/**
	 * Asigna un numero un VRN codificado con arreglo a la norma ISO/IEC 8859-codePage.
	 * (code-page pude ser de 1 a 15, segun ISO/IEC 8859).
	 * @param vehicleRegNumber the vehicleRegNumber to set
	 */
	public void setVehicleRegNumber(String vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber;
	}
	/**
	 * Obtiene el codigo de la norma ISO/IEC 8859-codePage, que va de 1-15.
	 * @return the codePage
	 */
	public short getCodePage() {
		return codePage;
	}
	/**
	 * Asigna el codigo de la norma ISO/IEC 8859-codePage, que va de 1-15.
	 * @param codePage the codePage to set
	 */
	public void setCodePage(short codePage) {
		this.codePage = codePage;
	}
	
	
	
}
