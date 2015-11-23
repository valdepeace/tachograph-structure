/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.tacografo.file.cardblockdriver.subblock.Name;
import org.tacografo.file.cardblockdriver.subblock.NationNumeric;
import org.tacografo.tiposdatos.IA5String;

/**
 * 2.14. CardDrivingLicenceInformation
 * Información almacenada en una tarjeta de conductor y relativa a los datos correspondientes al permiso de conducir del
 * titular de la tarjeta (requisito 196).
 * CardDrivingLicenceInformation ::= SEQUENCE {
 * drivingLicenceIssuingAuthority Name,
 * drivingLicenceIssuingNation NationNumeric,
 * drivingLicenceNumber IA5String(SIZE(16))
 * }
 * drivingLicenceIssuingAuthority es la autoridad que expidió el permiso de conducir.
 * drivingLicenceIssuingNation es la nacionalidad de la autoridad que expidió el permiso de conducir.
 * drivingLicenceNumber es el número del permiso de conducir.
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class CardDrivingLicenceInformation extends CardBlockDriver implements
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
		Name n=new Name(Arrays.copyOfRange(datos,start, start+=Sizes.DRIVINGLICENCEISSUINGAUTHORITY.getMax()));
		this.drivingLicenceIssuingAuthority=n.getName();
		
		NationNumeric nn=new NationNumeric(Arrays.copyOfRange(datos,start, start+=Sizes.DRIVINGLICENCEISSUINGNATION.getMax()));
		this.drivingLicenceIssuingNation=nn.getNationNumeric();
		
		IA5String ia=new IA5String(Arrays.copyOfRange(datos,start, start+=Sizes.DRIVINGLICENCENUMBER.getMax()));
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
	 * Obtenemos la autoridad que expidió el permiso de conducir.
	 * @return the drivingLicenceIssuingAuthority
	 */
	public String getDrivingLicenceIssuingAuthority() {
		return drivingLicenceIssuingAuthority;
	}

	/**
	 * Asignamos la autoridad que expidió el permiso de conducir.
	 * @param drivingLicenceIssuingAuthority the drivingLicenceIssuingAuthority to set
	 */
	public void setDrivingLicenceIssuingAuthority(
			String drivingLicenceIssuingAuthority) {
		this.drivingLicenceIssuingAuthority = drivingLicenceIssuingAuthority;
	}

	/**
	 * Obtenemos la nacionalidad de la autoridad que expidió el permiso de conducir.
	 * @return the drivingLicenceIssuingNation
	 */
	public String getDrivingLicenceIssuingNation() {
		return drivingLicenceIssuingNation;
	}

	/**
	 * Asignamos la nacionalidad de la autoridad que expidió el permiso de conducir.
	 * @param drivingLicenceIssuingNation the drivingLicenceIssuingNation to set
	 */
	public void setDrivingLicenceIssuingNation(String drivingLicenceIssuingNation) {
		this.drivingLicenceIssuingNation = drivingLicenceIssuingNation;
	}

	/**
	 * Obtenemos el número del permiso de conducir.
	 * @return the drivingLicenceNumber
	 */
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	/**
	 * Asignamos el número del permiso de conducir.
	 * @param drivingLicenceNumber the drivingLicenceNumber to set
	 */
	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	
	
}
