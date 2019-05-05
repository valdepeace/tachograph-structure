/**
 * 
 */
package com.tachographStructure.file.vuBlocks.subBlocks;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import com.tachographStructure.file.Block;
import com.tachographStructure.file.driverCardBlock.subBlocks.FullCardNumber;
import com.tachographStructure.file.driverCardBlock.subBlocks.Name;
import com.tachographStructure.file.driverCardBlock.subBlocks.VehicleRegistrationIdentification;
import com.tachographStructure.file.vuBlocks.VuSizes;
import com.tachographStructure.helpers.IA5String;
import com.tachographStructure.helpers.OdometerShort;
import com.tachographStructure.helpers.RealTime;
import com.tachographStructure.helpers.Number;

/**
 * @author Andres Carmona gil
 * 
 * 2.118.   VuCalibrationRecord
 * 
 * Información almacenada en una unidad intravehicular y relativa a un calibrado del aparato de control (requisito 098).
 * VuCalibrationRecord ::= SEQUENCE {
 * calibrationPurpose CalibrationPurpose,
 * workshopName Name,
 * workshopAddress Address,
 * workshopCardNumber FullCardNumber,
 * workshopCardExpiryDate TimeReal,
 * vehicleIdentificationNumber VehicleIdentificationNumber,
 * vehicleRegistrationIdentification VehicleRegistrationIdentification,
 * wVehicleCharacteristicConstant W-VehicleCharacteristicConstant,
 * kConstantOfRecordingEquipment K-ConstantOfRecordingEquipment,
 * lTyreCircumference L-TyreCircumference,
 * tyreSize TyreSize,
 * authorisedSpeed SpeedAuthorised,
 * oldOdometerValue OdometerShort,
 * newOdometerValue OdometerShort,
 * oldTimeValue TimeReal,
 * newTimeValue TimeReal,
 * nextCalibrationDate TimeReal
 * }
 * calibrationPurpose es el propósito del calibrado.
 * workshopName, workshopAddress son el nombre y la dirección del centro de ensayo.
 * workshopCardNumber identifica la tarjeta del centro de ensayo empleada durante el calibrado.
 * workshopCardExpiryDate es la fecha de caducidad de la tarjeta.
 * vehicleIdentificationNumber es el VIN.
 * vehicleRegistrationIdentification contiene el VRN y el nombre del Estado miembro donde se matriculó el vehículo.
 * wVehicleCharacteristicConstant es el coeficiente característico del vehículo.
 * kConstantOfRecordingEquipment es la constante del aparato de control.
 * lTyreCircumference es la circunferencia efectiva de los neumáticos de las ruedas.
 * tyreSize son las dimensiones de las ruedas montadas en el vehículo.
 * authorisedSpeed es la velocidad autorizada del vehículo.
 * oldOdometerValue, newOdometerValue son la lectura anterior y la nueva lectura del cuentakilómetros.
 * oldTimeValue, newTimeValue son el valor anterior y el nuevo valor de la fecha y la hora.
 * nextCalibrationDate es la fecha del próximo calibrado del tipo especificado en CalibrationPurpose, a cargo de una autoridad de inspección autorizada.
 *
 */
public class VuCalibrationRecord extends Block {
	
	private String calibrationPurpose; //CalibrationPurpose 
	private String workshopName;// Name,
	private String workshopAddress;// Address,
	private FullCardNumber workshopCardNumber;
	private Date workshopCardExpiryDate;// TimeReal	
	private String vehicleIdentificationNumber; //IA5String
	private VehicleRegistrationIdentification vehicleRegistrationIdentification;
	/**
	 * 2.154.   W-VehicleCharacteristicConstant
	 * Coeficiente característico del vehículo [definición k)].
	 * W-VehicleCharacteristicConstant ::= INTEGER(0..216-1))
	 * Asignación de valor: impulsos por kilómetro en el intervalo operativo de 0 a 64 255 impulsos/km.
	 */
	private int wVehicleCharacteristicConstant;
	/**
	 * 2.61.   K-ConstantOfRecordingEquipment
	 * Constante del aparato de control [definición m)].
	 * K-ConstantOfRecordingEquipment ::= INTEGER(0..216-1)
	 * Asignación de valor: impulsos por kilómetro en el intervalo operativo de 0 a 64 255 impulsos/km.
	 */
	private int kConstantOfRecordingEquipment;
	/**
	 * 2.63.   L-TyreCircumference
	 * Circunferencia efectiva de los neumáticos de las ruedas [definición u)].
	 * L-TyreCircumference ::= INTEGER(0..216-1)
	 * Asignación de valor: número binario sin signo, valor en 1/8 mm en el intervalo operativo de 0 a 8 031 mm.
	 */
	private int lTyreCircumference;
	private String tyreSize; //IA5String
	/**
	 * 2.106.   SpeedAuthorised
	 * Velocidad máxima autorizada para el vehículo [definición bb)].
	 * SpeedAuthorised ::= Speed
	 * 
	 * 2.105.   Speed
	 * Velocidad del vehículo (km/h).
	 * Speed ::= INTEGER(0..255)
	 * Asignación de valor: kilómetros por hora en el intervalo operativo de 0 a 220 km/h.
	 */
	private int authorisedSpeed;
	private int oldOdometerValue; //OdometerShort
	private int newOdometerValue; //OdometerShort
	private Date oldTimeValue; // TimeReal
	private Date newTimeValue; // TimeReal
	private Date nextCalibrationDate; // TimeReal

