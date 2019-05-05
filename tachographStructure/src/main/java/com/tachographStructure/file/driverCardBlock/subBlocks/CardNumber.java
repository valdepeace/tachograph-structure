/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.tachographStructure.helpers.IA5String;

/**
 * 2.21. CardNumber
 *
 * Un n�mero de tarjeta, seg�n se indica en la definici�n : Una secuencia de 16 caracteres alfanum�ricos que identifican 
 * una tarjeta de tac�grafo en un Estado miembro. El n�mero de tarjeta incluye un �ndice consecutivo (en su caso), un �ndice de sustituci�n y un �ndice de renovaci�n.
 *
 * Por consiguiente, cada tarjeta se identifica con el c�digo del Estado miembro que la asigna y con el n�mero de la propia tarjeta..
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
 * driverIdentification es la identificaci�n exclusiva de un conductor en un Estado miembro.
 *
 * ownerIdentification es la identificaci�n exclusiva de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
 *
 * cardConsecutiveIndex es el �ndice consecutivo de la tarjeta.
 *
 * cardReplacementIndex es el �ndice de sustituci�n de la tarjeta.
 *
 * cardRenewalIndex es el �ndice de renovaci�n de la tarjeta.
 *
 * La primera de las dos secuencias a elegir sirve para codificar el n�mero de una tarjeta de conductor, mientras que la segunda secuencia sirve 
 * para codificar el n�mero de una tarjeta de centro de ensayo, de una tarjeta de control y de una tarjeta de empresa.
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
	 * Obtiene la identificaci�n exclusiva de un conductor en un Estado miembro.
	 * @return the driverIdentification
	 */
	public String getDriverIdentification() {
		return driverIdentification;
	}

	/**
	 * Asigna la identificaci�n exclusiva de un conductor en un Estado miembro.
	 * @param driverIdentification the driverIdentification to set
	 */
	public void setDriverIdentification(String driverIdentification) {
		this.driverIdentification = driverIdentification;
	}

	/**
	 * Obtiene el �ndice de sustituci�n de la tarjeta conductor.
	 * @return the drivercardReplacementIndex
	 */
	public String getDrivercardReplacementIndex() {
		return drivercardReplacementIndex;
	}

	/**
	 * Asigna el �ndice de sustituci�n de la tarjeta del conductor.
	 * @param drivercardReplacementIndex the drivercardReplacementIndex to set
	 */
	public void setDrivercardReplacementIndex(String drivercardReplacementIndex) {
		this.drivercardReplacementIndex = drivercardReplacementIndex;
	}

	/**
	 * Obtiene el �ndice de renovaci�n de la tarjeta del conductor.
	 * @return the drivercardRenewalIndex
	 */
	public String getDrivercardRenewalIndex() {
		return drivercardRenewalIndex;
	}

	/**
	 * Asigna el �ndice de renovaci�n de la tarjeta del conductor.
	 * @param drivercardRenewalIndex the drivercardRenewalIndex to set
	 */
	public void setDrivercardRenewalIndex(String drivercardRenewalIndex) {
		this.drivercardRenewalIndex = drivercardRenewalIndex;
	}

	/**
	 * Obtiene la identificaci�n exclusiva de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @return the ownerIdentification
	 */
	public IA5String getOwnerIdentification() {
		return ownerIdentification;
	}

	/**
	 * Asigna la identificaci�n exclusiva de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @param ownerIdentification the ownerIdentification to set
	 */
	public void setOwnerIdentification(IA5String ownerIdentification) {
		this.ownerIdentification = ownerIdentification;
	}

	/**
	 * Obtiene el �ndice consecutivo de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro..
	 * @return the cardConsecutiveIndex
	 */
	public String getCardConsecutiveIndex() {
		return cardConsecutiveIndex;
	}

	/**
	 * Asigna el �ndice consecutivo de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro..
	 * @param cardConsecutiveIndex the cardConsecutiveIndex to set
	 */
	public void setCardConsecutiveIndex(String cardConsecutiveIndex) {
		this.cardConsecutiveIndex = cardConsecutiveIndex;
	}

	/**
	 * Obtiene el �ndice de sustituci�n de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
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
	 * Obtiene el �ndice de renovaci�n de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @return the cardRenewalIndex
	 */
	public String getCardRenewalIndex() {
		return cardRenewalIndex;
	}

	/**
	 * Asigna el �ndice de renovaci�n de la tarjeta de una empresa, de un centro de ensayo o de un organismo de control en un Estado miembro.
	 * @param cardRenewalIndex the cardRenewalIndex to set
	 */
	public void setCardRenewalIndex(String cardRenewalIndex) {
		this.cardRenewalIndex = cardRenewalIndex;
	}
	
	

	
}
