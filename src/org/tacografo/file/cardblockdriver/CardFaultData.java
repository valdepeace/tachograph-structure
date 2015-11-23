/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import java.util.ArrayList;
import java.util.Arrays;

import org.tacografo.file.cardblockdriver.subblock.CardFaultRecord;

/**
 * 2.17. CardFaultData
 * Información almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a los fallos asociados
 * al titular de la tarjeta (requisitos 207 y 223).
 * CardFaultData ::= SEQUENCE SIZE(2) OF {
 * cardFaultRecords SET SIZE(NoOfFaultsPerType) OF
 * CardFaultRecord
 * }
 * CardFaultData es una secuencia integrada por un conjunto con los registros de los fallos del aparato de control,
 * seguido de un conjunto con los registros de los fallos de la tarjeta.
 * 
 * cardFaultRecords es un conjunto de registros de fallos de una categoría determinada (del aparato de control o de la
 * tarjeta).
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class CardFaultData extends CardBlockDriver implements CardBlock {
	
	@SuppressWarnings("rawtypes")
	private ArrayList cardFaultRecords;
	
	
	public CardFaultData() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	@SuppressWarnings("unchecked")
	public CardFaultData(byte[] datos) {
		
		int start=0;		
		this.cardFaultRecords=new ArrayList<CardFaultRecord>();
		for (int i=0;i<datos.length;i+=Sizes.CARDFAULTRECORD.getMax()){						
			CardFaultRecord cer=new CardFaultRecord(Arrays.copyOfRange(datos,i,start+=Sizes.CARDFAULTRECORD.getMax()));
			this.cardFaultRecords.add(cer);
		}				
	}

	/**
	 * Obtiene un conjunto de registros de fallos de una categoría determinada (del aparato de control o de la
	 *	tarjeta).
	 * @return the cardFaultRecords
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CardFaultRecord> getCardFaultRecords() {
		return cardFaultRecords;
	}

	/**
	 * Asigna un conjunto de registros de fallos de una categoría determinada (del aparato de control o de la
	 * tarjeta).
	 * @param cardFaultRecords the cardFaultRecords to set
	 */
	public void setCardFaultRecords(ArrayList<CardFaultRecord> cardFaultRecords) {
		this.cardFaultRecords = cardFaultRecords;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardFaultData [cardFaultRecords=" + cardFaultRecords + "]";
	}
	
	
	
}