	/**
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public VuCalibrationRecord(byte[] bytes) throws UnsupportedEncodingException {
		int start = 0;
		this.calibrationPurpose = CalibrationPurpose.getCalibrationPurpose(Arrays.copyOfRange(bytes, start, start += VuSizes.CALIBRATIONPURPOSE.getSize()));
		Name n = new Name(Arrays.copyOfRange(bytes, start, start += VuSizes.WORKSHOPNAME_VUCALIBRATIONDATA.getSize()));
		this.workshopName = n.getName();
		n = new Name(Arrays.copyOfRange(bytes, start, start += VuSizes.WORKSHOPNAME_VUCALIBRATIONDATA.getSize()));
		this.workshopAddress = n.getName();
		this.workshopCardNumber = new FullCardNumber(Arrays.copyOfRange(bytes, start, start += VuSizes.WORKSHOPCARDNUMBER_VUCALIBRATIONDATA.getSize()));
		this.workshopCardExpiryDate = RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start += VuSizes.WORKSHOPCARDEXPIRYDATE.getSize()));
		IA5String ia5s = new IA5String(Arrays.copyOfRange(bytes, start, start += VuSizes.VEHICLEIDENTIFICATIONNUMBER.getSize()));
		this.vehicleIdentificationNumber = ia5s.getiA5String();
		this.vehicleRegistrationIdentification = new VehicleRegistrationIdentification(Arrays.copyOfRange(bytes, start, start += VuSizes.VEHICLEREGISTRATIONIDENTIFICATION_TREP5.getSize()));
		this.wVehicleCharacteristicConstant = Number.getNumber(Arrays.copyOfRange(bytes, start, start += VuSizes.WVEHICLECHARACTERISTICCONSTANT.getSize()));
		this.kConstantOfRecordingEquipment = Number.getNumber(Arrays.copyOfRange(bytes, start, start += VuSizes.KCONSTANTOFRECORDINGEQUIPMENT.getSize()));
		this.lTyreCircumference = Number.getNumber(Arrays.copyOfRange(bytes, start, start += VuSizes.LTYRECIRCUMFERENCE.getSize()));
		ia5s = new IA5String(Arrays.copyOfRange(bytes, start, start += VuSizes.TYRESIZE.getSize()));
		this.tyreSize = ia5s.getiA5String();
		this.authorisedSpeed = Number.getNumber(Arrays.copyOfRange(bytes, start, start += VuSizes.AUTHORISEDSPEED.getSize()));
		this.oldOdometerValue = OdometerShort.getOdometerShort(Arrays.copyOfRange(bytes, start, start += VuSizes.OLDODOMETERVALUE.getSize()));
		this.newOdometerValue = OdometerShort.getOdometerShort(Arrays.copyOfRange(bytes, start, start += VuSizes.NEWODOMETERVALUE.getSize()));
		this.oldTimeValue = RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start += VuSizes.OLDTIMEVALUE_VUCALIBRATIONDATA.getSize()));
		this.newTimeValue = RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start += VuSizes.NEWTIMEVALUE_VUCALIBRATIONDATA.getSize()));
		this.nextCalibrationDate = RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start += VuSizes.NEXTCALIBRATIONDATE.getSize()));
	}

}
