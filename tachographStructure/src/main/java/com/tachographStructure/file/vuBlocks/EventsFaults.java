/**
 * 
 */
package com.tachographStructure.file.vuBlocks;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import com.tachographStructure.file.Block;
import com.tachographStructure.file.vuBlocks.*;
import com.tachographStructure.file.vuBlocks.subBlocks.*;
import com.tachographStructure.helpers.Number;
import com.tachographStructure.helpers.OctetString;
import com.tachographStructure.file.certificate.Signature;

/**
 * @author Andres Carmona Gil
 *
 */
public class EventsFaults extends Block {
	
	/**
	 * 2.133.   VuFaultData
	 * Información almacenada en una unidad intravehicular y relativa a los fallos (requisito 096).
	 * VuFaultData ::= SEQUENCE {
	 * noOfVuFaults INTEGER(0..255),
	 * vuFaultRecords SET SIZE(noOfVuFaults) OF VuFaultRecord
	 * }
	 * noOfVuFaults es el número de fallos incluidos en el conjunto vuFaultRecords.
	 */
	private ArrayList<VuFaultRecord> vuFaultData;
	/**
	 * noOfVuFaults es el número de fallos incluidos en el conjunto vuFaultRecords.
	 */
	private int noOfVuFaults;
	
	/**
	 * 2.131.   VuEventData
	 * Información almacenada en una unidad intravehicular y relativa a incidentes (requisito 094, salvo el incidente de exceso de velocidad).
	 * VuEventData ::= SEQUENCE {
	 * noOfVuEvents INTEGER(0..255),
	 * vuEventRecords SET SIZE(noOfVuEvents) OF VuEventRecord
	 * }
	 * noOfVuEvents es el número de incidentes incluidos en el conjunto vuEventRecords.
	 * vuEventRecords es un conjunto de registros sobre incidentes.
	 */
	private ArrayList<VuEventRecord> vuEventData;
	/**
	 * noOfVuEvents es el número de incidentes incluidos en el conjunto vuEventRecords.
	 */
	private int noOfVuEvents;
	
	private VuOverSpeedingControlData vuOverSpeedingControlData;
	
	/**
	 * 2.140.   VuOverSpeedingEventData
	 * Información almacenada en una unidad intravehicular y relativa a incidentes de exceso de velocidad (requisito 094).
	 * VuOverSpeedingEventData ::= SEQUENCE {
	 * noOfVuOverSpeedingEvents INTEGER(0..255),
	 * vuOverSpeedingEventRecords SET SIZE(noOfVuOverSpeedingEvents) OF VuOverSpeedingEventRecord
	 * }
	 * noOfVuOverSpeedingEvents es el número de incidentes incluidos en el conjunto vuOverSpeedingEventRecords.
	 * vuOverSpeedingEventRecords es el conjunto de registros sobre incidentes de exceso de velocidad.
	 */
	private ArrayList<VuOverSpeedingEventRecord> vuOverSpeedingEventData;
	/**
	 * noOfVuOverSpeedingEvents es el número de incidentes incluidos en el conjunto vuOverSpeedingEventRecords.
	 */
	private int noOfVuOverSpeedingEvents;
	
	/**
	 * 2.152.   VuTimeAdjustmentData
	 * Información almacenada en una unidad intravehicular y relativa a los ajustes de hora que se han efectuado fuera del marco de un calibrado regular (requisito 101).
	 * VuTimeAdjustmentData ::= SEQUENCE {
	 * noOfVuTimeAdjRecords INTEGER(0..6),
	 * vuTimeAdjustmentRecords SET SIZE(noOfVuTimeAdjRecords) OF VuTimeAdjustmentRecord
	 * }
	 * noOfVuTimeAdjRecords es el número de registros que hay en el conjunto vuTimeAdjustmentRecords.
	 * vuTimeAdjustmentRecords es el conjunto de registros sobre ajustes de la hora.
	 */
	private ArrayList<VuTimeAdjustmentRecord> vuTimeAdjustmentData;
	/**
	 * noOfVuTimeAdjRecords es el número de registros que hay en el conjunto vuTimeAdjustmentRecords.
	 */
	private int noOfVuTimeAdjRecords;
	
	private Signature signature;
	private int size;
	
