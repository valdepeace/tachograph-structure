package com.tachographStructure.file.vuBlocks.subBlocks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import com.tachographStructure.file.vuBlocks.VuSizes;
import com.tachographStructure.helpers.IA5String;
import com.tachographStructure.helpers.RealTime;

/**
 * 
 * @author Andres Carmona Gil
 * 
 * 2.149.   VuSoftwareIdentification
 * 
 * Información almacenada en una unidad intravehicular y relativa al software instalado.
 * VuSoftwareIdentification ::= SEQUENCE {
 * vuSoftwareVersion VuSoftwareVersion,
 * vuSoftInstallationDate VuSoftInstallationDate
 * }
 *
 */
public class VuSoftwareIdentification {
	
	/**
	 * 2.150.   VuSoftwareVersion
	 * Número de la versión de software que lleva instalado la unidad intravehicular.
	 * VuSoftwareVersion ::= IA5String(SIZE(4))
	 * Asignación de valor: sin especificar.
	 */
	private String vuSoftwareVersion;
	/**
	 * 2.148.   VuSoftInstallationDate
	 * Fecha de instalación de la versión de software que lleva instalada la unidad intravehicular.
	 * VuSoftInstallationDate ::= TimeReal
	 * Asignación de valor: sin especificar.
	 */
	private Date vuSoftInstallationDate;
	
	public VuSoftwareIdentification(byte[] bytes) throws UnsupportedEncodingException{
		int start=0;
		IA5String ia5s=new IA5String(Arrays.copyOfRange(bytes,start, start+=VuSizes.VUSOFTWAREVERSION.getSize()));
		this.vuSoftwareVersion=ia5s.getiA5String();
		this.vuSoftInstallationDate=RealTime.getRealTime(Arrays.copyOfRange(bytes,start, start+=VuSizes.VUSOFTWAREINSTALLATIONDATE.getSize()));
	}

	/**
	 * @return the vuSoftwareVersion
	 */
	public String getVuSoftwareVersion() {
		return vuSoftwareVersion;
	}

	/**
	 * @param vuSoftwareVersion the vuSoftwareVersion to set
	 */
	public void setVuSoftwareVersion(String vuSoftwareVersion) {
		this.vuSoftwareVersion = vuSoftwareVersion;
	}

	/**
	 * @return the vuSoftInstallationDate
	 */
	public Date getVuSoftInstallationDate() {
		return vuSoftInstallationDate;
	}

	/**
	 * @param vuSoftInstallationDate the vuSoftInstallationDate to set
	 */
	public void setVuSoftInstallationDate(Date vuSoftInstallationDate) {
		this.vuSoftInstallationDate = vuSoftInstallationDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuSoftwareIdentification [vuSoftwareVersion=" + vuSoftwareVersion + ", vuSoftInstallationDate="
				+ vuSoftInstallationDate + "]";
	}

	
}
