/**
 * 
 */
package com.tachographStructure.file.driverCardBlock;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.tachographStructure.file.Block;
import com.tachographStructure.file.driverCardBlock.subBlocks.Name;
import com.tachographStructure.file.driverCardBlock.subBlocks.NationNumeric;
import com.tachographStructure.helpers.IA5String;

/**
 * 2.14. CardDrivingLicenceInformation
 * Informaci�n almacenada en una tarjeta de conductor y relativa a los datos correspondientes al permiso de conducir del
 * titular de la tarjeta (requisito 196).
 * CardDrivingLicenceInformation ::= SEQUENCE {
 * drivingLicenceIssuingAuthority Name,
 * drivingLicenceIssuingNation NationNumeric,
 * drivingLicenceNumber IA5String(SIZE(16))
 * }
 * drivingLicenceIssuingAuthority es la autoridad que expidi� el permiso de conducir.
 * drivingLicenceIssuingNation es la nacionalidad de la autoridad que expidi� el permiso de conducir.
 * drivingLicenceNumber es el n�mero del permiso de conducir.
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class CardDrivingLicenceInformation extends Block implements
		CardBlock {

	
	private String drivingLicenceIssuingAuthority;
	private String drivingLicenceIssuingNation;
	private String drivingLicenceNumber;
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardDrivingLicenceInformation(byte[] datos) throws UnsupportedEncodingException {
		int start = 0;
		Name n=new Name(Arrays.copyOfRange(datos,start, start+= DriverCardSizes.DRIVINGLICENCEISSUINGAUTHORITY.getMax()));
		this.drivingLicenceIssuingAuthority=n.getName();
		
		NationNumeric nn=new NationNumeric(Arrays.copyOfRange(datos,start, start+= DriverCardSizes.DRIVINGLICENCEISSUINGNATION.getMax()));
		this.drivingLicenceIssuingNation=nn.getNationNumeric();
		
		IA5String ia=new IA5String(Arrays.copyOfRange(datos,start, start+= DriverCardSizes.DRIVINGLICENCENUMBER.getMax()));
		this.drivingLicenceNumber=ia.getiA5String();
		
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardDrivingLicenceInformation [drivingLicenceIssuingAuthority="
				+ drivingLicenceIssuingAuthority
				+ ", drivingLicenceIssuingNation="
				+ drivingLicenceIssuingNation + ", drivingLicenceNumber="
				+ drivingLicenceNumber + "]";
	}


	/**
	 * Obtenemos la autoridad que expidi� el permiso de conducir.
	 * @return the drivingLicenceIssuingAuthority
	 */
	public String getDrivingLicenceIssuingAuthority() {
		return drivingLicenceIssuingAuthority;
	}

	/**
	 * Asignamos la autoridad que expidi� el permiso de conducir.
	 * @param drivingLicenceIssuingAuthority the drivingLicenceIssuingAuthority to set
	 */
	public void setDrivingLicenceIssuingAuthority(
			String drivingLicenceIssuingAuthority) {
		this.drivingLicenceIssuingAuthority = drivingLicenceIssuingAuthority;
	}

	/**
	 * Obtenemos la nacionalidad de la autoridad que expidi� el permiso de conducir.
	 * @return the drivingLicenceIssuingNation
	 */
	public String getDrivingLicenceIssuingNation() {
		return drivingLicenceIssuingNation;
	}

	/**
	 * Asignamos la nacionalidad de la autoridad que expidi� el permiso de conducir.
	 * @param drivingLicenceIssuingNation the drivingLicenceIssuingNation to set
	 */
	public void setDrivingLicenceIssuingNation(String drivingLicenceIssuingNation) {
		this.drivingLicenceIssuingNation = drivingLicenceIssuingNation;
	}

	/**
	 * Obtenemos el n�mero del permiso de conducir.
	 * @return the drivingLicenceNumber
	 */
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	/**
	 * Asignamos el n�mero del permiso de conducir.
	 * @param drivingLicenceNumber the drivingLicenceNumber to set
	 */
	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	
	
}
