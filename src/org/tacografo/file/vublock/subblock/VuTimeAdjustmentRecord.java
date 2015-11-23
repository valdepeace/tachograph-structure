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
 * 2.153.   VuTimeAdjustmentRecord
 * Información almacenada en una unidad intravehicular y relativa a un ajuste de la hora efectuado fuera del marco de un calibrado regular (requisito 101).
 * VuTimeAdjustmentRecord ::= SEQUENCE {
 * oldTimeValue TimeReal,
 * newTimeValue TimeReal,
 * workshopName Name,
 * workshopAddress Address,
 * workshopCardNumber FullCardNumber
 * }
 * oldTimeValue, newTimeValue son el valor anterior y el nuevo valor de la fecha y la hora.
 * workshopName, workshopAddress son el nombre y la dirección del centro de ensayo.
 * workshopCardNumber identifica la tarjeta del centro de ensayo empleada para realizar el ajuste de la hora.
 */
public class VuTimeAdjustmentRecord {
	
	private Date oldTimeValue;
	private Date newTimeValue;
	private String workshopName;
	private String workshopAddress;
	private FullCardNumber workshopCardNumber;
	
	public VuTimeAdjustmentRecord(byte[] bytes) throws UnsupportedEncodingException {
		int start=0;
		this.oldTimeValue=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.OLDTIMEVALUE_VUTIMEADJUSTMENTDATA.getSize()));
		this.newTimeValue=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.NEWTIMEVALUE_VUTIMEADJUSTMENTDATA.getSize()));
		Name n=new Name(Arrays.copyOfRange(bytes, start, start+=Sizes.WORKSHOPNAME_VUTIMEADJUSTMENTADATA.getSize()));
		this.workshopName=n.getName();
		n=new Name(Arrays.copyOfRange(bytes, start, start+=Sizes.WORKSHOPADDRESS_VUTIMEADJUSTMENTADATA.getSize()));
		this.workshopAddress=n.getName();
		this.workshopCardNumber=new FullCardNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.FULLCARDNUMBER.getSize()));
	}

	/**
	 * @return the oldTimeValue
	 */
	public Date getOldTimeValue() {
		return oldTimeValue;
	}

	/**
	 * @param oldTimeValue the oldTimeValue to set
	 */
	public void setOldTimeValue(Date oldTimeValue) {
		this.oldTimeValue = oldTimeValue;
	}

	/**
	 * @return the newTimeValue
	 */
	public Date getNewTimeValue() {
		return newTimeValue;
	}

	/**
	 * @param newTimeValue the newTimeValue to set
	 */
	public void setNewTimeValue(Date newTimeValue) {
		this.newTimeValue = newTimeValue;
	}

	/**
	 * @return the workshopName
	 */
	public String getWorkshopName() {
		return workshopName;
	}

	/**
	 * @param workshopName the workshopName to set
	 */
	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	/**
	 * @return the workshopAddress
	 */
	public String getWorkshopAddress() {
		return workshopAddress;
	}

	/**
	 * @param workshopAddress the workshopAddress to set
	 */
	public void setWorkshopAddress(String workshopAddress) {
		this.workshopAddress = workshopAddress;
	}

	/**
	 * @return the workshopCardNumber
	 */
	public FullCardNumber getWorkshopCardNumber() {
		return workshopCardNumber;
	}

	/**
	 * @param workshopCardNumber the workshopCardNumber to set
	 */
	public void setWorkshopCardNumber(FullCardNumber workshopCardNumber) {
		this.workshopCardNumber = workshopCardNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VuTimeAdjustmentRecord [oldTimeValue=" + oldTimeValue + ", newTimeValue=" + newTimeValue
				+ ", workshopName=" + workshopName + ", workshopAddress=" + workshopAddress + ", workshopCardNumber="
				+ workshopCardNumber + "]";
	}
	

}
