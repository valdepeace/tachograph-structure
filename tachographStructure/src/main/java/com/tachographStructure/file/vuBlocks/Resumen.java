package com.tachographStructure.file.vuBlocks;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import com.tachographStructure.file.Block;
import com.tachographStructure.file.certificate.Signature;
import com.tachographStructure.file.driverCardBlock.subBlocks.Certificate;
import com.tachographStructure.file.driverCardBlock.subBlocks.FullCardNumber;
import com.tachographStructure.file.driverCardBlock.subBlocks.Name;
import com.tachographStructure.file.driverCardBlock.subBlocks.VehicleRegistrationIdentification;
import com.tachographStructure.file.vuBlocks.subBlocks.VuCompanyLocksRecord;
import com.tachographStructure.file.vuBlocks.subBlocks.VuControlActivityRecord;
import com.tachographStructure.helpers.IA5String;
import com.tachographStructure.helpers.Number;
import com.tachographStructure.helpers.OctetString;
import com.tachographStructure.helpers.RealTime;

public class Resumen extends Block{

	// 2.68. MemberStateCertificate
	// El certificado de la clave p�blica de un Estado miembro, expedido por la autoridad de certificaci�n europea.
	// MemberStateCertificate::= Certificate
	
	private Certificate memberStateCertificate;
	
	// 2.121. VuCertificate
	// Certificado de la clave p�blica de una unidad intravehicular.
	// VuCertificate::= Certificate
	
	private Certificate VUcertificate;
	
	// 2.112. VehicleIdentificationNumber
	// N�mero de identificaci�n del veh�culo (VIN) referido al veh�culo completo, generalmente el n�mero de serie del chasis o el n�mero de bastidor.
	// VehicleIdentificationNumber::= IA5String(SIZE(17))
	// Asignaci�n de valor: tal y como se define en la norma ISO 3779.
	
	private String vehicleIdentificationNumber;
	
	private VehicleRegistrationIdentification vehicleRegistrationIdentification;
	
	// 2.45. CurrentDateTime
	// La fecha y la hora actuales del aparato de control.
	// CurrentDateTime::= TimeReal
	// Asignaci�n de valor: no hay m�s especificaciones.
	
	private Date currentDateTime;
	
	// 2.129. VuDownloadablePeriod
	// La fecha m�s antigua y la m�s reciente para las que una unidad intravehicular conserva datos relativos a las actividades de los conductores (requisitos 081, 084 o 087).
	// VuDownloadablePeriod::= SEQUENCE {
	// minDownloadableTime TimeReal
	// maxDownloadableTime TimeReal
	// }
	// minDownloadableTime es la fecha y la hora m�s antiguas en que se insert� una tarjeta, ocurri� un cambio de actividad o se introdujo un lugar; seg�n los datos almacenados en la VU.
	// maxDownloadableTime es la fecha y la hora m�s recientes en que se insert� una tarjeta, ocurri� un cambio de actividad o se introdujo un lugar; seg�n los datos almacenados en la VU.
	private Date minDownloableTime;
	private Date maxDonloadbleTime;
	
	// 2.28. CardSlotsStatus
	// C�digo que indica el tipo de tarjetas insertadas en las dos ranuras de la unidad intravehicular.
	// CardSlotsStatus::= OCTET STRING (SIZE(1))
	// Asignaci�n de valor - Alineaci�n de octeto: 'ccccdddd'B:
	// 'cccc'B Identificaci�n del tipo de tarjeta insertada en la ranura del segundo conductor,
	// 'dddd'B Identificaci�n del tipo de tarjeta insertada en la ranura del conductor,
	// con los siguientes c�digos de identificaci�n:
	// '0000'B no hay tarjeta insertada,
	// '0001'B se ha insertado una tarjeta de conductor,
	// '0010'B se ha insertado una tarjeta del centro de ensayo,
	// '0011'B se ha insertado una tarjeta de control,
	// '0100'B se ha insertado una tarjeta de empresa.
	
	private byte[] cardSlotsStatus;
	
