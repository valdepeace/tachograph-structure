/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.tacografo.file.cardblockdriver.Sizes;

/**
 * 
 * 2.57. FullCardNumber
 *
 * Código que identifica por completo a una tarjeta de tacógrafo.
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
 * cardType es el tipo de tarjeta de tacógrafo.
 * 
 * cardIssuingMemberState es el código del Estado miembro que ha expedido la tarjeta.
 * 
 * cardNumber es el número de la tarjeta.
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
		EquipmentType et=new EquipmentType(Arrays.copyOfRange(bytes,start,start+=Sizes.CARDTYPE.getMax()));
		this.cardType= et.getEquipmentType();
		NationNumeric nn=new NationNumeric(Arrays.copyOfRange(bytes,start,start+=Sizes.CARDISSUINGMEMBERSTATE.getMax()));
		this.cardIssuingMemberState = nn.getNationNumeric();
		this.cardNumber=new CardNumber(Arrays.copyOfRange(bytes,start,start+=Sizes.CARDNUMBER.getMax()));
	}
	/**
	 * Obtiene el tipo de tarjeta de tacógrafo.
	 * @return the CardType
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * Asigna el tipo de tarjeta de tacógrafo.
	 * @param cardType
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * Obtiene el código del Estado miembro que ha expedido la tarjeta.
	 * @return the CardIssuingMemberState
	 */
	public String getCardIssuingMemberState() {
		return cardIssuingMemberState;
	}
	/**
	 * el código del Estado miembro que ha expedido la tarjeta.
	 * @param cardIssuingMemberState
	 */
	public void setCardIssuingMemberState(String cardIssuingMemberState) {
		this.cardIssuingMemberState = cardIssuingMemberState;
	}
	/**
	 * Obtiene el número de la tarjeta.
	 * @return cardNumber
	 */
	public CardNumber getCardNumber() {
		return cardNumber;
	}
	/**
	 * Asigna el número de la tarjeta.
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
