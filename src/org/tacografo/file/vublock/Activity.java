/**
 * 
 */
package org.tacografo.file.vublock;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;

import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.tacografo.file.Block;
import org.tacografo.file.cardblockdriver.SpecificConditionRecord;
import org.tacografo.file.cardblockdriver.subblock.ActivityChangeInfo;
import org.tacografo.file.vublock.subblock.VuCardIWRecord;
import org.tacografo.file.vublock.subblock.VuPlaceDailyWorkPeriodRecord;
import org.tacografo.tiposdatos.RealTime;

import com.thingtrack.parse.Places;

import org.tacografo.tiposdatos.Number;
import org.tacografo.tiposdatos.OctetString;



/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class Activity extends Block{
		
	
	private Date timeReal;
	
	// 2.81. OdometerValueMidnight
	// La lectura del cuentakilómetros del vehículo a medianoche de un día determinado (requisito 090).
	// OdometerValueMidnight::= OdometerShort
	// Asignación de valor: no hay más especificaciones.
	
	private int OdometerValueMidnight;
	
	// 2.119. VuCardIWData
	// Información almacenada en una unidad intravehicular y relativa a los ciclos de inserción y
	// extracción de tarjetas de conductor o tarjetas del centro de ensayo en la unidad intravehicular (requisito 081).
	// VuCardIWData::= SEQUENCE {
	// noOfIWRecords INTEGER(0..216-1),
	// vuCardIWRecords SET SIZE(noOfIWRecords) OF VuCardIWRecord
	// }
	// noOfIWRecords es el número de registros que hay en el conjunto vuCardIWRecords.
	// vuCardIWRecords es el conjunto de registros relativos a los ciclos de inserción y extracción de la tarjeta.
	private int noOfIWRecords;
	private ArrayList<VuCardIWRecord> vuCardIWData;
	// 2.115. VuActivityDailyData
	// Información almacenada en una VU y relativa a los cambios de actividad y/o los cambios del régimen de conducción y/o 
	// los cambios del estado de la tarjeta que tengan lugar en un día civil determinado (requisito 084) y a los estados de las ranuras a las 00.00 de ese día.
	// VuActivityDailyData::= SEQUENCE {
	// noOfActivityChanges INTEGER SIZE(0..1440),
	// activityChangeInfos SET SIZE(noOfActivityChanges) OF ActivityChangeInfo
	// }
	// noOfActivityChanges es el número de palabras que hay en el conjunto activityChangeInfos.
	// activityChangeInfos es un conjunto de palabras que se almacenan en la VU a lo largo del día y 
	// contiene información sobre los cambios de actividad realizados ese día. Siempre incluye dos palabras de activityChangeInfo que dan el estado de las dos ranuras a las 00.00 de ese día.
	private int noOfActivityChanges;
	private ArrayList<ActivityChangeInfo> vuActivityDailyData;
	
	// 2.143. VuPlaceDailyWorkPeriodData
	// Información almacenada en una unidad intravehicular y relativa a los lugares donde los conductores comienzan o terminan los períodos de trabajo diarios (requisito 087).
	// VuPlaceDailyWorkPeriodData::= SEQUENCE {
	// noOfPlaceRecords INTEGER(0..255),
	// vuPlaceDailyWorkPeriodRecords SET SIZE(noOfPlaceRecords) OF VuPlaceDailyWorkPeriodRecord
	// }
	// noOfPlaceRecords es el número de registros incluidos en el conjunto vuPlaceDailyWorkPeriodRecords.
	// vuPlaceDailyWorkPeriodRecords es el conjunto de registros relativos a lugares.
	
	private int noOfPlaceRecords;
	private ArrayList<VuPlaceDailyWorkPeriodRecord> vuPlaceDailyWorkPeriodData;
	
	// 2.151. VuSpecificConditionData
	// Información almacenada en una unidad intravehicular y relativa a condiciones específicas.
	// VuSpecificConditionData::= SEQUENCE {
	// noOfSpecificConditionRecords INTEGER(0..216-1)
	// specificConditionRecords SET SIZE (noOfSpecificConditionRecords) OF SpecificConditionRecord
	// }
	// noOfSpecificConditionRecords es el número de registros incluidos en el conjunto specificConditionRecords set.
	// specificConditionRecords es el conjunto de registros relativos a condiciones específicas.
	private int noOfSpecificConditionRecords;
	private ArrayList<SpecificConditionRecord> vuSpecificConditionData;

	// 2.101. Signature
		// Una firma digital.
		// Signature::= OCTET STRING (SIZE(128))
		// Asignaci�n de valor: con arreglo a lo dispuesto en el Ap�ndice 11 (Mecanismos de seguridad comunes).
	private String signature;
	
	private int size=0;
	
	private com.thingtrack.parse.Activity activity;
	private ArrayList<com.thingtrack.parse.Driver> drivers;
	private HashMap<String, ArrayList> tacho;
	
	
	public Activity(byte[] bytes) throws Exception{
		int start=0;

		this.timeReal=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.TIMEREAL.getSize()));

		this.OdometerValueMidnight=Number.getInteger_24(Arrays.copyOfRange(bytes, start, start+=Sizes.ODOMETERVALUEMINDNIGHT.getSize()));
				
		this.noOfIWRecords=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFVUCARDIWRECORDS.getSize()));
		this.vuCardIWData = new ArrayList();
		this.getListvuCardIWData(Arrays.copyOfRange(bytes, start, start+=Sizes.VUCARDIWRECORD.getSize()*this.noOfIWRecords));
		
		this.noOfActivityChanges=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFACTIVITYCHANGES.getSize()));
		this.vuActivityDailyData = new ArrayList();
		this.getListVuActivityDailyData(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYCHANGEINFO.getSize()*this.noOfActivityChanges));
	
		this.noOfPlaceRecords=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFPLACERECORDS.getSize()));
		this.vuPlaceDailyWorkPeriodData = new ArrayList();
		this.getListVuPlaceDailyWorkPeriodData(Arrays.copyOfRange(bytes, start, start+=Sizes.VUPLACEDAILYWORKPERIODRECORD.getSize()*this.noOfPlaceRecords));
		
		
		this.noOfSpecificConditionRecords=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFSPECIFICCONDITIONSRECORDS.getSize()));
		this.vuSpecificConditionData = new ArrayList();
		this.getListVuSpecificConditionData(Arrays.copyOfRange(bytes, start, start+=Sizes.SPECIFICCONDITIONRECORD.getSize()*this.noOfSpecificConditionRecords));
	
		
		this.signature=OctetString.getHexString(Arrays.copyOfRange(bytes, start, start+=Sizes.SIGNATURE_TREP2.getSize()));
		this.size=start;
		
		this.activity = new com.thingtrack.parse.Activity();
		this.drivers = new ArrayList();
	}

	private void getListVuSpecificConditionData(byte[] bytes) {
		SpecificConditionRecord scr;
		int end=bytes.length/Sizes.SPECIFICCONDITIONRECORD.getSize();
		int start=0;
		for(int i=0; i<end;i++){
			scr=new SpecificConditionRecord(Arrays.copyOfRange(bytes, start, start+=Sizes.SPECIFICCONDITIONRECORD.getSize()));
			this.vuSpecificConditionData.add(scr);
		}
		
	}

	private void getListVuPlaceDailyWorkPeriodData(byte[] bytes) throws UnsupportedEncodingException {
		VuPlaceDailyWorkPeriodRecord vpdwpr;
		int end = bytes.length / Sizes.VUPLACEDAILYWORKPERIODRECORD.getSize();
		int start = 0;
		com.thingtrack.parse.Places place;
		com.thingtrack.parse.Places p;
		for (int i = 0; i < end; i++) {
			vpdwpr = new VuPlaceDailyWorkPeriodRecord(
					Arrays.copyOfRange(bytes, start, start += Sizes.VUPLACEDAILYWORKPERIODRECORD.getSize()));
			this.vuPlaceDailyWorkPeriodData.add(vpdwpr);
			Iterator<VuCardIWRecord> it = this.vuCardIWData.iterator();
			if (vpdwpr.getPlacerecord().getEntryTime().getTime() > 0) {
				while (it.hasNext()) {
					VuCardIWRecord el = it.next();
					place = new com.thingtrack.parse.Places(vpdwpr);
					el.getPlaces().add(place);

					String cn1 = el.getFullCardNumber().getCardNumber().getDriverIdentification();
					String cn2 = vpdwpr.getFullCardNumber().getCardNumber().getDriverIdentification();
					place = new com.thingtrack.parse.Places(vpdwpr);

					if (cn1.equals(cn2)) {
						if (el.getPlaces().isEmpty()) {
							el.getPlaces().add(place);
						} else {
							boolean exist = true;
							Iterator ite = el.getPlaces().iterator();
							while (ite.hasNext()) {
								p = (Places) ite.next();
								String str = vpdwpr.getPlacerecord().getEntryTypeDailyWorkPeriod().substring(0, 3);
								if (str.equals("Beg") && p.getPlaceBegin() == null) {
									el.getPlaces().get(el.getPlaces().lastIndexOf(p)).setPlaces(vpdwpr);
									exist = false;
								} else {
									if (p.getPlaceEnd() == null) {
										el.getPlaces().get(el.getPlaces().lastIndexOf(p)).setPlaces(vpdwpr);
										exist = false;
									}
								}
							}
							if (exist) {
								el.getPlaces().add(place);
							}
						}
					}
				}
			}

		}
		
	}

	private void getListVuActivityDailyData(byte[] bytes) {
		ActivityChangeInfo aci;
		com.thingtrack.parse.ActivityChangeInfo ctpaci;
		int end=bytes.length/Sizes.ACTIVITYCHANGEINFO.getSize();
		int start=0;
		HashMap<String, ArrayList> tacho=new HashMap();
		ArrayList lista_changeInfo;

		for(int i=0; i<end;i++){
			aci=new ActivityChangeInfo(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYCHANGEINFO.getSize()));
			ctpaci=new com.thingtrack.parse.ActivityChangeInfo(aci);			
			long fecha=this.timeReal.getTime();
			fecha+=aci.getHours()*60*60*1000;
			fecha+=aci.getMin()*60*1000;
			Date d=new Date(fecha);						
			Iterator<VuCardIWRecord> it=this.vuCardIWData.iterator();
			ctpaci.setFromTime(d);
			com.thingtrack.parse.ActivityChangeInfo aux=ctpaci;
			if (aci.getP()=="no insertada"){
				if(tacho.containsKey("withoutDriver")){
					tacho.get("withoutDriver").add(ctpaci);
				}else{
					lista_changeInfo=new ArrayList();
					lista_changeInfo.add(ctpaci);
					tacho.put("withoutDriver",lista_changeInfo);
				}
			}else{
				while(it.hasNext()){
					// ----> driver el
					VuCardIWRecord el = it.next();
					Date b=el.getCardInsertionTime();
					Date e=el.getCardWithdrawalTime();
					b.setSeconds(0);
					e.setSeconds(0);
					if(d.compareTo(b)>=0 && e.compareTo(d)>=0){
						if(tacho.containsKey(el.getFullCardNumber().getCardNumber().getDriverIdentification())){
							tacho.get(el.getFullCardNumber().getCardNumber().getDriverIdentification()).add(ctpaci);
						}else{
							lista_changeInfo=new ArrayList();
							lista_changeInfo.add(ctpaci);
							tacho.put(el.getFullCardNumber().getCardNumber().getDriverIdentification(),lista_changeInfo);
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
			this.vuActivityDailyData.add(aci);
		}
		this.tacho=tacho;
	}

	private void getListvuCardIWData(byte[] bytes) throws UnsupportedEncodingException {
		VuCardIWRecord vir;
		int end=bytes.length/Sizes.VUCARDIWRECORD.getSize();
		int start=0;
		for(int i=0; i<end;i++){
			vir=new VuCardIWRecord(Arrays.copyOfRange(bytes, start, start+=Sizes.VUCARDIWRECORD.getSize()));
			this.vuCardIWData.add(vir);
		}
		
		
	}

	public HashMap<String, ArrayList> getTacho() {
		return tacho;
	}

	public void setTacho(HashMap<String, ArrayList> tacho) {
		this.tacho = tacho;
	}

	/**
	 * @return the timeReal
	 */
	public Date getTimeReal() {
		return timeReal;
	}

	/**
	 * @param timeReal the timeReal to set
	 */
	public void setTimeReal(Date timeReal) {
		this.timeReal = timeReal;
	}

	/**
	 * @return the odometerValueMidnight
	 */
	public int getOdometerValueMidnight() {
		return OdometerValueMidnight;
	}

	/**
	 * @param odometerValueMidnight the odometerValueMidnight to set
	 */
	public void setOdometerValueMidnight(int odometerValueMidnight) {
		OdometerValueMidnight = odometerValueMidnight;
	}

	/**
	 * @return the noOfIWRecords
	 */
	public int getNoOfIWRecords() {
		return noOfIWRecords;
	}

	/**
	 * @param noOfIWRecords the noOfIWRecords to set
	 */
	public void setNoOfIWRecords(int noOfIWRecords) {
		this.noOfIWRecords = noOfIWRecords;
	}

	/**
	 * @return the vuCardIWData
	 */
	public ArrayList<VuCardIWRecord> getVuCardIWData() {
		return vuCardIWData;
	}

	/**
	 * @param vuCardIWData the vuCardIWData to set
	 */
	public void setVuCardIWData(ArrayList<VuCardIWRecord> vuCardIWData) {
		this.vuCardIWData = vuCardIWData;
	}

	/**
	 * @return the noOfActivityChanges
	 */
	public int getNoOfActivityChanges() {
		return noOfActivityChanges;
	}

	/**
	 * @param noOfActivityChanges the noOfActivityChanges to set
	 */
	public void setNoOfActivityChanges(int noOfActivityChanges) {
		this.noOfActivityChanges = noOfActivityChanges;
	}

	/**
	 * @return the vuActivityDailyData
	 */
	public ArrayList<ActivityChangeInfo> getVuActivityDailyData() {
		return vuActivityDailyData;
	}

	/**
	 * @param vuActivityDailyData the vuActivityDailyData to set
	 */
	public void setVuActivityDailyData(ArrayList<ActivityChangeInfo> vuActivityDailyData) {
		this.vuActivityDailyData = vuActivityDailyData;
	}

	/**
	 * @return the noOfPlaceRecords
	 */
	public int getNoOfPlaceRecords() {
		return noOfPlaceRecords;
	}

	/**
	 * @param noOfPlaceRecords the noOfPlaceRecords to set
	 */
	public void setNoOfPlaceRecords(int noOfPlaceRecords) {
		this.noOfPlaceRecords = noOfPlaceRecords;
	}

	/**
	 * @return the vuPlaceDailyWorkPeriodData
	 */
	public ArrayList<VuPlaceDailyWorkPeriodRecord> getVuPlaceDailyWorkPeriodData() {
		return vuPlaceDailyWorkPeriodData;
	}

	/**
	 * @param vuPlaceDailyWorkPeriodData the vuPlaceDailyWorkPeriodData to set
	 */
	public void setVuPlaceDailyWorkPeriodData(ArrayList<VuPlaceDailyWorkPeriodRecord> vuPlaceDailyWorkPeriodData) {
		this.vuPlaceDailyWorkPeriodData = vuPlaceDailyWorkPeriodData;
	}

	/**
	 * @return the noOfSpecificConditionRecords
	 */
	public int getNoOfSpecificConditionRecords() {
		return noOfSpecificConditionRecords;
	}

	/**
	 * @param noOfSpecificConditionRecords the noOfSpecificConditionRecords to set
	 */
	public void setNoOfSpecificConditionRecords(int noOfSpecificConditionRecords) {
		this.noOfSpecificConditionRecords = noOfSpecificConditionRecords;
	}

	/**
	 * @return the vuSpecificConditionData
	 */
	public ArrayList<SpecificConditionRecord> getVuSpecificConditionData() {
		return vuSpecificConditionData;
	}

	/**
	 * @param vuSpecificConditionData the vuSpecificConditionData to set
	 */
	public void setVuSpecificConditionData(ArrayList<SpecificConditionRecord> vuSpecificConditionData) {
		this.vuSpecificConditionData = vuSpecificConditionData;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Activity [timeReal=" + timeReal + ", OdometerValueMidnight=" + OdometerValueMidnight
				+ ", noOfIWRecords=" + noOfIWRecords + ", vuCardIWData=" + vuCardIWData + ", noOfActivityChanges="
				+ noOfActivityChanges + ", vuActivityDailyData=" + vuActivityDailyData + ", noOfPlaceRecords="
				+ noOfPlaceRecords + ", vuPlaceDailyWorkPeriodData=" + vuPlaceDailyWorkPeriodData
				+ ", noOfSpecificConditionRecords=" + noOfSpecificConditionRecords + ", vuSpecificConditionData="
				+ vuSpecificConditionData + ", signature=" + signature + "]";
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
	

}
