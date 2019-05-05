/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.tachographStructure.file.driverCardBlock.DriverCardSizes;
import com.tachographStructure.helpers.EquipmentType;

/**
 * 
 * 2.57. FullCardNumber
 *
 * C�digo que identifica por completo a una tarjeta de tac�grafo.
 * 
 * FullCardNumber::= SEQUENCE {
 * 
 * cardType EquipmentType,
 *
 * cardIssuingMemberState NationNumeric,
 *
 * cardNumber CardNumber
 *
 * }
 *
 * cardType es el tipo de tarjeta de tac�grafo.
 * 
 * cardIssuingMemberState es el c�digo del Estado miembro que ha expedido la tarjeta.
 * 
 * cardNumber es el n�mero de la tarjeta.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class FullCardNumber {
	private String cardType;
	private String cardIssuingMemberState;
	private CardNumber cardNumber;
	
	public FullCardNumber() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public FullCardNumber(byte[] bytes) throws UnsupportedEncodingException{
		int start=0;
		EquipmentType et=new EquipmentType(Arrays.copyOfRange(bytes,start,start+= DriverCardSizes.CARDTYPE.getMax()));
		this.cardType= et.getEquipmentType();
		NationNumeric nn=new NationNumeric(Arrays.copyOfRange(bytes,start,start+=DriverCardSizes.CARDISSUINGMEMBERSTATE.getMax()));
		this.cardIssuingMemberState = nn.getNationNumeric();
		this.cardNumber=new CardNumber(Arrays.copyOfRange(bytes,start,start+=DriverCardSizes.CARDNUMBER.getMax()));
	}
	/**
	 * Obtiene el tipo de tarjeta de tac�grafo.
	 * @return the CardType
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * Asigna el tipo de tarjeta de tac�grafo.
	 * @param cardType
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * Obtiene el c�digo del Estado miembro que ha expedido la tarjeta.
	 * @return the CardIssuingMemberState
	 */
	public String getCardIssuingMemberState() {
		return cardIssuingMemberState;
	}
	/**
	 * el c�digo del Estado miembro que ha expedido la tarjeta.
	 * @param cardIssuingMemberState
	 */
	public void setCardIssuingMemberState(String cardIssuingMemberState) {
		this.cardIssuingMemberState = cardIssuingMemberState;
	}
	/**
	 * Obtiene el n�mero de la tarjeta.
	 * @return cardNumber
	 */
	public CardNumber getCardNumber() {
		return cardNumber;
	}
	/**
	 * Asigna el n�mero de la tarjeta.
	 * @param cardNumber
	 */
	public void setCardNumber(CardNumber cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "\nFullCardNumber [cardType=" + cardType
				+ ", cardIssuingMemberState=" + cardIssuingMemberState
				+ ", cardNumber=" + cardNumber + "]";
	}
	
}
