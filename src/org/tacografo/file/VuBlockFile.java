/**
 * 
 */
package org.tacografo.file;


import java.util.Arrays;
import java.util.HashMap;

import org.tacografo.file.cardblockdriver.CardCertificate;
import org.tacografo.file.cardblockdriver.CardChipIdentification;
import org.tacografo.file.cardblockdriver.CardControlActivityDataRecord;
import org.tacografo.file.cardblockdriver.CardCurrentUse;
import org.tacografo.file.cardblockdriver.CardDriverActivity;
import org.tacografo.file.cardblockdriver.CardDrivingLicenceInformation;
import org.tacografo.file.cardblockdriver.CardEventData;
import org.tacografo.file.cardblockdriver.CardFaultData;
import org.tacografo.file.cardblockdriver.CardIccIdentification;
import org.tacografo.file.cardblockdriver.CardIdentification;
import org.tacografo.file.cardblockdriver.CardPlaceDailyWorkPeriod;
import org.tacografo.file.cardblockdriver.CardVehiclesUsed;
import org.tacografo.file.cardblockdriver.DriverCardApplicationIdentification;
import org.tacografo.file.cardblockdriver.LastCardDownload;
import org.tacografo.file.cardblockdriver.MemberStateCertificate;
import org.tacografo.file.cardblockdriver.SpecificConditionRecord;
import org.tacografo.file.vublock.Activity;
import org.tacografo.file.vublock.EventsFaults;
import org.tacografo.file.vublock.ListActivity;
import org.tacografo.file.vublock.Resumen;
import org.tacografo.file.vublock.Speed;
import org.tacografo.file.vublock.Technical;
import org.tacografo.file.vublock.Trep;
import org.tacografo.tiposdatos.Number;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
include=JsonTypeInfo.As.PROPERTY,
property="type")
@JsonSubTypes({	
@Type(value=Resumen.class, name="resumen"),
@Type(value=ListActivity.class, name="activity"),
@Type(value=EventsFaults.class, name="eventsFaults"),
@Type(value=Speed.class, name="speed"),
@Type(value=Technical.class, name="technical")
})
public class VuBlockFile {

	
	private HashMap<String,Block> listBlock;
	
	private Resumen resumen=null; // VU_RESUMEN(0X7601),
	private ListActivity activity=null; //VU_ACTIVITY(0X7602),
	private EventsFaults eventFault=null; //VU_EVENT_FAULT(0X7603),
	private Speed speed=null; //VU_SPEED(0X7604),
	private Technical technical=null; //VU_TECHNICAL(0X7605);
		
	public VuBlockFile(){
		
	}
	
	public VuBlockFile(byte[] datos) throws Exception{
		int start=0;
		ListActivity listActivity=new ListActivity();
		this.listBlock=new HashMap();
		while(start<datos.length){	
			//System.out.println(Integer.toHexString(datos[start])+" ====="+datos[start]);
			if(datos[start]==0x76){
				
				
					//start+=1;					
				
					//if(datos[start]>0x00 && datos[start]<0x06){						
						
						//int word = Number.getNumber(Arrays.copyOfRange(datos, start-1, start+1));
				
					    int word = Number.getNumber(Arrays.copyOfRange(datos, start, start+2));
					    
						String str=Integer.toHexString(word);
						Block b=(Block) FactoriaBloques.getFactoria(word, Arrays.copyOfRange(datos, start+2, datos.length));
						if(b.getTRED()==Trep.VU_ACTIVITY.toString()){
							listActivity.add((Activity)b);
						}else{
							this.listBlock.put(b.getTRED(), b);
						}
						start+=b.getSize();			
					//}
							
					 
					
			}else{				

				start+=1;	
				
			}
			
		}
		this.listBlock.put("VU_ACTIVITY", listActivity);
		
		setTrep();
	}
	private void setTrep(){
		
		this.resumen=(Resumen)this.listBlock.get(Trep.VU_RESUMEN.toString());
		this.activity=(ListActivity) this.listBlock.get(Trep.VU_ACTIVITY.toString());
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
	 * @return the activity
	 */
	public ListActivity getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(ListActivity activity) {
		this.activity = activity;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuBlockFile [listBlock=" + listBlock + ", resumen=" + resumen + ", activity=" + activity
				+ ", eventFault=" + eventFault + ", speed=" + speed + ", technical=" + technical + "]";
	}
	
	
}
