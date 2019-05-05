/**
 * 
 */
package com.tachographStructure.file.vuBlocks.subBlocks;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import com.tachographStructure.file.vuBlocks.VuSizes;
import com.tachographStructure.helpers.IA5String;
import com.tachographStructure.helpers.RealTime;
import com.tachographStructure.file.driverCardBlock.subBlocks.ExtendedSerialNumber;
import com.tachographStructure.file.driverCardBlock.subBlocks.Name;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 
 * 2.135.   VuIdentification
 * 
 * Información almacenada en una unidad intravehicular y relativa a la identificación de dicha unidad intravehicular (requisito 075).
 * VuIdentification ::= SEQUENCE {
 * vuManufacturerName VuManufacturerName,
 * vuManufacturerAddress VuManufacturerAddress,
 * vuPartNumber VuPartNumber,
 * vuSerialNumber VuSerialNumber,
 * vuSoftwareIdentification VuSoftwareIdentification,
 * vuManufacturingDate VuManufacturingDate,
 * vuApprovalNumber VuApprovalNumber
 * }
 * vuManufacturerName es el nombre del fabricante de la unidad intravehicular.
 * vuManufacturerAddress es la dirección del fabricante de la unidad intravehicular.
 * vuPartNumber es el número de pieza de la unidad intravehicular.
 * vuSerialNumber es el número de serie de la unidad intravehicular.
 * vuSoftwareIdentification identifica el software instalado en la unidad intravehicular.
 * vuManufacturingDate es la fecha de fabricación de la unidad intravehicular.
 * vuApprovalNumber es el número de homologación de la unidad intravehicular.
 *
 */
public class VuIdentification {
	
	/**
	 * 2.137.   VuManufacturerName
	 * Nombre del fabricante de la unidad intravehicular.
	 * VuManufacturerName ::= Name
	 * Asignación de valor: sin especificar.
	 */
	private String vuManufacturerName;
	/**
	 * 2.136.   VuManufacturerAddress
	 * Dirección del fabricante de la unidad intravehicular.
	 * VuManufacturerAddress ::= Address
	 * Asignación de valor: sin especificar.
	 */
	private String VuManufacturerAddress;
	/**
	 * 2.142.   VuPartNumber
	 * Número de pieza de la unidad intravehicular.
	 * VuPartNumber ::= IA5String(SIZE(16))
	 * Asignación de valor: específica del fabricante de la VU.
	 */
	private  String VuPartNumber;
	/**
	 * 2.147.   VuSerialNumber
	 * Número de serie de la unidad intravehicular (requisito 075).
	 * VuSerialNumber ::= ExtendedSerialNumber
	 */
	private ExtendedSerialNumber VuSerialNumber;
	/**
	 * 2.149.   VuSoftwareIdentification
	 * Información almacenada en una unidad intravehicular y relativa al software instalado.
	 * VuSoftwareIdentification ::= SEQUENCE {
	 * vuSoftwareVersion VuSoftwareVersion,
	 * vuSoftInstallationDate VuSoftInstallationDate
	 * }
	 * vuSoftwareVersion es el número de la versión de software que lleva instalado la unidad intravehicular.
	 * vuSoftInstallationDate es la fecha de instalación de la versión de software.
	 */
	private VuSoftwareIdentification VuSoftwareIdentification;
	/**
	 * 
	 */
	private Date VuManufacturingDate;
	/**
	 * 2.116.   VuApprovalNumber
	 * Número de homologación de la unidad intravehicular.
	 * VuApprovalNumber ::= IA5String(SIZE(8))
	 * Asignación de valor: sin especificar.
	 */
	private String vuApprovalNumber;
	

