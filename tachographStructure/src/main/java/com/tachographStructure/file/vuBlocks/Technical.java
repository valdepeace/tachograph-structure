/**
 * 
 */
package com.tachographStructure.file.vuBlocks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import com.tachographStructure.file.Block;
import com.tachographStructure.file.certificate.Signature;
import com.tachographStructure.file.vuBlocks.subBlocks.SensorPaired;
import com.tachographStructure.file.vuBlocks.subBlocks.VuCalibrationRecord;
import com.tachographStructure.file.vuBlocks.subBlocks.VuIdentification;
import com.tachographStructure.helpers.Number;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 
 *
 */
public class Technical extends Block {

	private VuIdentification vuIdentification;
	private SensorPaired sensosrPaired;
	/**
	 * 2.117.   VuCalibrationData
	 * Información almacenada en una unidad intravehicular y relativa a los calibrados del aparato de control (requisito 098).
	 * VuCalibrationData ::= SEQUENCE {
	 * noOfVuCalibrationRecords INTEGER(0..255),
	 * vuCalibrationRecords SET SIZE(noOfVuCalibrationRecords) OF VuCalibrationRecord
	 * }
	 * noOfVuCalibrationRecords es el número de registros que hay en el conjunto vuCalibrationRecords.
	 * vuCalibrationRecords es el conjunto de registros de calibrado.
	 */
	private ArrayList<VuCalibrationRecord> vuCalibrationData;
	/**
	 * vuCalibrationRecords es el conjunto de registros de calibrado.
	 */
	private int noOfVuCalibrationRecords;
	
	private Signature signature;
	private int size;
	
	public Technical(byte[] bytes) throws IOException{
		int start=0;
		this.vuIdentification=new VuIdentification(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUIDENTIFICATION.getSize()));
		
	
		this.sensosrPaired=new SensorPaired(Arrays.copyOfRange(bytes, start, start+= VuSizes.SENSORPAIRED.getSize()));
		
		
		this.noOfVuCalibrationRecords= Number.getNumber(Arrays.copyOfRange(bytes, start, start+= VuSizes.NOOFVUCALIBRATIONRECORDS.getSize()));
		
		this.setListVuCalibrationData(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUCALIBRATIONRECORD.getSize()*this.noOfVuCalibrationRecords));

		this.signature = new Signature(Arrays.copyOfRange(bytes, start, start += VuSizes.SIGNATURE_TREP5.getSize()));

		this.size=start;
	}

	private void setListVuCalibrationData(byte[] bytes) throws UnsupportedEncodingException {		
		this.vuCalibrationData=new ArrayList();
		int end=bytes.length/ VuSizes.VUCALIBRATIONRECORD.getSize();
		int start=0;
		VuCalibrationRecord vcr;
		for (int i=0;i<end;i++){
			vcr=new VuCalibrationRecord(Arrays.copyOfRange(bytes, start, start+= VuSizes.VUCALIBRATIONRECORD.getSize()));
			this.vuCalibrationData.add(vcr);
		}
		
	}

	/**
	 * @return the vuIdentification
	 */
	public VuIdentification getVuIdentification() {
		return vuIdentification;
	}

	/**
	 * @param vuIdentification the vuIdentification to set
	 */
	public void setVuIdentification(VuIdentification vuIdentification) {
		this.vuIdentification = vuIdentification;
	}

	/**
	 * @return the sensosrPaired
	 */
	public SensorPaired getSensosrPaired() {
		return sensosrPaired;
	}

	/**
	 * @param sensosrPaired the sensosrPaired to set
	 */
	public void setSensosrPaired(SensorPaired sensosrPaired) {
		this.sensosrPaired = sensosrPaired;
	}

	/**
	 * @return the vuCalibrationData
	 */
	public ArrayList<VuCalibrationRecord> getVuCalibrationData() {
		return vuCalibrationData;
	}

	/**
	 * @param vuCalibrationData the vuCalibrationData to set
	 */
	public void setVuCalibrationData(ArrayList<VuCalibrationRecord> vuCalibrationData) {
		this.vuCalibrationData = vuCalibrationData;
	}

	/**
	 * @return the noOfVuCalibrationRecords
	 */
	public int getNoOfVuCalibrationRecords() {
		return noOfVuCalibrationRecords;
	}

	/**
	 * @param noOfVuCalibrationRecords the noOfVuCalibrationRecords to set
	 */
	public void setNoOfVuCalibrationRecords(int noOfVuCalibrationRecords) {
		this.noOfVuCalibrationRecords = noOfVuCalibrationRecords;
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
		return "Technical [vuIdentification=" + vuIdentification + ", sensosrPaired=" + sensosrPaired
				+ ", vuCalibrationData=" + vuCalibrationData + ", noOfVuCalibrationRecords=" + noOfVuCalibrationRecords
				+ ", signature=" + signature + ", size=" + size + "]";
	}
	
}
