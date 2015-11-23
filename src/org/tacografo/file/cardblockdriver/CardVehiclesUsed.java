/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import java.util.ArrayList;
import java.util.Arrays;
import org.tacografo.file.cardblockdriver.subblock.CardVehicleRecord;
import org.tacografo.tiposdatos.Number;

/**
 * 2.31. CardVehiclesUsed
 * Información almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a los vehículos utilizados
 * por el titular de la tarjeta (requisitos 197 y 217).
 * CardVehiclesUsed := SEQUENCE {
 * vehiclePointerNewestRecord INTEGER(0..NoOfCardVehicleRecords-1),
 * cardVehicleRecords SET SIZE(NoOfCardVehicleRecords) OF
 * CardVehicleRecord
 * }
 * vehiclePointerNewestRecord es el índice del último registro actualizado de un vehículo.
 * Asignación de valor: número correspondiente al numerador del registro de un vehículo. Al primer registro de la
 * estructura se le asigna el número ¡0¡.
 * cardVehicleRecords es el conjunto de registros con información sobre los vehículos utilizados
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class CardVehiclesUsed extends CardBlockDriver implements CardBlock {
	
	private short vehiclePointerNewestRecord;
	private ArrayList<CardVehicleRecord> cardVehicleRecords;
	private int noOfCardVehicleRecords;
	private byte[] datos;
	
	
	public CardVehiclesUsed() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardVehiclesUsed(byte[] datos) {
		this.datos=datos;
		int start=0;		
		this.vehiclePointerNewestRecord=Number.getShort_16(Arrays.copyOfRange(datos,start , start+=Sizes.VEHICLEPOINTERNEWESTRECORD.getMax()));
		//this.vehiclePointerNewestRecord+=-1;		
		this.cardVehicleRecords=new ArrayList<CardVehicleRecord>();
		
	}
	
	/**
	 * @param noOfCardVehicleRecords the noOfCardVehicleRecords to set
	 */
	public void setNoOfCardVehicleRecords(int noOfCardVehicleRecords) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
		this.setListaCardVehicleRecords();
	}
	/**
	 * Rellena array con cardvehiclerecord segun número correspondiente al numerador del registro de un vehículo. Al primer registro de la
	 * estructura se le asigna el número ¡0¡.
	 */
	private void setListaCardVehicleRecords(){
		
		CardVehicleRecord cvr;
		int start=2;
		for (int i=0;i<this.vehiclePointerNewestRecord;i++){
			if((start+Sizes.CARDVEHICLERECORD.getMax())>=this.datos.length){
				start=0;
			}else{
				cvr=new CardVehicleRecord(Arrays.copyOfRange(this.datos, start, start+=Sizes.CARDVEHICLERECORD.getMax()));
				this.cardVehicleRecords.add(cvr);
			}
			
		}
	}
	
	/**
	 * Obtiene el número de registros del vehículo que caben en la tarjeta.
	 * @return the noOfCardVehicleRecords
	 */
	public int getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Obtiene el índice del último registro actualizado de un vehículo.
	 * @return the vehiclePointerNewestRecord
	 */
	public short getVehiclePointerNewestRecord() {
		return vehiclePointerNewestRecord;
	}


	/**
	 * Asigna el índice del último registro actualizado de un vehículo.
	 * @param vehiclePointerNewestRecord the vehiclePointerNewestRecord to set
	 */
	public void setVehiclePointerNewestRecord(short vehiclePointerNewestRecord) {
		this.vehiclePointerNewestRecord = vehiclePointerNewestRecord;
	}


	/**
	 * Obtiene el conjunto de registros con información sobre los vehículos utilizados.
	 * @return the cardVehicleRecords
	 */
	public ArrayList<CardVehicleRecord> getCardVehicleRecords() {
		return cardVehicleRecords;
	}


	/**
	 * Asigna el conjunto de registros con información sobre los vehículos utilizados.
	 * @param cardVehicleRecords the cardVehicleRecords to set
	 */
	public void setCardVehicleRecords(
			ArrayList<CardVehicleRecord> cardVehicleRecords) {
		this.cardVehicleRecords = cardVehicleRecords;
	}


	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardVehiclesUsed [vehiclePointerNewestRecord="
				+ vehiclePointerNewestRecord + ", cardVehicleRecords="
				+ cardVehicleRecords + ", noOfCardVehicleRecords="
				+ noOfCardVehicleRecords + ", datos=" + Arrays.toString(datos)
				+ "]";
	}


	
	

}