	// 2.130. VuDownloadActivityData
	// Informaci�n almacenada en una unidad intravehicular y relativa a la �ltima transferencia de sus datos (requisito 105).
	// VuDownloadActivityData::= SEQUENCE {
	// downloadingTime TimeReal,
	// fullCardNumber FullCardNumber,
	// companyOrWorkshopName Name
	// }
	// downloadingTime es la fecha y la hora de la transferencia.
	// fullCardNumber identifica la tarjeta empleada para autorizar la transferencia.
	// companyOrWorkshopName es el nombre de la empresa o del centro de ensayo.
	
	private Date downloadingTime;
	private FullCardNumber fullcardNumber;
	private Name companyOrWorkshopName;
	
	
	// 2.122. VuCompanyLocksData
	// Informaci�n almacenada en una unidad intravehicular y relativa a bloqueos introducidos por empresas (requisito 104).
	// VuCompanyLocksData::= SEQUENCE {
	// noOfLocks INTEGER(0..20),
	// vuCompanyLocksRecords SET SIZE(noOfLocks) OF VuCompanyLocksRecord
	// }
	// noOfLocks es el n�mero de bloqueos incluidos en el conjunto vuCompanyLocksRecords.
	// vuCompanyLocksRecords es el conjunto de registros de bloqueos introducidos por empresas.
	private int noOfLocks;
	private ArrayList<VuCompanyLocksRecord> vuCompanyLocksRecords;
	
	// 2.124. VuControlActivityData
	// Informacion almacenada en una unidad intravehicular y relativa a los controles efectuados con dicha VU (requisito 102).
	// VuControlActivityData::= SEQUENCE {
	// noOfControls INTEGER(0..20),
	// vuControlActivityRecords SET SIZE(noOfControls) OF VuControlActivityRecord
	// }
	// noOfControls es el n�mero de controles incluidos en el conjunto vuControlActivityRecords.
	// vuControlActivityRecords es el conjunto de registros sobre actividades de control.
	private int noOfControls;
	private ArrayList<VuControlActivityRecord> vuControlActivityRecord;
	
	// 2.101. Signature
	// Una firma digital.
	// Signature::= OCTET STRING (SIZE(128))
	// Asignaci�n de valor: con arreglo a lo dispuesto en el Ap�ndice 11 (Mecanismos de seguridad comunes).
	private Signature signature;
	
	private int size;
	
