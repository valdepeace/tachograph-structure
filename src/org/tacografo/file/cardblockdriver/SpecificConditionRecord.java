/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import java.util.Arrays;
import java.util.Date;

import org.tacografo.file.cardblockdriver.subblock.SpecificConditionType;
import org.tacografo.tiposdatos.RealTime;

/**
 * 2.104. SpecificConditionRecord
 * Información almacenada en una tarjeta de conductor, una tarjeta del centro de ensayo o una unidad intravehicular y
 * relativa a una condición específica (requisitos 105a, 212a y 230a).
 * SpecificConditionRecord ::= SEQUENCE {
 * entryTime TimeReal,
 * specificConditionType SpecificConditionType
 * }
 * entryTime es la fecha y la hora de la entrada.
 * specificConditionType es el código que identifica a la condición específica.
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class SpecificConditionRecord extends CardBlockDriver implements
		CardBlock {
	private Date entryTime;
	private  String specificConditionType;
	
	
	public SpecificConditionRecord() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public SpecificConditionRecord(byte[] datos){
		int start=0;
		this.entryTime=RealTime.getRealTime(Arrays.copyOfRange(datos, start, start+=Sizes.ENTRYTIME.getMax()));
		SpecificConditionType sct=new SpecificConditionType(Arrays.copyOfRange(datos,start,start+=Sizes.SPECIFICCIONDITIONTYPE.getMax()));
		this.specificConditionType=sct.getSpecificConditionType();
		}

	/**
	 * Obtiene la fecha y la hora de la entrada.
	 * @return Date
	 */
	public Date getEntryTime() {
		return entryTime;
	}
	/**
	 * Asigna la fecha y la hora de la entrada.
	 * @param entryTime
	 */
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	/**
	 * Obtiene el código que identifica a la condición específica.
	 * @return String
	 */
	public String getSpecificConditionType() {
		return specificConditionType;
	}
	/**
	 * Asigna el código que identifica a la condición específica.
	 * @param specificConditionType
	 */
	public void setSpecificConditionType(String specificConditionType) {
		this.specificConditionType = specificConditionType;
	}

	@Override
	public String toString() {
		return "SpecificConditionRecord [entryTime=" + entryTime
				+ ", specificConditionType=" + specificConditionType + "]";
	}
	
}
