/**
 * 
 */
package com.thingtrack.parse;

import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.PlaceRecord;
import org.tacografo.file.vublock.subblock.VuPlaceDailyWorkPeriodRecord;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author negrero
 *
 */
public class Places {
	
	private String placeBegin;
	private String placeRegionBegin;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT")
	private Date fromDate;
	private String placeEnd;
	private String placeRegionEnd;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT")
	private Date toDate;

	/**
	 * 
	 */
	public Places() {
		// TODO Auto-generated constructor stub
	}

	public Places(VuPlaceDailyWorkPeriodRecord vpdwpr) {
		String str=vpdwpr.getPlacerecord().getEntryTypeDailyWorkPeriod().substring(0, 3);
		if(str.equals("Beg")){
			this.placeBegin=vpdwpr.getPlacerecord().getDailyWorkPeriodCountry();
			this.placeRegionBegin=vpdwpr.getPlacerecord().getDailyWorkPeriodRegion();
			this.fromDate=vpdwpr.getPlacerecord().getEntryTime();
		}else{
			this.placeEnd=vpdwpr.getPlacerecord().getDailyWorkPeriodCountry();
			this.placeRegionEnd=vpdwpr.getPlacerecord().getDailyWorkPeriodRegion();
			this.toDate=vpdwpr.getPlacerecord().getEntryTime();
		}
	}
	public Places(PlaceRecord pr) {		
		String str=pr.getEntryTypeDailyWorkPeriod().substring(0, 3);
		long d=pr.getEntryTime().getTime()-(pr.getEntryTime().getSeconds()*1000);
		if(str.equals("Beg")){
			this.placeBegin=pr.getDailyWorkPeriodCountry();
			this.placeRegionBegin=pr.getDailyWorkPeriodRegion();
			this.fromDate=new Date(d);
		}else{
			this.placeEnd=pr.getDailyWorkPeriodCountry();
			this.placeRegionEnd=pr.getDailyWorkPeriodRegion();
			this.toDate=new Date(d);
		}
	}
	public void setPlaces(PlaceRecord pr){
		String str=pr.getEntryTypeDailyWorkPeriod().substring(0, 3);
				if(str.equals("Beg")){
					this.placeBegin=pr.getDailyWorkPeriodCountry();
					this.placeRegionBegin=pr.getDailyWorkPeriodRegion();
					this.fromDate=pr.getEntryTime();
				}else{
					this.placeEnd=pr.getDailyWorkPeriodCountry();
					this.placeRegionEnd=pr.getDailyWorkPeriodRegion();
					this.toDate=pr.getEntryTime();
				}
	}
	public void setPlaces(VuPlaceDailyWorkPeriodRecord vpdwpr){
		String str=vpdwpr.getPlacerecord().getEntryTypeDailyWorkPeriod().substring(0, 3);
		if(str.equals("Beg")){
			this.placeBegin=vpdwpr.getPlacerecord().getDailyWorkPeriodCountry();
			this.placeRegionBegin=vpdwpr.getPlacerecord().getDailyWorkPeriodRegion();
			this.fromDate=vpdwpr.getPlacerecord().getEntryTime();
		}else{
			this.placeEnd=vpdwpr.getPlacerecord().getDailyWorkPeriodCountry();
			this.placeRegionEnd=vpdwpr.getPlacerecord().getDailyWorkPeriodRegion();
			this.toDate=vpdwpr.getPlacerecord().getEntryTime();
		}
	}
	/**
	 * @return the placeBegin
	 */
	public String getPlaceBegin() {
		return placeBegin;
	}

	/**
	 * @param placeBegin the placeBegin to set
	 */
	public void setPlaceBegin(String placeBegin) {
		this.placeBegin = placeBegin;
	}

	/**
	 * @return the placeReginBegin
	 */
	public String getPlaceReginBegin() {
		return placeRegionBegin;
	}

	/**
	 * @param placeReginBegin the placeReginBegin to set
	 */
	public void setPlaceReginBegin(String placeReginBegin) {
		this.placeRegionBegin = placeReginBegin;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the placeEnd
	 */
	public String getPlaceEnd() {
		return placeEnd;
	}

	/**
	 * @param placeEnd the placeEnd to set
	 */
	public void setPlaceEnd(String placeEnd) {
		this.placeEnd = placeEnd;
	}

	/**
	 * @return the placeReginEnd
	 */
	public String getPlaceReginEnd() {
		return placeRegionBegin;
	}

	/**
	 * @param placeReginEnd the placeReginEnd to set
	 */
	public void setPlaceReginEnd(String placeReginEnd) {
		this.placeRegionBegin = placeReginEnd;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