	public Resumen(byte[] bytes) throws Exception{
		
		int start=0; // start in 2 by one byte to id and one byte trep

		this.memberStateCertificate = new Certificate(Arrays.copyOfRange(bytes, start, start+= VuSizes.MEMBERSTATECERTIFICATE.getSize()));

		this.VUcertificate =  new Certificate(Arrays.copyOfRange(bytes, start, start+= VuSizes.MEMBERSTATECERTIFICATE.getSize()));
		
		IA5String ia5=new IA5String(Arrays.copyOfRange(bytes, start, start+= VuSizes.VEHICLEIDENTIFICATIONNUMBER.getSize()));
		this.vehicleIdentificationNumber= ia5.getiA5String();
		
		this.vehicleRegistrationIdentification=new VehicleRegistrationIdentification(Arrays.copyOfRange(bytes, start, start+= VuSizes.VEHICLEREGISTRATIONIDENTIFICATION_TREP1.getSize()));
		
		this.currentDateTime= RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+= VuSizes.CURRENTDATETIME.getSize()));
		
		this.minDownloableTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+= VuSizes.MINDOWNLOADLETIME.getSize()));
		
		this.maxDonloadbleTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+= VuSizes.MAXDOWNLOADLETIME.getSize()));
		//tengo que mirar los bytes
		this.cardSlotsStatus=Arrays.copyOfRange(bytes, start, start+= VuSizes.CARDSLOTSSTATUS.getSize());
		
		this.downloadingTime= RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+= VuSizes.DOWNLOADINGTIME.getSize()));
		this.fullcardNumber=new FullCardNumber(Arrays.copyOfRange(bytes, start, start+= VuSizes.FULLCARDNUMBER.getSize()));
		
		this.companyOrWorkshopName= new Name(Arrays.copyOfRange(bytes, start, start+= VuSizes.COMPANYORWORKSHOPNAME.getSize()));
		this.noOfLocks= Number.getShort_8(Arrays.copyOfRange(bytes, start, start+= VuSizes.NOOFLOCKS.getSize()));
		if(this.noOfLocks>0){
			this.vuCompanyLocksRecords=new ArrayList<VuCompanyLocksRecord>();
			this.getListVuCompanyLocksRecords(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUCOMPANYLOCKSRECORD.getSize()*this.noOfLocks));
		}else{
			this.vuCompanyLocksRecords=null;
		}
		 this.noOfControls=Number.getShort_8(Arrays.copyOfRange(bytes, start, start+= VuSizes.NOOFCONTROLS.getSize()));
		 if(this.noOfControls>0){
			 this.vuControlActivityRecord=new ArrayList();
			 this.getListVuControlActivityRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUCONTROLACTIVITYRECORD.getSize()*this.noOfControls));
		 }else{
			 this.vuControlActivityRecord=null;
		 }
		 
		 this.signature = new Signature(Arrays.copyOfRange(bytes, start, start += VuSizes.SIGNATURE_TREP1.getSize()));
		this.size=start;		
	}

	private void getListVuControlActivityRecord(byte[] bytes) throws UnsupportedEncodingException {
		VuControlActivityRecord vcar=null;
		int end=bytes.length/ VuSizes.VUCONTROLACTIVITYRECORD.getSize();
		int start=0;
				for (int i=0; i<this.noOfControls;i++){
					vcar=new VuControlActivityRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUCONTROLACTIVITYRECORD.getSize()));
					this.vuControlActivityRecord.add(vcar);
				}
		
	}

	private void getListVuCompanyLocksRecords(byte[] bytes) throws UnsupportedEncodingException {
				
		VuCompanyLocksRecord vclr=null;		
		int start=0;
		for(int i=0; i<this.noOfLocks; i++){
			vclr=new VuCompanyLocksRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUCOMPANYLOCKSRECORD.getSize()));
			this.vuCompanyLocksRecords.add(vclr);
		}
		
	}

	
	/**
	 * @return the memberStateCertificate
	 */
	public Certificate getMemberStateCertificate() {
		return memberStateCertificate;
	}

	/**
	 * @param memberStateCertificate the memberStateCertificate to set
	 */
	public void setMemberStateCertificate(Certificate memberStateCertificate) {
		this.memberStateCertificate = memberStateCertificate;
	}

	/**
	 * @return the vUcertificate
	 */
	public Certificate getVUcertificate() {
		return VUcertificate;
	}

	/**
	 * @param vUcertificate the vUcertificate to set
	 */
	public void setVUcertificate(Certificate vUcertificate) {
		VUcertificate = vUcertificate;
	}

	/**
	 * @return the vehicleIdentificationNumber
	 */
	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	/**
	 * @param vehicleIdentificationNumber the vehicleIdentificationNumber to set
	 */
	public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	/**
	 * @return the vehicleRegistrationIdentification
	 */
	public VehicleRegistrationIdentification getVehicleRegistrationIdentification() {
		return vehicleRegistrationIdentification;
	}

	/**
	 * @param vehicleRegistrationIdentification the vehicleRegistrationIdentification to set
	 */
	public void setVehicleRegistrationIdentification(VehicleRegistrationIdentification vehicleRegistrationIdentification) {
		this.vehicleRegistrationIdentification = vehicleRegistrationIdentification;
	}

	/**
	 * @return the currentDateTime
	 */
	public Date getCurrentDateTime() {
		return currentDateTime;
	}

	/**
	 * @param currentDateTime the currentDateTime to set
	 */
	public void setCurrentDateTime(Date currentDateTime) {
		this.currentDateTime = currentDateTime;
	}

	/**
	 * @return the minDownloableTime
	 */
	public Date getMinDownloableTime() {
		return minDownloableTime;
	}

	/**
	 * @param minDownloableTime the minDownloableTime to set
	 */
	public void setMinDownloableTime(Date minDownloableTime) {
		this.minDownloableTime = minDownloableTime;
	}

	/**
	 * @return the maxDonloadbleTime
	 */
	public Date getMaxDonloadbleTime() {
		return maxDonloadbleTime;
	}

	/**
	 * @param maxDonloadbleTime the maxDonloadbleTime to set
	 */
	public void setMaxDonloadbleTime(Date maxDonloadbleTime) {
		this.maxDonloadbleTime = maxDonloadbleTime;
	}

	/**
	 * @return the cardSlotsStatus
	 */
	public byte[] getCardSlotsStatus() {
		return cardSlotsStatus;
	}

	/**
	 * @param cardSlotsStatus the cardSlotsStatus to set
	 */
	public void setCardSlotsStatus(byte[] cardSlotsStatus) {
		this.cardSlotsStatus = cardSlotsStatus;
	}

	/**
	 * @return the downloadingTime
	 */
	public Date getDownloadingTime() {
		return downloadingTime;
	}

	/**
	 * @param downloadingTime the downloadingTime to set
	 */
	public void setDownloadingTime(Date downloadingTime) {
		this.downloadingTime = downloadingTime;
	}

	/**
	 * @return the fullcardNumber
	 */
	public FullCardNumber getFullcardNumber() {
		return fullcardNumber;
	}

	/**
	 * @param fullcardNumber the fullcardNumber to set
	 */
	public void setFullcardNumber(FullCardNumber fullcardNumber) {
		this.fullcardNumber = fullcardNumber;
	}

	/**
	 * @return the companyOrWorkshopName
	 */
	public Name getCompanyOrWorkshopName() {
		return companyOrWorkshopName;
	}

	/**
	 * @param companyOrWorkshopName the companyOrWorkshopName to set
	 */
	public void setCompanyOrWorkshopName(Name companyOrWorkshopName) {
		this.companyOrWorkshopName = companyOrWorkshopName;
	}

	/**
	 * @return the noOfLocks
	 */
	public int getNoOfLocks() {
		return noOfLocks;
	}

	/**
	 * @param noOfLocks the noOfLocks to set
	 */
	public void setNoOfLocks(int noOfLocks) {
		this.noOfLocks = noOfLocks;
	}

	/**
	 * @return the vuCompanyLocksRecords
	 */
	public ArrayList<VuCompanyLocksRecord> getVuCompanyLocksRecords() {
		return vuCompanyLocksRecords;
	}

	/**
	 * @param vuCompanyLocksRecords the vuCompanyLocksRecords to set
	 */
	public void setVuCompanyLocksRecords(ArrayList<VuCompanyLocksRecord> vuCompanyLocksRecords) {
		this.vuCompanyLocksRecords = vuCompanyLocksRecords;
	}

	/**
	 * @return the noOfControls
	 */
	public int getNoOfControls() {
		return noOfControls;
	}

	/**
	 * @param noOfControls the noOfControls to set
	 */
	public void setNoOfControls(int noOfControls) {
		this.noOfControls = noOfControls;
	}

	/**
	 * @return the vuControlActivityRecord
	 */
	public ArrayList<VuControlActivityRecord> getVuControlActivityRecord() {
		return vuControlActivityRecord;
	}

	/**
	 * @param vuControlActivityRecord the vuControlActivityRecord to set
	 */
	public void setVuControlActivityRecord(ArrayList<VuControlActivityRecord> vuControlActivityRecord) {
		this.vuControlActivityRecord = vuControlActivityRecord;
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
		return "Resumen [memberStateCertificate=" + memberStateCertificate.toString() + "\n, VUcertificate=" + VUcertificate.toString()
				+ "\n, vehicleIdentificationNumber=" + vehicleIdentificationNumber
				+ "\n, vehicleRegistrationIdentification=" + vehicleRegistrationIdentification + "\n, currentDateTime="
				+ currentDateTime + "\n, minDownloableTime=" + minDownloableTime + "\n, maxDonloadbleTime="
				+ maxDonloadbleTime + "\n, cardSlotsStatus=" + Arrays.toString(cardSlotsStatus) + "\n, downloadingTime="
				+ downloadingTime + "\n, fullcardNumber=" + fullcardNumber + "\n, companyOrWorkshopName="
				+ companyOrWorkshopName + "\n, noOfLocks=" + noOfLocks + "\n, vuCompanyLocksRecords="
				+ vuCompanyLocksRecords + "\n, noOfControls=" + noOfControls + "\n, vuControlActivityRecord="
				+ vuControlActivityRecord + "\n, signature=" + signature + "]";
	}
	
	
	
	
}
