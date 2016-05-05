package com.thingtrack.parse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.tacografo.file.cardblockdriver.CardPlaceDailyWorkPeriod;
import org.tacografo.file.cardblockdriver.CardVehiclesUsed;
import org.tacografo.file.vublock.subblock.VuCardIWRecord;

public class Tacho {
	
	private String id;
	private String relationId;
	private ArrayList<com.thingtrack.parse.Activity> activity;
	private ArrayList<com.thingtrack.parse.Event> event;
	private ArrayList<com.thingtrack.parse.Fault> fault;
	private ArrayList<com.thingtrack.parse.OverSpeed> overSpeed;
	public Tacho(){
		this.activity=new ArrayList();
	}
	
	public Tacho(Activity activity,Driver driver, VuCardIWRecord vc){
		this.id=driver.getCardNumber().get(0).getNumber();
		this.activity.add(activity);
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the relationId
	 */
	public String getRelationId() {
		return relationId;
	}
	/**
	 * @param relationId the relationId to set
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	/**
	 * @return the activity
	 */
	public ArrayList<com.thingtrack.parse.Activity> getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(ArrayList<com.thingtrack.parse.Activity> activity) {
		this.activity = activity;
	}
	/**
	 * @return the event
	 */
	public ArrayList<com.thingtrack.parse.Event> getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(ArrayList<com.thingtrack.parse.Event> event) {
		this.event = event;
	}
	/**
	 * @return the fault
	 */
	public ArrayList<com.thingtrack.parse.Fault> getFault() {
		return fault;
	}
	/**
	 * @param fault the fault to set
	 */
	public void setFault(ArrayList<com.thingtrack.parse.Fault> fault) {
		this.fault = fault;
	}
	/**
	 * @return the overSpeed
	 */
	public ArrayList<com.thingtrack.parse.OverSpeed> getOverSpeed() {
		return overSpeed;
	}
	/**
	 * @param overSpeed the overSpeed to set
	 */
	public void setOverSpeed(ArrayList<com.thingtrack.parse.OverSpeed> overSpeed) {
		this.overSpeed = overSpeed;
	}
	

	public Tacho(HashMap<String, Activity> activity, ArrayList<Places> places,
		ArrayList<VehicleChangeInfo> vehicles) {
		Iterator it=vehicles.iterator();
		VehicleChangeInfo v;
		this.activity= new ArrayList<Activity>();
		while(it.hasNext()){
			v=(VehicleChangeInfo) it.next();
			String f=v.getFromDate().toGMTString();
			this.getActivity().add(activity.get(f));

			
		}
		
	}

	
}
