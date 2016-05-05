/**
 * 
 */
package com.thingtrack.parse;

import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.CardVehicleRecord;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author negrero
 *
 */
public class VehicleChangeInfo {

	private String registration;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT")
	private Date fromDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",timezone="GMT")
	private Date toDate;
	private int distance;
	
		/**
	 * 
	 */
	public VehicleChangeInfo() {
		// TODO Auto-generated constructor stub
	}

		public VehicleChangeInfo(CardVehicleRecord cvr) {
			this.registration=cvr.getVehicleRegistration().getVehicleRegistrationNumber();
			long num=0;
			num=cvr.getVehicleFirstUse().getTime()-cvr.getVehicleFirstUse().getSeconds()*1000;
			this.fromDate=new Date(num);
			num=cvr.getVehicleLastUse().getTime()-cvr.getVehicleLastUse().getSeconds()*1000;
			this.toDate=cvr.getVehicleLastUse();
			this.distance=cvr.getVehicleOdometerEnd()-cvr.getVehicleOdometerBegin();
		}

		/**
		 * @return the registration
		 */
		public String getRegistration() {
			return registration;
		}

		/**
		 * @param registration the registration to set
		 */
		public void setRegistration(String registration) {
			this.registration = registration;
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

		/**
		 * @return the distance
		 */
		public int getDistance() {
			return distance;
		}

		/**
		 * @param distance the distance to set
		 */
		public void setDistance(int distance) {
			this.distance = distance;
		}

}
