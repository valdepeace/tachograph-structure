/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import org.tacografo.file.Block;
import org.tacografo.file.cardblockdriver.subblock.CardVehicleRecord;
import org.tacografo.tiposdatos.Number;

import com.thingtrack.parse.Vehicle;
import com.thingtrack.parse.VehicleChangeInfo;

/**
 * 2.31. CardVehiclesUsed
 * Informaci�n almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a los veh�culos utilizados
 * por el titular de la tarjeta (requisitos 197 y 217).
 * CardVehiclesUsed := SEQUENCE {
 * vehiclePointerNewestRecord INTEGER(0..NoOfCardVehicleRecords-1),
 * cardVehicleRecords SET SIZE(NoOfCardVehicleRecords) OF
 * CardVehicleRecord
 * }
 * vehiclePointerNewestRecord es el �ndice del �ltimo registro actualizado de un veh�culo.
 * Asignaci�n de valor: n�mero correspondiente al numerador del registro de un veh�culo. Al primer registro de la
 * estructura se le asigna el n�mero �0�.
 * cardVehicleRecords es el conjunto de registros con informaci�n sobre los veh�culos utilizados
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class CardVehiclesUsed extends Block implements CardBlock {
	
	private int vehiclePointerNewestRecord;
	private ArrayList<CardVehicleRecord> cardVehicleRecords;
	private int noOfCardVehicleRecords;
	private byte[] datos;
	private HashMap<String,ArrayList<VehicleChangeInfo>> listVehicle;
	
	
	public CardVehiclesUsed() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardVehiclesUsed(byte[] datos) {
		this.datos=datos;
		int start=0;		
		this.vehiclePointerNewestRecord=Number.getNumber(Arrays.copyOfRange(datos,start , start+=Sizes.VEHICLEPOINTERNEWESTRECORD.getMax()));
		//this.vehiclePointerNewestRecord+=-1;		
		this.cardVehicleRecords=new ArrayList<CardVehicleRecord>();
		this.listVehicle=new HashMap<String,ArrayList<VehicleChangeInfo>>();
		
	}
	
	/**
	 * @param noOfCardVehicleRecords the noOfCardVehicleRecords to set
	 */
	public void setNoOfCardVehicleRecords(int noOfCardVehicleRecords) {
		this.noOfCardVehicleRecords = noOfCardVehicleRecords;
		this.setListaCardVehicleRecords();
	}
	/**
	 * Rellena array con cardvehiclerecord segun n�mero correspondiente al numerador del registro de un veh�culo. Al primer registro de la
	 * estructura se le asigna el n�mero �0�.
	 */
	private void setListaCardVehicleRecords(){
		
		CardVehicleRecord cvr;
		VehicleChangeInfo v;
		ArrayList<VehicleChangeInfo> list_VehicleChangeInfo;
		int start=2;
		String key="";
		Calendar c=Calendar.getInstance();
		for (int i=0;i<this.vehiclePointerNewestRecord;i++){
			if((start+Sizes.CARDVEHICLERECORD.getMax())>=this.datos.length){
				start=0;
			}else{
				cvr=new CardVehicleRecord(Arrays.copyOfRange(this.datos, start, start+=Sizes.CARDVEHICLERECORD.getMax()));
				this.cardVehicleRecords.add(cvr);
				v=new VehicleChangeInfo(cvr);
				c.setTime(cvr.getVehicleFirstUse());
				key=c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
				if(this.listVehicle.get(key)==null){
					list_VehicleChangeInfo = new ArrayList<VehicleChangeInfo>();
					list_VehicleChangeInfo.add(v);
					this.listVehicle.put(key,list_VehicleChangeInfo);
				}else{
					this.listVehicle.get(key).add(v);
				}

			}
			
		}
	}
	
	/**
	 * @return the datos
	 */
	public byte[] getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}

	/**
	 * @return the listVehicle
	 */
	public HashMap<String,ArrayList<VehicleChangeInfo>> getListVehicle() {
		return listVehicle;
	}

	/**
	 * @param listVehicle the listVehicle to set
	 */
	public void setListVehicle(HashMap<String,ArrayList<VehicleChangeInfo>> listVehicle) {
		this.listVehicle = listVehicle;
	}

	/**
	 * @param vehiclePointerNewestRecord the vehiclePointerNewestRecord to set
	 */
	public void setVehiclePointerNewestRecord(int vehiclePointerNewestRecord) {
		this.vehiclePointerNewestRecord = vehiclePointerNewestRecord;
	}

	/**
	 * Obtiene el n�mero de registros del veh�culo que caben en la tarjeta.
	 * @return the noOfCardVehicleRecords
	 */
	public int getNoOfCardVehicleRecords() {
		return noOfCardVehicleRecords;
	}

	/**
	 * Obtiene el �ndice del �ltimo registro actualizado de un veh�culo.
	 * @return the vehiclePointerNewestRecord
	 */
	public int getVehiclePointerNewestRecord() {
		return vehiclePointerNewestRecord;
	}


	/**
	 * Asigna el �ndice del �ltimo registro actualizado de un veh�culo.
	 * @param vehiclePointerNewestRecord the vehiclePointerNewestRecord to set
	 */
	public void setVehiclePointerNewestRecord(short vehiclePointerNewestRecord) {
		this.vehiclePointerNewestRecord = vehiclePointerNewestRecord;
	}


	/**
	 * Obtiene el conjunto de registros con informaci�n sobre los veh�culos utilizados.
	 * @return the cardVehicleRecords
	 */
	public ArrayList<CardVehicleRecord> getCardVehicleRecords() {
		return cardVehicleRecords;
	}


	/**
	 * Asigna el conjunto de registros con informaci�n sobre los veh�culos utilizados.
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
