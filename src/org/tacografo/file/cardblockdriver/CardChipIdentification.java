package org.tacografo.file.cardblockdriver;

import java.util.Arrays;

import org.tacografo.tiposdatos.OctetString;


/**
 * 2.9. CardChipIdentification
 * Información almacenada en una tarjeta y relativa a la identificación del circuito integrado (CI) de dicha tarjeta (requisito 191).
 * CardChipIdentification::= SEQUENCE {
 * icSerialNumber OCTET STRING (SIZE(4)),
 * icManufacturingReferences OCTET STRING (SIZE(4))
 * }
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 **/


public class CardChipIdentification extends CardBlockDriver implements CardBlock{
	

	private String icSerialNumber;
	

	private String icManufacturingReferneces;
	
	
	public CardChipIdentification(){
		
		
	}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardChipIdentification(byte[] datos) {
		
		int start=0;
		OctetString os=new OctetString(Arrays.copyOfRange(datos,start,start+Sizes.ICSERIALNUMBER.getMax()));
		this.icSerialNumber= os.getOctetString();
		start+=Sizes.ICSERIALNUMBER.getMax();
		
		os=new OctetString(Arrays.copyOfRange(datos,start,start+Sizes.ICMANUFACTURINGREFENCES.getMax()));
		this.icManufacturingReferneces=os.getOctetString();
		
	}


	/**
	 * @return the icSerialNumber
	 */
	public String getIcSerialNumber() {
		return icSerialNumber;
	}


	/**
	 * @param icSerialNumber the icSerialNumber to set
	 */
	public void setIcSerialNumber(String icSerialNumber) {
		this.icSerialNumber = icSerialNumber;
	}


	/**
	 * @return the icManufacturingReferneces
	 */
	public String getIcManufacturingReferneces() {
		return icManufacturingReferneces;
	}


	/**
	 * @param icManufacturingReferneces the icManufacturingReferneces to set
	 */
	public void setIcManufacturingReferneces(String icManufacturingReferneces) {
		this.icManufacturingReferneces = icManufacturingReferneces;
	}
	
	public String toString(){
		String cadena;
		cadena="************************** FID = "+this.getFID()+
				"\nicSerialNumber:"+this.icSerialNumber+
				"\nicManufacturingreferences:"+this.icManufacturingReferneces;
			
		return cadena;
		
		
	}
	
	
}
