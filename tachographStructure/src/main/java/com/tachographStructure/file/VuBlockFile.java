package com.tachographStructure.file;


import java.util.*;


import com.tachographStructure.file.vuBlocks.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tachographStructure.helpers.Number;


/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 */
public class VuBlockFile {


	private HashMap<String,Block> listBlock;	
	@JsonIgnore
	private ListActivity listActivity = null; //VU_ACTIVITY(0X7602),
	@JsonIgnore
	private EventsFaults eventFault = null; //VU_EVENT_FAULT(0X7603),
	@JsonIgnore
	private Speed speed = null; //VU_SPEED(0X7604),
	@JsonIgnore
	private Technical technical = null; //VU_TECHNICAL(0X7605);
	@JsonIgnore
	private Resumen resumen = null; // VU_RESUMEN(0X7601),

	public VuBlockFile(){
		
	}
	
	public VuBlockFile(byte[] datos) throws Exception{
		int start=0;
		ListActivity listActivity=new ListActivity();
		this.listBlock=new HashMap();

		while(start<datos.length){	

			if(datos[start]==0x76){
					start+=1;					
					if(start<datos.length){
						if(datos[start]>0x00 && datos[start]<0x06){

						    int word = Number.getNumber(Arrays.copyOfRange(datos, start-1, start+1));
							Block b=(Block) FactoriaBloques.getFactoria(word, Arrays.copyOfRange(datos, start+1, datos.length));

							if(b.getTRED()==Trep.VU_ACTIVITY.toString()){
								listActivity.add((Activity)b);
							}else{
								this.listBlock.put(b.getTRED(), b);
							}
							start+=b.getSize();
						}
					}
			}else{				
				start+=1;	
			}
			
		}

		this.listBlock.put("VU_ACTIVITY", listActivity);
		setTrep();
	}

	private void setTrep(){
		
		this.resumen = (Resumen)this.listBlock.get(Trep.VU_RESUMEN.toString());
		this.listActivity=(ListActivity) this.listBlock.get(Trep.VU_ACTIVITY.toString());
		this.eventFault=(EventsFaults) this.listBlock.get(Trep.VU_EVENT_FAULT.toString());
		this.speed=(Speed) this.listBlock.get(Trep.VU_SPEED.toString());
		this.technical=(Technical) this.listBlock.get(Trep.VU_TECHNICAL.toString());
	}
	/**
	 * @return the listBlock
	 */
	public HashMap<String, Block> getListBlock() {
		return listBlock;
	}
	/**
	 * @param listBlock the listBlock to set
	 */
	public void setListBlock(HashMap<String, Block> listBlock) {
		this.listBlock = listBlock;
	}
	/**
	 * @return the resumen
	 */
	public Resumen getResumen() {
		return resumen;
	}
	/**
	 * @param resumen the resumen to set
	 */
	public void setResumen(Resumen resumen) {
		this.resumen = resumen;
	}

	
	/**
	 * @return the eventFault
	 */
	public EventsFaults getEventFault() {
		return eventFault;
	}
	/**
	 * @param eventFault the eventFault to set
	 */
	public void setEventFault(EventsFaults eventFault) {
		this.eventFault = eventFault;
	}
	/**
	 * @return the speed
	 */
	public Speed getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Speed speed) {
		this.speed = speed;
	}
	/**
	 * @return the technical
	 */
	public Technical getTechnical() {
		return technical;
	}
	/**
	 * @param technical the technical to set
	 */
	public void setTechnical(Technical technical) {
		this.technical = technical;
	}
	
	
	/**
	 * @return the listActivity
	 */
	public ListActivity getListActivity() {
		return listActivity;
	}

	/**
	 * @param listActivity the listActivity to set
	 */
	public void setListActivity(ListActivity listActivity) {
		this.listActivity = listActivity;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuBlockFile [listBlock=" + listBlock + ", resumen=" + resumen + ", activity=" + listActivity
				+ ", eventFault=" + eventFault + ", speed=" + speed + ", technical=" + technical + "]";
	}
	
	
}
