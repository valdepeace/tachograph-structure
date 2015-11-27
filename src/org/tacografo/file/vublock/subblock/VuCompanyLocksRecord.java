/**
 * 
 */
package org.tacografo.file.vublock.subblock;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.FullCardNumber;
import org.tacografo.file.cardblockdriver.subblock.Name;
import org.tacografo.file.vublock.Sizes;
import org.tacografo.tiposdatos.RealTime;

/**
 * @author Andres Carmona Gil
 *
 */

/**
 * 
 * 2.123. VuCompanyLocksRecord
 *
 * Informaci�n almacenada en una unidad intravehicular y relativa a un bloqueo introducido por una empresa (requisito 104).
 *
 * VuCompanyLocksRecord::= SEQUENCE {
 * 
 * lockInTime TimeReal,
 * 
 * lockOutTime TimeReal,
 *
 * companyName Name,
 * 
 * companyAddress Address,
 * 
 * companyCardNumber FullCardNumber
 *
 * }
 *
 * lockInTime, lockOutTime son la fecha y la hora de activaci�n y desactivaci�n del bloqueo.
 * 
 * companyName, companyAddress son el nombre y la direcci�n de la empresa relacionada con la activaci�n del bloqueo.
 *
 * companyCardNumber identifica la tarjeta empleada para la activaci�n del bloqueo.
 *
 */
public class VuCompanyLocksRecord {
	
	
	private Date lockInTime;
	private Date lockOutTime;
	private String companyName;
	private String companyAddres;
	private FullCardNumber companyCardNumber;
	
	
	public VuCompanyLocksRecord(byte[] bytes) throws UnsupportedEncodingException{
		int start=0;
		this.lockInTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.LOCKINTITME.getSize()));
		this.lockOutTime=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.LOCKOUTTIME.getSize()));
		Name n=new Name(Arrays.copyOfRange(bytes, start, start+=Sizes.COMPANYNAME.getSize()));
		this.companyName=n.getName();
		n=new Name(Arrays.copyOfRange(bytes, start, start+=Sizes.COMPANYADDRESS.getSize()));
		this.companyAddres=n.getName();
		this.companyCardNumber=new FullCardNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.FULLCARDNUMBER.getSize()));
		
	}


	/**
	 * @return the lockInTime
	 */
	public Date getLockInTime() {
		return lockInTime;
	}


	/**
	 * @param lockInTime the lockInTime to set
	 */
	public void setLockInTime(Date lockInTime) {
		this.lockInTime = lockInTime;
	}


	/**
	 * @return the lockOutTime
	 */
	public Date getLockOutTime() {
		return lockOutTime;
	}


	/**
	 * @param lockOutTime the lockOutTime to set
	 */
	public void setLockOutTime(Date lockOutTime) {
		this.lockOutTime = lockOutTime;
	}


	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}


	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	/**
	 * @return the companyAddres
	 */
	public String getCompanyAddres() {
		return companyAddres;
	}


	/**
	 * @param companyAddres the companyAddres to set
	 */
	public void setCompanyAddres(String companyAddres) {
		this.companyAddres = companyAddres;
	}


	/**
	 * @return the companyCardNumber
	 */
	public FullCardNumber getCompanyCardNumber() {
		return companyCardNumber;
	}


	/**
	 * @param companyCardNumber the companyCardNumber to set
	 */
	public void setCompanyCardNumber(FullCardNumber companyCardNumber) {
		this.companyCardNumber = companyCardNumber;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuCompanyLocksRecord [lockInTime=" + lockInTime + ", lockOutTime=" + lockOutTime + ", companyName="
				+ companyName + ", companyAddres=" + companyAddres + ", companyCardNumber=" + companyCardNumber + "]";
	}

	
}