	public EventsFaults(byte[] bytes) throws Exception{
		int start=0;
		
		this.noOfVuFaults=Number.getNumber(Arrays.copyOfRange(bytes, start, start+= VuSizes.NOOFVUFAULTS.getSize()));
		if (this.noOfVuFaults>0){
			this.setListVuFaultData(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUFAULTRECORD.getSize()*this.noOfVuFaults));
		}
		
		
		this.noOfVuEvents=Number.getNumber(Arrays.copyOfRange(bytes, start, start+= VuSizes.NOOFVUEVENTS.getSize()));
		
		if(this.noOfVuEvents>0)
		this.setListVuEventData(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUEVENTRECORD.getSize()*this.noOfVuEvents));
		
		this.vuOverSpeedingControlData=new VuOverSpeedingControlData(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUOVERSPEEDINGCONTROLDATA.getSize()));
		
		this.noOfVuOverSpeedingEvents= Number.getNumber(Arrays.copyOfRange(bytes, start, start+= VuSizes.NOOFVUOVERSPEEDINGEVENTS.getSize()));
		if(this.noOfVuOverSpeedingEvents>0)
		this.setListVuOverSpeedingEventData(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUOVERSPEEDINGEVENTRECORD.getSize()*this.noOfVuOverSpeedingEvents));
		
		this.noOfVuTimeAdjRecords=Number.getNumber(Arrays.copyOfRange(bytes, start, start+= VuSizes.NOOFVUTIMEADJRECORDS.getSize()));
		if(this.noOfVuTimeAdjRecords>0)
		this.setListvuTimeAdjustmentData(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUTIMEADJUSTMENTRECORD.getSize()*this.noOfVuTimeAdjRecords));
		this.signature= new Signature(Arrays.copyOfRange(bytes, start, start += VuSizes.SIGNATURE_TREP3.getSize()));
		this.size=start;			
	}

	

	private void setListVuFaultData(byte[] bytes) throws UnsupportedEncodingException {
		this.vuFaultData=new ArrayList();		
		int start=0;
		VuFaultRecord vfr;
		for (int i=0;i<this.noOfVuFaults;i++){
			vfr=new VuFaultRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUFAULTRECORD.getSize()));
			this.vuFaultData.add(vfr);
		}
				
	}
	
	private void setListVuEventData(byte[]bytes) throws UnsupportedEncodingException {
		this.vuEventData=new ArrayList();
		int start=0;
		VuEventRecord ver;
		for (int i=0;i<this.noOfVuEvents;i++){
			ver=new VuEventRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUEVENTRECORD.getSize()));
			this.vuEventData.add(ver);
		}
		
	}
	private void setListVuOverSpeedingEventData(byte[] bytes) throws UnsupportedEncodingException {
		this.vuOverSpeedingEventData=new ArrayList();		
		int start=0;
		VuOverSpeedingEventRecord voser;
		for (int i=0;i<this.noOfVuOverSpeedingEvents;i++){
			voser=new VuOverSpeedingEventRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUOVERSPEEDINGEVENTRECORD.getSize()));
			this.vuOverSpeedingEventData.add(voser);
		}
	}
	
	private void setListvuTimeAdjustmentData(byte[] bytes) throws UnsupportedEncodingException {
		this.vuTimeAdjustmentData=new ArrayList();		
		int start=0;
		VuTimeAdjustmentRecord vtar;
		for (int i=0;i<this.noOfVuTimeAdjRecords;i++){
			vtar=new VuTimeAdjustmentRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUTIMEADJUSTMENTRECORD.getSize()));
			this.vuTimeAdjustmentData.add(vtar);
		}
				
	}



	/**
	 * @return the vuFaultData
	 */
	public ArrayList<VuFaultRecord> getVuFaultData() {
		return vuFaultData;
	}



	/**
	 * @param vuFaultData the vuFaultData to set
	 */
	public void setVuFaultData(ArrayList<VuFaultRecord> vuFaultData) {
		this.vuFaultData = vuFaultData;
	}



	/**
	 * @return the noOfVuFaults
	 */
	public int getNoOfVuFaults() {
		return noOfVuFaults;
	}



	/**
	 * @param noOfVuFaults the noOfVuFaults to set
	 */
	public void setNoOfVuFaults(int noOfVuFaults) {
		this.noOfVuFaults = noOfVuFaults;
	}



	/**
	 * @return the vuEventData
	 */
	public ArrayList<VuEventRecord> getVuEventData() {
		return vuEventData;
	}



	/**
	 * @param vuEventData the vuEventData to set
	 */
	public void setVuEventData(ArrayList<VuEventRecord> vuEventData) {
		this.vuEventData = vuEventData;
	}



	/**
	 * @return the noOfVuEvents
	 */
	public int getNoOfVuEvents() {
		return noOfVuEvents;
	}



	/**
	 * @param noOfVuEvents the noOfVuEvents to set
	 */
	public void setNoOfVuEvents(int noOfVuEvents) {
		this.noOfVuEvents = noOfVuEvents;
	}



	/**
	 * @return the vuOverSpeedingControlData
	 */
	public VuOverSpeedingControlData getVuOverSpeedingControlData() {
		return vuOverSpeedingControlData;
	}



	/**
	 * @param vuOverSpeedingControlData the vuOverSpeedingControlData to set
	 */
	public void setVuOverSpeedingControlData(VuOverSpeedingControlData vuOverSpeedingControlData) {
		this.vuOverSpeedingControlData = vuOverSpeedingControlData;
	}



	/**
	 * @return the vuOverSpeedingEventData
	 */
	public ArrayList<VuOverSpeedingEventRecord> getVuOverSpeedingEventData() {
		return vuOverSpeedingEventData;
	}



	/**
	 * @param vuOverSpeedingEventData the vuOverSpeedingEventData to set
	 */
	public void setVuOverSpeedingEventData(ArrayList<VuOverSpeedingEventRecord> vuOverSpeedingEventData) {
		this.vuOverSpeedingEventData = vuOverSpeedingEventData;
	}



	/**
	 * @return the noOfVuOverSpeedingEvents
	 */
	public int getNoOfVuOverSpeedingEvents() {
		return noOfVuOverSpeedingEvents;
	}



	/**
	 * @param noOfVuOverSpeedingEvents the noOfVuOverSpeedingEvents to set
	 */
	public void setNoOfVuOverSpeedingEvents(int noOfVuOverSpeedingEvents) {
		this.noOfVuOverSpeedingEvents = noOfVuOverSpeedingEvents;
	}



	/**
	 * @return the vuTimeAdjustmentData
	 */
	public ArrayList<VuTimeAdjustmentRecord> getVuTimeAdjustmentData() {
		return vuTimeAdjustmentData;
	}



	/**
	 * @param vuTimeAdjustmentData the vuTimeAdjustmentData to set
	 */
	public void setVuTimeAdjustmentData(ArrayList<VuTimeAdjustmentRecord> vuTimeAdjustmentData) {
		this.vuTimeAdjustmentData = vuTimeAdjustmentData;
	}



	/**
	 * @return the noOfVuTimeAdjRecords
	 */
	public int getNoOfVuTimeAdjRecords() {
		return noOfVuTimeAdjRecords;
	}



	/**
	 * @param noOfVuTimeAdjRecords the noOfVuTimeAdjRecords to set
	 */
	public void setNoOfVuTimeAdjRecords(int noOfVuTimeAdjRecords) {
		this.noOfVuTimeAdjRecords = noOfVuTimeAdjRecords;
	}



	/**
	 * @return the signature
	 */
	public Signature getSignature() {
		return signature;
	}



	/**
	 * @param signature the signature to set
	 */
	public void setSignature(Signature signature) {
		this.signature = signature;
	}



	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}



	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuEventsFaults [vuFaultData=" + vuFaultData + ", noOfVuFaults=" + noOfVuFaults + ", vuEventData="
				+ vuEventData + ", noOfVuEvents=" + noOfVuEvents + ", vuOverSpeedingControlData="
				+ vuOverSpeedingControlData + ", vuOverSpeedingEventData=" + vuOverSpeedingEventData
				+ ", noOfVuOverSpeedingEvents=" + noOfVuOverSpeedingEvents + ", vuTimeAdjustmentData="
				+ vuTimeAdjustmentData + ", noOfVuTimeAdjRecords=" + noOfVuTimeAdjRecords + ", signature=" + signature
				+ ", size=" + size + "]";
	}
	
}
