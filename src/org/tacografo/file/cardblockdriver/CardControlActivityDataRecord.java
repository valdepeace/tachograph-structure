package org.tacografo.file.cardblockdriver;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Arrays;

import org.tacografo.file.cardblockdriver.subblock.ControlType;
import org.tacografo.file.cardblockdriver.subblock.FullCardNumber;
import org.tacografo.file.cardblockdriver.subblock.VehicleRegistrationIdentification;
import org.tacografo.tiposdatos.RealTime;


/**
 * 
 * 2.11. CardControlActivityDataRecord
 * Información almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa al último control
 * que ha pasado el conductor (requisitos 210 y 225).
 * CardControlActivityDataRecord ::= SEQUENCE {
 * controlType controlType,
 * controlTime TimeReal,
 * controlCardNumber FullCardNumber,
 * controlVehicleRegistration VehicleRegistrationIdentification,
 * controlDownloadPeriodBegin TimeReal,
 * controlDownloadPeriodEnd TimeReal,
 * }
 * 
 * controlType es el tipo de control.
 * 
 * controlTime es la fecha y la hora del control.
 * 
 * controlCardNumber es el FullCardNumber del funcionario que ha realizado el control.
 * 
 * controlVehicleRegistration es el VRN y el nombre del Estado miembro donde se matriculó el vehículo que ha sido
 * objeto del control.
 * 
 * controlDownloadPeriodBegin y controlDownloadPeriodEnd es el período transferido, en caso de transferencia.
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class CardControlActivityDataRecord extends CardBlockDriver implements
		CardBlock {

	
	private ControlType controlType;
	private Date controlTime;
	private FullCardNumber controlCardNumber; 
	private VehicleRegistrationIdentification controlVehicleRegistration;
	private Date controlDownloadPeriodBegin; 
	private Date controlDownloadPeriodEnd;
	
	
	public CardControlActivityDataRecord() {
		
	}
	
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardControlActivityDataRecord(byte[] datos) throws UnsupportedEncodingException{
		
		int start=0;
		this.controlType=new ControlType(Arrays.copyOfRange(datos, start, start+=Sizes.CONTROLTYPE.getMax()));		
		this.controlTime=RealTime.getRealTime(Arrays.copyOfRange(datos, start, start+=Sizes.CONTROLTIME.getMax()));
		this.controlCardNumber=new FullCardNumber(Arrays.copyOfRange(datos, start, start+=Sizes.CONTROLCARDNUMBER.getMax()));
		this.controlVehicleRegistration = new VehicleRegistrationIdentification(Arrays.copyOfRange(datos, start, start+=Sizes.CONTROLVEHICLEREGISTRATION.getMax()));
		this.controlDownloadPeriodBegin= RealTime.getRealTime(Arrays.copyOfRange(datos, start, start+=Sizes.CONTROLDOWNLOADPERIODBEGIN.getMax()));
		this.controlDownloadPeriodEnd= RealTime.getRealTime(Arrays.copyOfRange(datos, start, start+=Sizes.CONTROLDOWNLOADPERIODEND.getMax()));
		
	}
	/**
	 * Obtenemos el tipo de control.
	 * @return tipo de control
	 */
	public ControlType getControlType() {
		return controlType;
	}
	/**
	 * Asignamos el tipo de control.
	 * @param controlType
	 */
	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}
	/**
	 * Obtenemos la fecha de control
	 * @return fecha control
	 */
	public Date getControlTime() {
		return controlTime;
	}
	/**
	 * Asginamos la fecha de control
	 * @param controlTime
	 */
	public void setControlTime(Date controlTime) {
		this.controlTime = controlTime;
	}
	/**
	 * Obtenemos FullCardNumber del funcionario que a realizado el control.
	 * @return FullCardNumber
	 */
	public FullCardNumber getControlCardNumber() {
		return controlCardNumber;
	}
	/**
	 * Asignamos FullCardNumber del funcionario que a realizado el control.
	 * @param controlCardNumber
	 */
	public void setControlCardNumber(FullCardNumber controlCardNumber) {
		this.controlCardNumber = controlCardNumber;
	}
	/**
	 * Obetemos el VRN y el nombre del Estado miembro donde se matriculó el vehículo que ha sido
	 * objeto del control.
	 * @return VehicleRegistrationIdentification
	 */
	public VehicleRegistrationIdentification getControlVehicleRegistration() {
		return controlVehicleRegistration;
	}
	/**
	 * Asignamos el VRN y el nombre del Estado miembro donde se matriculó el vehículo que ha sido
	 * objeto del control.
	 * @param controlVehicleRegistration
	 */
	public void setControlVehicleRegistration(
			VehicleRegistrationIdentification controlVehicleRegistration) {
		this.controlVehicleRegistration = controlVehicleRegistration;
	}
	/**
	 * Obtemos el comienzo del periodo de transferencia, en caso de transferencia
	 * @return ControlDownloadPeriodBegin
	 */
	public Date getControlDownloadPeriodBegin() {
		return controlDownloadPeriodBegin;
	}
	/**
	 * Asignamos el comienzo del periodo de transferencia, en caso de transferencia
	 * @param controlDownloadPeriodBegin
	 */
	public void setControlDownloadPeriodBegin(Date controlDownloadPeriodBegin) {
		this.controlDownloadPeriodBegin = controlDownloadPeriodBegin;
	}
	/**
	 * Obtenemos el final del periodo de transferencia, en caso de transferencia
	 * @return ControlDownloadPeriodEnd
	 */
	public Date getControlDownloadPeriodEnd() {
		return controlDownloadPeriodEnd;
	}
	/**
	 * Asignamos el final del periodo de transferencia, en caso de transferencia
	 * @param controlDownloadPeriodEnd
	 */
	public void setControlDownloadPeriodEnd(Date controlDownloadPeriodEnd) {
		this.controlDownloadPeriodEnd = controlDownloadPeriodEnd;
	}

	@Override
	public String toString() {
		return "CardControlActivityDataRecord [controlType=" + controlType
				+ ", \ncontrolTime=" + controlTime + ", \ncontrolCardNumber="
				+ controlCardNumber + ", controlVehicleRegistration="
				+ controlVehicleRegistration + ", \ncontrolDownloadPeriodBegin="
				+ controlDownloadPeriodBegin + ", \ncontrolDownloadPeriodEnd="
				+ controlDownloadPeriodEnd + "]";
	}
	
}