	/**
	 * @throws IOException 
	 * 
	 */
	public VuIdentification(byte[] bytes) throws IOException {
		int start=0;
		Name n=new Name(Arrays.copyOfRange(bytes, start, start+=VuSizes.VUMANUFACTURERNAME.getSize()));
		this.vuManufacturerName=n.getName();
		n=new Name(Arrays.copyOfRange(bytes, start, start+=VuSizes.VUMANUFACTURERADDRESS.getSize()));
		this.VuManufacturerAddress=n.getName();
		IA5String ia5s=new IA5String(Arrays.copyOfRange(bytes, start, start+=VuSizes.VUPARTNUMBER.getSize()));
		this.VuPartNumber=ia5s.getiA5String();
		this.VuSerialNumber=new ExtendedSerialNumber(Arrays.copyOfRange(bytes, start, start+=VuSizes.VUSERIALNUMBER.getSize()));
		this.VuSoftwareIdentification=new VuSoftwareIdentification(Arrays.copyOfRange(bytes, start, start+=VuSizes.VUSOFTWAREIDENTIFICATION.getSize()));
		this.VuManufacturingDate=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=VuSizes.VUMANUFATURINGDATE.getSize()));
		ia5s=new IA5String(Arrays.copyOfRange(bytes, start, start+=VuSizes.VUAPPROVALNUMBER.getSize()));
		this.vuApprovalNumber=ia5s.getiA5String();
		
	}


	/**
	 * @return the vuManufacturerName
	 */
	public String getVuManufacturerName() {
		return vuManufacturerName;
	}


	/**
	 * @param vuManufacturerName the vuManufacturerName to set
	 */
	public void setVuManufacturerName(String vuManufacturerName) {
		this.vuManufacturerName = vuManufacturerName;
	}


	/**
	 * @return the vuManufacturerAddress
	 */
	public String getVuManufacturerAddress() {
		return VuManufacturerAddress;
	}


	/**
	 * @param vuManufacturerAddress the vuManufacturerAddress to set
	 */
	public void setVuManufacturerAddress(String vuManufacturerAddress) {
		VuManufacturerAddress = vuManufacturerAddress;
	}


	/**
	 * @return the vuPartNumber
	 */
	public String getVuPartNumber() {
		return VuPartNumber;
	}


	/**
	 * @param vuPartNumber the vuPartNumber to set
	 */
	public void setVuPartNumber(String vuPartNumber) {
		VuPartNumber = vuPartNumber;
	}


	/**
	 * @return the vuSerialNumber
	 */
	public ExtendedSerialNumber getVuSerialNumber() {
		return VuSerialNumber;
	}


	/**
	 * @param vuSerialNumber the vuSerialNumber to set
	 */
	public void setVuSerialNumber(ExtendedSerialNumber vuSerialNumber) {
		VuSerialNumber = vuSerialNumber;
	}


	/**
	 * @return the vuSoftwareIdentification
	 */
	public VuSoftwareIdentification getVuSoftwareIdentification() {
		return VuSoftwareIdentification;
	}


	/**
	 * @param vuSoftwareIdentification the vuSoftwareIdentification to set
	 */
	public void setVuSoftwareIdentification(VuSoftwareIdentification vuSoftwareIdentification) {
		VuSoftwareIdentification = vuSoftwareIdentification;
	}


	/**
	 * @return the vuManufacturingDate
	 */
	public Date getVuManufacturingDate() {
		return VuManufacturingDate;
	}


	/**
	 * @param vuManufacturingDate the vuManufacturingDate to set
	 */
	public void setVuManufacturingDate(Date vuManufacturingDate) {
		VuManufacturingDate = vuManufacturingDate;
	}


	/**
	 * @return the vuApprovalNumber
	 */
	public String getVuApprovalNumber() {
		return vuApprovalNumber;
	}


	/**
	 * @param vuApprovalNumber the vuApprovalNumber to set
	 */
	public void setVuApprovalNumber(String vuApprovalNumber) {
		this.vuApprovalNumber = vuApprovalNumber;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuIdentification [\n"
				+ "vuManufacturerName=" + vuManufacturerName 
				+ "\n, VuManufacturerAddress="	+ VuManufacturerAddress 
				+ "\n, VuPartNumber=" + VuPartNumber 
				+ "\n, VuSerialNumber=" + VuSerialNumber
				+ ", VuSoftwareIdentification=" + VuSoftwareIdentification 
				+ "\n, VuManufacturingDate="+ VuManufacturingDate 
				+ "\n, vuApprovalNumber=" + vuApprovalNumber + "\n]";
	}
	

}
