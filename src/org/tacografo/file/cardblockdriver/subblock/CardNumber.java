/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.tacografo.tiposdatos.IA5String;

/**
 * 2.21. CardNumber
 *
 * Un número de tarjeta, según se indica en la definición : Una secuencia de 16 caracteres alfanuméricos que identifican 
 * una tarjeta de tacógrafo en un Estado miembro. El número de tarjeta incluye un índice consecutivo (en su caso), un índice de sustitución y un índice de renovación.
 *
 * Por consiguiente, cada tarjeta se identifica con el código del Estado miembro que la asigna y con el número de la propia tarjeta..
 *
 * CardNumber::= CHOICE {
 *
 * SEQUENCE {
 * 
 * driverIdentification IA5String(SIZE(14)),
 * 
 * cardReplacementIndex CardReplacementIndex,
 *
 * cardRenewalIndex CardRenewalIndex
 *
 * }
 * 
 * SEQUENCE {
 *
 * ownerIdentification IA5String(SIZE(13)),
 * 
 * cardConsecutiveIndex CardConsecutiveIndex,
 * 
 * cardReplacementIndex CardReplacementIndex,
 * 
 * cardRenewalIndex CardRenewalIndex
 *
 * }
 * 
 * }
 *
 * driverIdentification es la identificación exclusiva de un conductor en un Estado miembro.
 *
 * ownerIdentification es la identificación exclusiva de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
 *
 * cardConsecutiveIndex es el índice consecutivo de la tarjeta.
 *
 * cardReplacementIndex es el índice de sustitución de la tarjeta.
 *
 * cardRenewalIndex es el índice de renovación de la tarjeta.
 *
 * La primera de las dos secuencias a elegir sirve para codificar el número de una tarjeta de conductor, mientras que la segunda secuencia sirve 
 * para codificar el número de una tarjeta de centro de ensayo, de una tarjeta de control y de una tarjeta de empresa.
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class CardNumber {

	
	// tarjeta conductor
	private String driverIdentification;
	private String drivercardReplacementIndex;
	private String drivercardRenewalIndex;
	// tarjeta de centro de ensayo, de una tarjeta de control y de una tarjeta de empresa
	private IA5String ownerIdentification;
	private String cardConsecutiveIndex;
	private String cardReplacementIndex;
	private String cardRenewalIndex;
	
	
	public CardNumber() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardNumber(byte[] datos) throws UnsupportedEncodingException {
		// start indice de vector de bytes
		int start=0;
		
		IA5String ia=new IA5String(Arrays.copyOfRange(datos, start,start+=14));
		this.driverIdentification=ia.getiA5String();
		
		ia=new IA5String(Arrays.copyOfRange(datos,start, start+=1));
		this.drivercardReplacementIndex=ia.getiA5String();
		
		ia=new IA5String(Arrays.copyOfRange(datos,start,start+=1));
		this.drivercardRenewalIndex=ia.getiA5String();
	}
	
	public String toString(){
		String cadena="";
		cadena=this.driverIdentification+this.drivercardReplacementIndex+this.drivercardRenewalIndex;
		return cadena;
	}

	/**
	 * Obtiene la identificación exclusiva de un conductor en un Estado miembro.
	 * @return the driverIdentification
	 */
	public String getDriverIdentification() {
		return driverIdentification;
	}

	/**
	 * Asigna la identificación exclusiva de un conductor en un Estado miembro.
	 * @param driverIdentification the driverIdentification to set
	 */
	public void setDriverIdentification(String driverIdentification) {
		this.driverIdentification = driverIdentification;
	}

	/**
	 * Obtiene el índice de sustitución de la tarjeta conductor.
	 * @return the drivercardReplacementIndex
	 */
	public String getDrivercardReplacementIndex() {
		return drivercardReplacementIndex;
	}

	/**
	 * Asigna el índice de sustitución de la tarjeta del conductor.
	 * @param drivercardReplacementIndex the drivercardReplacementIndex to set
	 */
	public void setDrivercardReplacementIndex(String drivercardReplacementIndex) {
		this.drivercardReplacementIndex = drivercardReplacementIndex;
	}

	/**
	 * Obtiene el índice de renovación de la tarjeta del conductor.
	 * @return the drivercardRenewalIndex
	 */
	public String getDrivercardRenewalIndex() {
		return drivercardRenewalIndex;
	}

	/**
	 * Asigna el índice de renovación de la tarjeta del conductor.
	 * @param drivercardRenewalIndex the drivercardRenewalIndex to set
	 */
	public void setDrivercardRenewalIndex(String drivercardRenewalIndex) {
		this.drivercardRenewalIndex = drivercardRenewalIndex;
	}

	/**
	 * Obtiene la identificación exclusiva de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @return the ownerIdentification
	 */
	public IA5String getOwnerIdentification() {
		return ownerIdentification;
	}

	/**
	 * Asigna la identificación exclusiva de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @param ownerIdentification the ownerIdentification to set
	 */
	public void setOwnerIdentification(IA5String ownerIdentification) {
		this.ownerIdentification = ownerIdentification;
	}

	/**
	 * Obtiene el índice consecutivo de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro..
	 * @return the cardConsecutiveIndex
	 */
	public String getCardConsecutiveIndex() {
		return cardConsecutiveIndex;
	}

	/**
	 * Asigna el índice consecutivo de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro..
	 * @param cardConsecutiveIndex the cardConsecutiveIndex to set
	 */
	public void setCardConsecutiveIndex(String cardConsecutiveIndex) {
		this.cardConsecutiveIndex = cardConsecutiveIndex;
	}

	/**
	 * Obtiene el índice de sustitución de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @return the cardReplacementIndex
	 */
	public String getCardReplacementIndex() {
		return cardReplacementIndex;
	}

	/**
	 * Asigna de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @param cardReplacementIndex the cardReplacementIndex to set
	 */
	public void setCardReplacementIndex(String cardReplacementIndex) {
		this.cardReplacementIndex = cardReplacementIndex;
	}

	/**
	 * Obtiene el índice de renovación de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @return the cardRenewalIndex
	 */
	public String getCardRenewalIndex() {
		return cardRenewalIndex;
	}

	/**
	 * Asigna el índice de renovación de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @param cardRenewalIndex the cardRenewalIndex to set
	 */
	public void setCardRenewalIndex(String cardRenewalIndex) {
		this.cardRenewalIndex = cardRenewalIndex;
	}
	
	

	
}
