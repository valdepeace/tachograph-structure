/**
 * 
 */
package org.tacografo.file.vublock.subblock;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.ExtendedSerialNumber;
import org.tacografo.file.vublock.Sizes;
import org.tacografo.tiposdatos.IA5String;
import org.tacografo.tiposdatos.RealTime;

/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 
 * 2.97.   SensorPaired
 * 
 * Información almacenada en una unidad intravehicular y relativa a la identificación del sensor de movimiento acoplado a la unidad intravehicular (requisito 079).
 * SensorPaired ::= SEQUENCE {
 * sensorSerialNumber SensorSerialNumber,
 * sensorApprovalNumber SensorApprovalNumber,
 * sensorPairingDateFirst SensorPairingDate
 * }
 * sensorSerialNumber es el número de serie del sensor de movimiento que está acoplado actualmente a la unidad intravehicular.
 * sensorApprovalNumber es el número de homologación del sensor de movimiento que está acoplado actualmente a la unidad intravehicular.
 * sensorPairingDateFirst es la fecha en que el sensor de movimiento acoplado actualmente a la unidad intravehicular se acopló por primera vez a una unidad intravehicular.
 *
 */
public class SensorPaired {
	
	
	/**
	 * 2.99.   SensorSerialNumber
	 * Número de serie del sensor de movimiento.
	 * SensorSerialNumber ::= ExtendedSerialNumber
	 */
	private ExtendedSerialNumber sensorSerialNumber;
	/**
	 * 2.92.   SensorApprovalNumber
	 * Número de homologación del sensor.
	 * SensorApprovalNumber ::= IA5String(SIZE(8))
	 * Asignación de valor: sin especificar.
	 */
	private String sensorApprovalNumber;
	/**
	 * 2.98.   SensorPairingDate
	 * Fecha de un acoplamiento entre el sensor de movimiento y la unidad intravehicular.
	 * SensorPairingDate ::= TimeReal
	 * Asignación de valor: sin especificar.
	 */
	private Date sensorPairingDateFirst;

	/**
	 * @throws IOException 
	 * 
	 */
	public SensorPaired(byte[] bytes) throws IOException {
		int start=0;
		this.sensorSerialNumber=new ExtendedSerialNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.SENSORSERIALNUMBER.getSize()));
		System.out.println("sensorSerialNumber: "+this.sensorSerialNumber);
		IA5String ia5s=new IA5String(Arrays.copyOfRange(bytes, start, start+=Sizes.SENSORAPPROVALNUMBER.getSize()));
		this.sensorApprovalNumber=ia5s.getiA5String();
		this.sensorPairingDateFirst=RealTime.getRealTime(Arrays.copyOfRange(bytes, start, start+=Sizes.SENSORPAIRINGDATEFIRST.getSize()));
	}

}
