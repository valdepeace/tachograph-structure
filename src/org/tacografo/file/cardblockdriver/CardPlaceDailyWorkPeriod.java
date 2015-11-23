package org.tacografo.file.cardblockdriver;


import java.util.ArrayList;
import java.util.Arrays;

import org.tacografo.file.cardblockdriver.subblock.PlaceRecord;
import org.tacografo.tiposdatos.Number;

/**
 * CardPlaceDailyWorkPeriod
 * Información almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a los lugares donde
 * comienzan y/o terminan los períodos de trabajo diarios (requisitos 202 y 221).
 * CardPlaceDailyWorkPeriod ::= SEQUENCE {
 * placePointerNewestRecord INTEGER(0..NoOfCardPlaceRecords-1),
 * placeRecords SET SIZE(NoOfCardPlaceRecords) OF PlaceRecord
 * }
 * placePointerNewestRecord es el índice del último registro actualizado de un lugar.
 * Asignación de valor: número que corresponde al numerador del registro de un lugar. Al primer registro de la estructura
 * se le asigna el número '0'.
 * placeRecords es el conjunto de registros que contiene la información relativa a los lugares introducidos. 
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */

public class CardPlaceDailyWorkPeriod extends CardBlockDriver implements
		CardBlock {
	private Integer placePointerNewestRecord;
	private ArrayList<PlaceRecord> placeRecords;
	private byte[] datos;
	private int noOfCardPlaceRecords;
	
	
	public CardPlaceDailyWorkPeriod() {	}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	
	public CardPlaceDailyWorkPeriod(byte[] datos){
		int start=0;
		this.placePointerNewestRecord=(int) Number.getShort_8(Arrays.copyOfRange(datos, start, start+=Sizes.PLACEPOINTERNEWESTRECORD.getMax()));
		this.placeRecords=new ArrayList<PlaceRecord>();
		this.datos=datos;
	}
	
	public void setNoOfCArdPlaceRecords(int noOfCardPlaceRecords){
		this.noOfCardPlaceRecords=noOfCardPlaceRecords-1;
		setListaPlaceRecords();
	}
	
	private void setListaPlaceRecords(){
		PlaceRecord pr;
		// el indice sera por ejemplo 15 registros * 10 = 150 +10 del indice 0 + 1 byte del placePointerNewestRecord 
		// comenzaria en el byte 151.
		int start=this.placePointerNewestRecord*Sizes.PLACERECORD.getMax()+10+1;
		//int start=1;
		
		for (int i=0;i<this.noOfCardPlaceRecords;i++){			
			pr=new PlaceRecord(Arrays.copyOfRange(this.datos, start, start+=Sizes.PLACERECORD.getMax()));			
			this.placeRecords.add(pr);
			
			if (start>this.noOfCardPlaceRecords*10){
				start=1;
						
			}
		}
	}

	
	/**
	 * Obtiene el índice del último registro actualizado de un lugar.
	 * @return the placePointerNewestRecord
	 */
	public Integer getPlacePointerNewestRecord() {
		return placePointerNewestRecord;
	}

	/**
	 * Asigna el índice del último registro actualizado de un lugar.
	 * @param placePointerNewestRecord the placePointerNewestRecord to set
	 */
	public void setPlacePointerNewestRecord(Integer placePointerNewestRecord) {
		this.placePointerNewestRecord = placePointerNewestRecord;
	}

	/**
	 * Obtiene  el número de lugares que puede registrar la tarjeta.
	 * @return the noOfCardPlaceRecords
	 */
	public int getNoOfCardPlaceRecords() {
		return noOfCardPlaceRecords;
	}

	/**
	 * Asigna  el número de lugares que puede registrar la tarjeta.
	 * @param noOfCardPlaceRecords the noOfCardPlaceRecords to set
	 */
	public void setNoOfCardPlaceRecords(int noOfCardPlaceRecords) {
		this.noOfCardPlaceRecords = noOfCardPlaceRecords;
	}

	/**
	 * Obtiene el conjunto de registros que contiene la información relativa a los lugares introducidos.
	 * @param placeRecords the placeRecords to set
	 */
	public void setPlaceRecords(ArrayList<PlaceRecord> placeRecords) {
		this.placeRecords = placeRecords;
	}

	/**
	 * Asigna el conjunto de registros que contiene la información relativa a los lugares introducidos.
	 * @return the placeRecords
	 */
	public ArrayList<PlaceRecord> getPlaceRecords() {
		return placeRecords;
	}

	@Override
	public String toString() {
		return "CardPlaceDailyWorkPeriod [placePointerNewestRecord="
				+ placePointerNewestRecord + ", placeRecords=" + placeRecords.toString()
				+ ", noOfCardPlaceRecords=" + noOfCardPlaceRecords + "]";
	}
	
	
}
