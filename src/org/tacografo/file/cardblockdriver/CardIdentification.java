package org.tacografo.file.cardblockdriver;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.CardNumber;
import org.tacografo.file.cardblockdriver.subblock.Name;
import org.tacografo.file.cardblockdriver.subblock.NationNumeric;
import org.tacografo.tiposdatos.RealTime;

import com.fasterxml.jackson.annotation.JsonFormat;



/**
 * 
 * 2.20. CardIdentification
 *
 * Información almacenada en una tarjeta y relativa a la identificación de la tarjeta (requisitos 194, 215, 231, 235).
 * 
 * CardIdentification::= SEQUENCE
 * cardIssuingMemberState NationNumeric,
 * cardNumber CardNumber,
 * cardIssuingAuthorityName Name, 
 * cardIssueDate TimeReal,
 * cardValidityBegin TimeReal, 
 * cardExpiryDate TimeReal
 * }
 *
 * cardIssuingMemberState es el código del Estado miembro que expide la tarjeta.
 * cardNumber es el número de la tarjeta.
 * cardIssuingAuthorityName es el nombre de la autoridad que ha expedido la tarjeta.
 * cardIssueDate es la fecha en que se expidió la tarjeta al titular actual.
 * cardValidityBegin es la fecha correspondiente al primer día de validez de la tarjeta. 
 * cardExpiryDate es la fecha en que termina la validez de la tarjeta.
 * @author Andrés Carmona Gil
 * @version 0.0.1
 */
public class CardIdentification extends CardBlockDriver implements CardBlock{
	
	

	private String cardIssuingMemberState;
	
	private CardNumber cardNumber;
	
	private String cardIssuingAuthorityName;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date cardIssueDate; 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date cardValidityBegin;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date cardExpiryDate;
			
	
	
	
	public CardIdentification() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardIdentification(byte[] datos) throws UnsupportedEncodingException {
		//start indice array de bytef
		int start=0;
		
		NationNumeric nn=new NationNumeric(Arrays.copyOfRange(datos, start, start+=Sizes.CARDISSUINGMEMBERSTATE.getMax()));
		this.cardIssuingMemberState=nn.getNationNumeric();
		
		this.cardNumber=new CardNumber(Arrays.copyOfRange(datos,start,start+=Sizes.CARDNUMBER.getMax()));
		
		Name name=new Name(Arrays.copyOfRange(datos,start,start+=Sizes.CARDISSUINGAUTORITYNAME.getMax()));
		this.cardIssuingAuthorityName=name.getName();
		
		
		this.cardIssueDate= new Date();
		this.cardIssueDate=RealTime.getRealTime(Arrays.copyOfRange(datos,start, start+=Sizes.CARDISSUEDATE.getMax()));
		
		this.cardValidityBegin=new Date();
		this.cardValidityBegin=RealTime.getRealTime(Arrays.copyOfRange(datos,start, start+=Sizes.CARDVALIDITYBEGIN.getMax()));
		
		this.cardExpiryDate=new Date();
		this.cardExpiryDate=RealTime.getRealTime(Arrays.copyOfRange(datos,start, start+=Sizes.CARDEXPIRYDATE.getMax()));
		
		
		
				
	}
	

	/**
	 * Obtiene el código del Estado miembro que expide la tarjeta.
	 * @return the cardIssuingMemberState
	 */
	public String getCardIssuingMemberState() {
		return cardIssuingMemberState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardIdentification [cardIssuingMemberState="
				+ cardIssuingMemberState + ", cardNumber=" + cardNumber
				+ ", cardIssuingAuthorityName=" + cardIssuingAuthorityName
				+ ", cardIssueDate=" + cardIssueDate + ", cardValidityBegin="
				+ cardValidityBegin + ", cardExpiryDate=" + cardExpiryDate
				+ "]";
	}

	/**
	 * Asigna el código del Estado miembro que expide la tarjeta.
	 * @param cardIssuingMemberState the cardIssuingMemberState to set
	 */
	public void setCardIssuingMemberState(String cardIssuingMemberState) {
		this.cardIssuingMemberState = cardIssuingMemberState;
	}

	/**
	 * Obtiene el número de la tarjeta.
	 * @return the cardNumber
	 */
	public CardNumber getCardNumber() {
		return cardNumber;
	}

	/**
	 * Asigna el número de la tarjeta.
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(CardNumber cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Obtiene el nombre de la autoridad que ha expedido la tarjeta.
	 * @return the cardIssuingAuthorityName
	 */
	public String getCardIssuingAuthorityName() {
		return cardIssuingAuthorityName;
	}

	/**
	 * Asigna el nombre de la autoridad que ha expedido la tarjeta.
	 * @param cardIssuingAuthorityName the cardIssuingAuthorityName to set
	 */
	public void setCardIssuingAuthorityName(String cardIssuingAuthorityName) {
		this.cardIssuingAuthorityName = cardIssuingAuthorityName;
	}

	/**
	 * Obtiene la fecha en que se expidió la tarjeta al titular actual.
	 * @return the cardIssueDate
	 */
	public Date getCardIssueDate() {
		return cardIssueDate;
	}

	/**
	 * Asigna la fecha en que se expidió la tarjeta al titular actual.
	 * @param cardIssueDate the cardIssueDate to set
	 */
	public void setCardIssueDate(Date cardIssueDate) {
		this.cardIssueDate = cardIssueDate;
	}

	/**
	 * Obtiene la fecha correspondiente al primer día de validez de la tarjeta. 
	 * @return the cardValidityBegin
	 */
	public Date getCardValidityBegin() {
		return cardValidityBegin;
	}

	/**
	 * Asigna la fecha correspondiente al primer día de validez de la tarjeta. 
	 * @param cardValidityBegin the cardValidityBegin to set
	 */
	public void setCardValidityBegin(Date cardValidityBegin) {
		this.cardValidityBegin = cardValidityBegin;
	}

	/**
	 * Obtiene la fecha en que termina la validez de la tarjeta.
	 * @return the cardExpiryDate
	 */
	public Date getCardExpiryDate() {
		return cardExpiryDate;
	}

	/**
	 * Asigna la fecha en que termina la validez de la tarjeta.
	 * @param cardExpiryDate the cardExpiryDate to set
	 */
	public void setCardExpiryDate(Date cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	
	
	
	
}
