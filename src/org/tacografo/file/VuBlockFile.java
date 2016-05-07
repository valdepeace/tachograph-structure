/**
 * 
 */
package org.tacografo.file;


import java.util.*;

import com.thingtrack.parse.*;
import org.tacografo.file.cardblockdriver.subblock.ActivityChangeInfo;
import org.tacografo.file.vublock.*;
import org.tacografo.file.vublock.Activity;
import org.tacografo.file.vublock.subblock.VuCardIWRecord;
import org.tacografo.tiposdatos.Number;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 */
/*
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
*/
public class VuBlockFile {

	@JsonIgnore
	private HashMap<String,Block> listBlock;	
	@JsonIgnore
	private ListActivity listActivity=null; //VU_ACTIVITY(0X7602),
	@JsonIgnore
	private EventsFaults eventFault=null; //VU_EVENT_FAULT(0X7603),
	@JsonIgnore
	private Speed speed=null; //VU_SPEED(0X7604),	
	@JsonIgnore
	private Technical technical=null; //VU_TECHNICAL(0X7605);
	
	private Resumen resumen=null; // VU_RESUMEN(0X7601),
	
	private Tacho tachos;
	
	private HashMap<String,Driver> drivers;
	
	private HashMap<String, Vehicle> vehicles;
	
	public VuBlockFile(){
		
	}
	
