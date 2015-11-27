/**
 * 
 */
package org.tacografo.file.vublock;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.Block;
import org.tacografo.file.cardblockdriver.SpecificConditionRecord;
import org.tacografo.file.cardblockdriver.subblock.ActivityChangeInfo;
import org.tacografo.file.vublock.subblock.VuCardIWRecord;
import org.tacografo.file.vublock.subblock.VuPlaceDailyWorkPeriodRecord;
import org.tacografo.tiposdatos.RealTime;
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
	public Activity(byte[] bytes) throws Exception{
		int start=0;
		this.timeReal=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.TIMEREAL.getSize()));
		//System.out.println(this.timeReal);
		
		this.OdometerValueMidnight=Number.getInteger_24(Arrays.copyOfRange(bytes, start, start+=Sizes.ODOMETERVALUEMINDNIGHT.getSize()));
		//System.out.println(this.OdometerValueMidnight);
				
		this.noOfIWRecords=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFVUCARDIWRECORDS.getSize()));
		this.vuCardIWData = new ArrayList();
		this.getListvuCardIWData(Arrays.copyOfRange(bytes, start, start+=Sizes.VUCARDIWRECORD.getSize()*this.noOfIWRecords));
		//System.out.println(this.noOfIWRecords);
		//System.out.println(this.vuCardIWData.toString());
		
		
		this.noOfActivityChanges=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFACTIVITYCHANGES.getSize()));
		this.vuActivityDailyData = new ArrayList();
		//System.out.println("start "+start);
		this.getListVuActivityDailyData(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYCHANGEINFO.getSize()*this.noOfActivityChanges-1));
		//System.out.println(this.vuActivityDailyData.toString());
		//System.out.println(this.noOfActivityChanges);
		//System.out.println("start "+start);
		
		//System.out.println("byte 1:"+Integer.toBinaryString(bytes[start])+" byte 2: "+Integer.toBinaryString(bytes[start+1]));
		this.noOfPlaceRecords=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFPLACERECORDS.getSize()));
		//System.out.println(this.noOfPlaceRecords);
		this.vuPlaceDailyWorkPeriodData = new ArrayList();
		this.getListVuPlaceDailyWorkPeriodData(Arrays.copyOfRange(bytes, start, start+=Sizes.VUPLACEDAILYWORKPERIODRECORD.getSize()*this.noOfPlaceRecords));
		//System.out.println(this.vuPlaceDailyWorkPeriodData.toString());
		
		this.noOfSpecificConditionRecords=Number.getShort_16(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFSPECIFICCONDITIONSRECORDS.getSize()));
		this.vuSpecificConditionData = new ArrayList();
		this.getListVuSpecificConditionData(Arrays.copyOfRange(bytes, start, start+=Sizes.SPECIFICCONDITIONRECORD.getSize()*this.noOfSpecificConditionRecords));
		//System.out.println(this.vuSpecificConditionData);
		
		this.signature=OctetString.getHexString(Arrays.copyOfRange(bytes, start, start+=Sizes.SIGNATURE_TREP2.getSize()));
		this.size=start;
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
		int end=bytes.length/Sizes.VUPLACEDAILYWORKPERIODRECORD.getSize();
		int start=0;
		for(int i=0; i<end;i++){
			vpdwpr=new VuPlaceDailyWorkPeriodRecord(Arrays.copyOfRange(bytes, start, start+=Sizes.VUPLACEDAILYWORKPERIODRECORD.getSize()));
			this.vuPlaceDailyWorkPeriodData.add(vpdwpr);
		}
		
	}

	private void getListVuActivityDailyData(byte[] bytes) {
		ActivityChangeInfo aci;
		int end=bytes.length/Sizes.ACTIVITYCHANGEINFO.getSize();
		int start=0;
		for(int i=0; i<end;i++){
			aci=new ActivityChangeInfo(Arrays.copyOfRange(bytes, start, start+=Sizes.ACTIVITYCHANGEINFO.getSize()));
			this.vuActivityDailyData.add(aci);
		}
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
