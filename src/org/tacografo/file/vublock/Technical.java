/**
 * 
 */
package org.tacografo.file.vublock;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import org.tacografo.file.Block;
import org.tacografo.file.vublock.subblock.SensorPaired;
import org.tacografo.file.vublock.subblock.VuCalibrationRecord;
import org.tacografo.file.vublock.subblock.VuDetailedSpeedBlock;
import org.tacografo.file.vublock.subblock.VuIdentification;
import org.tacografo.tiposdatos.Number;
/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 
 *
 */
public class Technical extends Block{

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
	
	private String signature;
	private int size;
	
	public Technical(byte[] bytes) throws IOException{
		int start=0;
		this.vuIdentification=new VuIdentification(Arrays.copyOfRange(bytes, start, start+=Sizes.VUIDENTIFICATION.getSize()));
		
	
		this.sensosrPaired=new SensorPaired(Arrays.copyOfRange(bytes, start, start+=Sizes.SENSORPAIRED.getSize()));
		
		
		this.noOfVuCalibrationRecords=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFVUCALIBRATIONRECORDS.getSize()));
		
		this.setListVuCalibrationData(Arrays.copyOfRange(bytes, start, start+=Sizes.VUCALIBRATIONRECORD.getSize()*this.noOfVuCalibrationRecords));
	}

	private void setListVuCalibrationData(byte[] bytes) throws UnsupportedEncodingException {		
		this.vuCalibrationData=new ArrayList();
		int end=bytes.length/Sizes.VUCALIBRATIONRECORD.getSize();
		int start=0;
		VuCalibrationRecord vcr;
		for (int i=0;i<end;i++){
			vcr=new VuCalibrationRecord(Arrays.copyOfRange(bytes, start, start+=Sizes.VUCALIBRATIONRECORD.getSize()));
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
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
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