	public VuBlockFile(byte[] datos) throws Exception{
		int start=0;
		ListActivity listActivity=new ListActivity();
		this.listBlock=new HashMap();
		this.tachos=new Tacho();
		this.drivers=new HashMap();

		Activity activity;
		com.thingtrack.parse.Activity activityParse;
		Driver driver;
		Tacho tacho;
		String registration="";
		while(start<datos.length){	
			//System.out.println(Integer.toHexString(datos[start])+" ====="+datos[start]);
			if(datos[start]==0x76){
					start+=1;					
					if(start<datos.length){											
						if(datos[start]>0x00 && datos[start]<0x06){						
																			
						    int word = Number.getNumber(Arrays.copyOfRange(datos, start-1, start+1));
						    
							String str=Integer.toHexString(word);
							
							Block b=(Block) FactoriaBloques.getFactoria(word, Arrays.copyOfRange(datos, start+1, datos.length));
							if(b.getTRED()==Trep.VU_RESUMEN.toString()){
								Resumen r=(Resumen)b;							
								registration=r.getVehicleRegistrationIdentification().getVehicleRegistrationNumber();
							}
							if(b.getTRED()==Trep.VU_ACTIVITY.toString()){								
								listActivity.add((Activity)b);
								activity=(Activity) b;
								java.util.Iterator<VuCardIWRecord> it= activity.getVuCardIWData().iterator();
								// ----------> driver

								ActivityChangeInfo aci;
								com.thingtrack.parse.ActivityChangeInfo ctpaci;
								HashMap<String, ArrayList<com.thingtrack.parse.ActivityChangeInfo>> tacho_activity=new HashMap();
								HashMap<String, ArrayList<VehicleChangeInfo>> tacho_vehicles=new HashMap();
								HashMap<String, ArrayList> tacho_places=new HashMap();
								ArrayList lista_changeInfo, lista;
								Driver dr;
								// ----> ActivityChangeInfo for driver
								Iterator it_changeInfo=activity.getVuActivityDailyData().iterator();
								while (it_changeInfo.hasNext()){
									aci=(ActivityChangeInfo)it_changeInfo.next();
									ctpaci=new com.thingtrack.parse.ActivityChangeInfo(aci);
									long fecha=activity.getTimeReal().getTime();
									fecha+=aci.getHours()*60*60*1000;
									fecha+=aci.getMin()*60*1000;
									Date d=new Date(fecha);
									Iterator<VuCardIWRecord> iter_driver=activity.getVuCardIWData().iterator();
									ctpaci.setFromTime(d);
									com.thingtrack.parse.ActivityChangeInfo aux=ctpaci;
									if (aci.getP()=="no insertada"){
										if(tacho_activity.containsKey("withoutDriver")){
											tacho_activity.get("withoutDriver").add(ctpaci);
										}else{
											lista_changeInfo=new ArrayList();
											lista_changeInfo.add(ctpaci);
											tacho_activity.put("withoutDriver",lista_changeInfo);
										}
									}else{
										while(iter_driver.hasNext()){
											// ----> driver el
											VuCardIWRecord el = iter_driver.next();
											dr = new Driver(el);
											Date be=el.getCardInsertionTime();
											Date e=el.getCardWithdrawalTime();
											be.setSeconds(0);
											e.setSeconds(0);
											if(d.compareTo(be)>=0 && e.compareTo(d)>=0){
												if(tacho_activity.containsKey(dr.getCardNumber().get(0).getNumber())){
													int size=tacho_activity.get(dr.getCardNumber().get(0).getNumber()).size();
													tacho_activity.get(dr.getCardNumber().get(0).getNumber()).get(size-1).setToTime(ctpaci.getFromTime());
													tacho_activity.get(dr.getCardNumber().get(0).getNumber()).add(ctpaci);
												}else{
													lista_changeInfo=new ArrayList();
													lista_changeInfo.add(ctpaci);
													tacho_activity.put(dr.getCardNumber().get(0).getNumber(),lista_changeInfo);
												}
												if(el.getActvityChangeInfo().isEmpty()){
													el.getActvityChangeInfo().add(ctpaci);
												}else{
													el.getActvityChangeInfo().get(el.getActvityChangeInfo().size()-1).setToTime(ctpaci.getFromTime());
													el.getActvityChangeInfo().add(ctpaci);
												}
											}
										}
									}
								}
								// -----> vehicles for driver
								while(it.hasNext()){
									VuCardIWRecord vc=it.next();
									driver=new Driver(vc);
									VehicleChangeInfo v=new VehicleChangeInfo();
									v.setFromDate(vc.getCardInsertionTime());
									v.setToDate(vc.getCardWithdrawalTime());
									v.setDistance(vc.getVehicleOdometerValueAtWithdrawal()-vc.getVehicleOdometerValueAtInsertion());
									if(tacho_vehicles.containsKey(vc.getFullCardNumber().getCardNumber().getDriverIdentification())){
										tacho_vehicles.get(vc.getFullCardNumber().getCardNumber().getDriverIdentification()).add(v);
									}else{
										lista=new ArrayList();
										lista.add(v);
										tacho_vehicles.put(driver.getCardNumber().get(0).getNumber(),lista);
									}
									if(!tacho_places.containsKey(vc.getFullCardNumber().getCardNumber().getDriverIdentification())){
										tacho_places.put(driver.getCardNumber().get(0).getNumber(),vc.getPlaces());
									}
									if(!drivers.containsKey(driver.getCardNumber())){
										drivers.put(driver.getCardNumber().get(0).getNumber(),driver);	
									}



								}
								// ----> Activity for driver
								Iterator iter_tacho=tacho_activity.entrySet().iterator();
								while(iter_tacho.hasNext()){
									Map.Entry<String,ArrayList> t=(Map.Entry) iter_tacho.next();
									activityParse=new com.thingtrack.parse.Activity();
									activityParse.setDate(activity.getTimeReal());
									activityParse.setActivityChangeInfo(t.getValue());

									if(t.getKey()=="withoutDriver"){
										activityParse.setCardNumber(null);
									}else{
										activityParse.setVehicles(tacho_vehicles.get(t.getKey()));
										activityParse.setPlaces(tacho_places.get(t.getKey()));

										int distance=0;
										for(int i=0;i<tacho_vehicles.get(t.getKey()).size();i++){
											distance+=tacho_vehicles.get(t.getKey()).get(i).getDistance();
										}

										activityParse.setDistance(distance);
										activityParse.setCardNumber(drivers.get(t.getKey()).getCardNumber().get(0).getNumber());
									}

									this.tachos.getActivity().add(activityParse);
								}
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
	
	public VuBlockFile(byte[] datos, String organizationId, String filename) throws Exception {
		int start=0;
		ListActivity listActivity=new ListActivity();
		this.listBlock=new HashMap();
		this.tachos=new Tacho();
		this.drivers=new HashMap();

		Activity activity;
		com.thingtrack.parse.Activity activityParse;
		Driver driver;
		Tacho tacho;
		String registration="";
		while(start<datos.length){	
			//System.out.println(Integer.toHexString(datos[start])+" ====="+datos[start]);
			if(datos[start]==0x76){
							
					start+=1;					
					if(start<datos.length){											
						if(datos[start]>0x00 && datos[start]<0x06){						
																			
						    int word = Number.getNumber(Arrays.copyOfRange(datos, start-1, start+1));
						    
							String str=Integer.toHexString(word);
							
							Block b=(Block) FactoriaBloques.getFactoria(word, Arrays.copyOfRange(datos, start+1, datos.length));
							if(b.getTRED()==Trep.VU_RESUMEN.toString()){
								Resumen r=(Resumen)b;							
								registration=r.getVehicleRegistrationIdentification().getVehicleRegistrationNumber();
							}
							if(b.getTRED()==Trep.VU_ACTIVITY.toString()){								
								listActivity.add((Activity)b);
								activity=(Activity) b;
								java.util.Iterator<VuCardIWRecord> it= activity.getVuCardIWData().iterator();
								// ----------> driver

								ActivityChangeInfo aci;
								com.thingtrack.parse.ActivityChangeInfo ctpaci;
								HashMap<String, ArrayList<com.thingtrack.parse.ActivityChangeInfo>> tacho_activity=new HashMap();
								HashMap<String, ArrayList<VehicleChangeInfo>> tacho_vehicles=new HashMap();
								HashMap<String, ArrayList> tacho_places=new HashMap();
								ArrayList lista_changeInfo, lista;
								Driver dr;
								// ----> ActivityChangeInfo for driver
								Iterator it_changeInfo=activity.getVuActivityDailyData().iterator();
								while (it_changeInfo.hasNext()){
									aci=(ActivityChangeInfo)it_changeInfo.next();
									ctpaci=new com.thingtrack.parse.ActivityChangeInfo(aci);
									long fecha=activity.getTimeReal().getTime();
									fecha+=aci.getHours()*60*60*1000;
									fecha+=aci.getMin()*60*1000;
									Date d=new Date(fecha);
									Iterator<VuCardIWRecord> iter_driver=activity.getVuCardIWData().iterator();
									ctpaci.setFromTime(d);
									com.thingtrack.parse.ActivityChangeInfo aux=ctpaci;
									if (aci.getP()=="no insertada"){
										if(tacho_activity.containsKey("withoutDriver")){
											tacho_activity.get("withoutDriver").add(ctpaci);
										}else{
											lista_changeInfo=new ArrayList();
											lista_changeInfo.add(ctpaci);
											tacho_activity.put("withoutDriver",lista_changeInfo);
										}
									}else{
										while(iter_driver.hasNext()){
											// ----> driver el
											VuCardIWRecord el = iter_driver.next();
											dr = new Driver(el);
											Date be=el.getCardInsertionTime();
											Date e=el.getCardWithdrawalTime();
											be.setSeconds(0);
											e.setSeconds(0);
											if(d.compareTo(be)>=0 && e.compareTo(d)>=0){
												if(tacho_activity.containsKey(dr.getCardNumber().get(0).getNumber())){
													int size=tacho_activity.get(dr.getCardNumber().get(0).getNumber()).size();
													tacho_activity.get(dr.getCardNumber().get(0).getNumber()).get(size-1).setToTime(ctpaci.getFromTime());
													tacho_activity.get(dr.getCardNumber().get(0).getNumber()).add(ctpaci);
												}else{
													lista_changeInfo=new ArrayList();
													lista_changeInfo.add(ctpaci);
													tacho_activity.put(dr.getCardNumber().get(0).getNumber(),lista_changeInfo);
												}
												if(el.getActvityChangeInfo().isEmpty()){
													el.getActvityChangeInfo().add(ctpaci);
												}else{
													el.getActvityChangeInfo().get(el.getActvityChangeInfo().size()-1).setToTime(ctpaci.getFromTime());
													el.getActvityChangeInfo().add(ctpaci);
												}
											}
										}
									}
								}
								// -----> vehicles for driver
								while(it.hasNext()){
									VuCardIWRecord vc=it.next();
									driver=new Driver(vc);
									VehicleChangeInfo v=new VehicleChangeInfo();
									v.setFromDate(vc.getCardInsertionTime());
									v.setToDate(vc.getCardWithdrawalTime());
									v.setDistance(vc.getVehicleOdometerValueAtWithdrawal()-vc.getVehicleOdometerValueAtInsertion());
									if(tacho_vehicles.containsKey(vc.getFullCardNumber().getCardNumber().getDriverIdentification())){
										tacho_vehicles.get(vc.getFullCardNumber().getCardNumber().getDriverIdentification()).add(v);
									}else{
										lista=new ArrayList();
										lista.add(v);
										tacho_vehicles.put(driver.getCardNumber().get(0).getNumber(),lista);
									}
									if(!tacho_places.containsKey(vc.getFullCardNumber().getCardNumber().getDriverIdentification())){
										tacho_places.put(driver.getCardNumber().get(0).getNumber(),vc.getPlaces());
									}
									if(!drivers.containsKey(driver.getCardNumber())){
										drivers.put(driver.getCardNumber().get(0).getNumber(),driver);
									}



								}
								// ----> Activity for driver
								Iterator iter_tacho=tacho_activity.entrySet().iterator();
								while(iter_tacho.hasNext()){
									Map.Entry<String,ArrayList> t=(Map.Entry) iter_tacho.next();
									activityParse=new com.thingtrack.parse.Activity();
									activityParse.setDate(activity.getTimeReal());
									activityParse.setActivityChangeInfo(t.getValue());
									activityParse.setOrganizationId(organizationId);
									activityParse.getFiles().add(filename);
									if(t.getKey()=="withoutDriver"){
										activityParse.setCardNumber(null);
									}else{
										activityParse.setVehicles(tacho_vehicles.get(t.getKey()));
										activityParse.setPlaces(tacho_places.get(t.getKey()));

										int distance=0;
										for(int i=0;i<tacho_vehicles.get(t.getKey()).size();i++){
											distance+=tacho_vehicles.get(t.getKey()).get(i).getDistance();
										}

										activityParse.setDistance(distance);
										activityParse.setCardNumber(drivers.get(t.getKey()).getCardNumber().get(0).getNumber());
									}

									this.tachos.getActivity().add(activityParse);
								}
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
		
		this.resumen=(Resumen)this.listBlock.get(Trep.VU_RESUMEN.toString());
		this.vehicles=new HashMap<String, Vehicle>();
		Vehicle v=new Vehicle(this.resumen);
		this.vehicles.put(v.getRegistration(),v);
		this.listActivity=(ListActivity) this.listBlock.get(Trep.VU_ACTIVITY.toString());		
		this.eventFault=(EventsFaults) this.listBlock.get(Trep.VU_EVENT_FAULT.toString());
		this.speed=(Speed) this.listBlock.get(Trep.VU_SPEED.toString());
		this.technical=(Technical) this.listBlock.get(Trep.VU_TECHNICAL.toString());
		Iterator it=this.tachos.getActivity().iterator();
		String reg=this.resumen.getVehicleRegistrationIdentification().getVehicleRegistrationNumber();
		while(it.hasNext()){
			com.thingtrack.parse.Activity a=(com.thingtrack.parse.Activity)it.next();
			a.setRegistration(reg);
			for(int i=0;i<a.getVehicles().size();i++){
				a.getVehicles().get(i).setRegistration(reg);
			}

		}

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

	/**
	 * @return the tachos
	 */
	public Tacho getTachos() {
		return tachos;
	}

	/**
	 * @param tachos the tachos to set
	 */
	public void setTachos(Tacho tachos) {
		this.tachos = tachos;
	}

	/**
	 * @return the drivers
	 */
	public HashMap getDrivers() {
		return drivers;
	}

	


	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(HashMap<String,Vehicle> vehicle) {
		this.vehicles = vehicle;
	}

	/**
	 * @return the vehicles
	 */
	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(HashMap<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * @param drivers the drivers to set
	 */
	public void setDrivers(HashMap<String, Driver> drivers) {
		this.drivers = drivers;
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
