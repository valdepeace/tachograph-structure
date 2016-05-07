/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.tacografo.file.cardblockdriver.Sizes;
import org.tacografo.tiposdatos.RealTime;
import org.tacografo.tiposdatos.Number;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 2.5. CardActivityDailyRecord
 *
 * Informaci�n almacenada en una tarjeta y relativa a las actividades del conductor en un d�a civil concreto. Este tipo de datos est� relacionado con los requisitos 199 y 219.
 *
 * CardActivityDailyRecord::= SEQUENCE {
 *
 * activityPreviousRecordLength INTEGER(0..CardActivityLengthRange),
 * 
 * activityRecordDate TimeReal,
 *
 * activityDailyPresenceCounter DailyPresenceCounter,
 *
 * activityDayDistance Distance,
 *
 * activityChangeInfo SET SIZE(1..1440) OF ActivityChangeInfo
 *
 * }
 * 
 * activityPreviousRecordLength es la longitud total del registro diario anterior, expresada en bytes. 
 * El valor m�ximo viene dado por la longitud de la CADENA DE OCTETOS que contiene dichos registros (v�ase CardActivityLengthRange, apartado 3). Cuando este registro es el registro diario m�s antiguo, el valor de activityPreviousRecordLength debe configurarse a 0. 
 * 
 * activityRecordLength es la longitud total de este registro, expresada en bytes. 
 * El valor m�ximo viene dado por la longitud de la CADENA DE OCTETOS que contiene dichos registros.
 * 
 * activityRecordDate es la fecha del registro. 
 *
 * activityDailyPresenceCounter es el contador de presencia diaria para esa tarjeta en ese d�a.
 * 
 * activityDayDistance es la distancia total recorrida ese d�a.
 *
 * activityChangeInfo es el conjunto de datos de ActivityChangeInfo correspondientes al conductor en ese d�a. Puede contener 1440 valores como m�ximo (un cambio de actividad cada minuto). Este conjunto incluye siempre la ActivityChangeInfo que codifica el estado del conductor a las 00:00.
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class CardActivityDailyRecord {		
	private int activityPreviousRecordLength; //2 byte
	private int activityRecordLength; // 2 byte 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="GMT")
	private Date activityRecordDate; // 4 byte
	private int activityDailyPresenceCounter; // 2 size
	private int activityDayDistance; // 2 size
	private ArrayList <ActivityChangeInfo> activityChangeInfo; // size 2
	private ArrayList<com.thingtrack.parse.ActivityChangeInfo> comActivityChangeInfo;
	
	
	
	public CardActivityDailyRecord() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public CardActivityDailyRecord(byte[] bytes){
		
		int start=0;
		this.activityPreviousRecordLength= Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYPREVIUSRECORDLENGTH.getMax()));
		this.activityRecordLength= Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYRECORDLENGTH.getMax()));
		this.activityRecordDate = RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYRECORDDATE.getMax()));
		//DailyPresenceCounter adpc=new DailyPresenceCounter(Arrays.copyOfRange(bytes, start, start+=2));
		this.activityDailyPresenceCounter = Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYDAILYPRESENCECOUNTER.getMax()));	
		//Distance d=new Distance(Arrays.copyOfRange(bytes, start, start+=2));
		this.activityDayDistance=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYDAYDISTANCE.getMax()));	
		this.activityChangeInfo=new ArrayList<ActivityChangeInfo>();
		this.comActivityChangeInfo=new ArrayList<com.thingtrack.parse.ActivityChangeInfo>();

		Calendar c= Calendar.getInstance();
		Date d;
		long num=0;
		for (int i=start; i<this.activityRecordLength;i+=2){
			ActivityChangeInfo activityChangeInfo=new ActivityChangeInfo(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYCHANGEINFO.getMin()));			
			this.activityChangeInfo.add(activityChangeInfo);
			com.thingtrack.parse.ActivityChangeInfo caci=new com.thingtrack.parse.ActivityChangeInfo(activityChangeInfo);
			c.setTime(this.activityRecordDate);
			c.set(Calendar.HOUR,activityChangeInfo.getHours());
			c.set(Calendar.MINUTE,activityChangeInfo.getMin());
			//caci.setFromTime(c.getTime());
			num=this.activityRecordDate.getTime()+(activityChangeInfo.getHours()*60*60*1000)+(activityChangeInfo.getMin()*60*1000);
			d=new Date(num);
			//d.setHours(activityChangeInfo.getHours());
			//d.setMinutes(activityChangeInfo.getMin());
			caci.setFromTime(d);
			if(this.getComActivityChangeInfo().size()>0){
				this.getComActivityChangeInfo().get(this.getComActivityChangeInfo().size()-1).setToTime(caci.getFromTime());
			}
			this.getComActivityChangeInfo().add(caci);
		}
				
	
		
	}

	/**
	 * @return the comActivityChangeInfo
	 */
	public ArrayList<com.thingtrack.parse.ActivityChangeInfo> getComActivityChangeInfo() {
		return comActivityChangeInfo;
	}
	/**
	 * @param comActivityChangeInfo the comActivityChangeInfo to set
	 */
	public void setComActivityChangeInfo(ArrayList<com.thingtrack.parse.ActivityChangeInfo> comActivityChangeInfo) {
		this.comActivityChangeInfo = comActivityChangeInfo;
	}
	/**
	 * @param activityDailyPresenceCounter the activityDailyPresenceCounter to set
	 */
	public void setActivityDailyPresenceCounter(int activityDailyPresenceCounter) {
		this.activityDailyPresenceCounter = activityDailyPresenceCounter;
	}
	/**
	 * @param activityDayDistance the activityDayDistance to set
	 */
	public void setActivityDayDistance(int activityDayDistance) {
		this.activityDayDistance = activityDayDistance;
	}
	/**
	 * Obtiene la longitud total del registro diario anterior, expresada en bytes.
	 * @return the activityPreviousRecordLength
	 */
	public int getActivityPreviousRecordLength() {
		return activityPreviousRecordLength;
	}

	/**
	 * Asigna la longitud total del registro diario anterior, expresada en bytes.
	 * @param activityPreviousRecordLength the activityPreviousRecordLength to set
	 */
	public void setActivityPreviousRecordLength(int activityPreviousRecordLength) {
		this.activityPreviousRecordLength = activityPreviousRecordLength;
	}

	/**
	 * Obtiene la longitud total de este registro, expresada en bytes.
	 * @return the activityRecordLength
	 */
	public int getActivityRecordLength() {
		return activityRecordLength;
	}

	/**
	 * Asigna la longitud total de este registro, expresada en bytes.
	 * @param activityRecordLength the activityRecordLength to set
	 */
	public void setActivityRecordLength(int activityRecordLength) {
		this.activityRecordLength = activityRecordLength;
	}

	/**
	 * Obtiene la fecha del registro. 
	 * @return the activityRecordDate
	 */
	public Date getActivityRecordDate() {
		return activityRecordDate;
	}

	/**
	 * Asigna la fecha del registro. 
	 * @param activityRecordDate the activityRecordDate to set
	 */
	public void setActivityRecordDate(Date activityRecordDate) {
		this.activityRecordDate = activityRecordDate;
	}

	/**
	 * Obtiene el contador de presencia diaria para esa tarjeta en ese d�a.
	 * @return the activityDailyPresenceCounter
	 */
	public int getActivityDailyPresenceCounter() {
		return activityDailyPresenceCounter;
	}

	/**
	 * Asigna el contador de presencia diaria para esa tarjeta en ese d�a.
	 * @param activityDailyPresenceCounter the activityDailyPresenceCounter to set
	 */
	public void setActivityDailyPresenceCounter(short activityDailyPresenceCounter) {
		this.activityDailyPresenceCounter = activityDailyPresenceCounter;
	}

	/**
	 * Obtiene la distancia total recorrida ese d�a.
	 * @return the activityDayDistance
	 */
	public int getActivityDayDistance() {
		return activityDayDistance;
	}

	/**
	 * Asigna la distancia total recorrida ese d�a.
	 * @param activityDayDistance the activityDayDistance to set
	 */
	public void setActivityDayDistance(short activityDayDistance) {
		this.activityDayDistance = activityDayDistance;
	}

	/**
	 * Obtiene el conjunto de datos de ActivityChangeInfo correspondientes al conductor en ese d�a. 
	 * Puede contener 1440 valores como m�ximo (un cambio de actividad cada minuto). 
	 * Este conjunto incluye siempre la ActivityChangeInfo que codifica el estado del conductor a las 00:00.
	 * @return the activityChangeInfo
	 */
	public ArrayList<ActivityChangeInfo> getActivityChangeInfo() {
		return activityChangeInfo;
	}

	/**
	 * Asigna el conjunto de datos de ActivityChangeInfo correspondientes al conductor en ese d�a. 
	 * Puede contener 1440 valores como m�ximo (un cambio de actividad cada minuto). 
	 * Este conjunto incluye siempre la ActivityChangeInfo que codifica el estado del conductor a las 00:00.
	 * @param activityChangeInfo the activityChangeInfo to set
	 */
	public void setActivityChangeInfo(
			ArrayList<ActivityChangeInfo> activityChangeInfo) {
		this.activityChangeInfo = activityChangeInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nCardActivityDailyRecord [activityPreviousRecordLength="
				+ activityPreviousRecordLength + ", activityRecordLength="
				+ activityRecordLength + ", activityRecordDate="
				+ activityRecordDate + ", activityDailyPresenceCounter="
				+ activityDailyPresenceCounter + ", activityDayDistance="
				+ activityDayDistance + ", activityChangeInfo="
				+ activityChangeInfo.toString() + "]";
	}
	
}
