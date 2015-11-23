/**
 * 
 */
package org.tacografo.file.cardblockdriver;

import java.util.ArrayList;
import java.util.Arrays;

import org.tacografo.file.cardblockdriver.subblock.CardActivityDailyRecord;
import org.tacografo.tiposdatos.Number;

/**
 * 2.13. CardDriverActivity
 * Información almacenada en una tarjeta de conductor o en una tarjeta del centro de ensayo y relativa a las actividades
 * del conductor (requisitos 199 y 219).
 * CardDriverActivity ::= SEQUENCE {
 * activityPointerOldestDayRecord INTEGER(0..CardActivityLengthRange-1),
 * activityPointerNewestRecord INTEGER(0..CardActivityLengthRange-1),
 * activityDailyRecords OCTET STRING (SIZE(CardActivityLengthRange))
 * }
 * activityPointerOldestDayRecord es un elemento que señala el comienzo del espacio de almacenamiento (número de
 * bytes a partir del principio de la cadena) que corresponde al registro completo más antiguo de ese día en la cadena activityDailyRecords.
 * El valor máximo viene dado por la longitud de la cadena.
 * 
 * activityPointerNewestRecord es un elemento que señala el comienzo del espacio de almacenamiento (número de
 * bytes a partir del principio de la cadena) que corresponde al registro más reciente de ese día en la cadena activityDailyRecords.
 * El valor máximo viene dado por la longitud de la cadena.
 * 
 * activityDailyRecords es el espacio disponible para almacenar los datos sobre la actividad del conductor (estructura de
 * datos: CardActivityDailyRecord) en cada uno de los días civiles en que se ha utilizado la tarjeta.
 * 
 * Asignación de valor: esta cadena de octetos se va llenando cíclicamente con registros del tipo CardActivityDailyRecord.
 * En el primer uso, el almacenamiento comienza en el primer byte de la cadena. Cada nuevo registro se añade al
 * final del anterior. Cuando la cadena está llena, el almacenamiento continúa en el primer byte de la cadena, con independencia
 * de si hay alguna pausa dentro de un elemento de datos. Antes de introducir en la cadena nuevos datos de actividad
 * (ampliando el actual activityDailyRecord, o introduciendo un nuevo activityDailyRecord) que sustituyan a datos
 * antiguos, es preciso actualizar el activityPointerOldestDayRecord para reflejar la nueva ubicación del registro completo
 * más antiguo de ese día, y además es preciso poner a 0 la longitud activityPreviousRecordLength de este (nuevo) registro
 * completo más antiguo del día.
 * 
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class CardDriverActivity extends CardBlockDriver implements CardBlock {

	/**
	 * 
	 */
	public CardDriverActivity() {}

	private Integer activityPointerOldestDayRecord;
	private Integer activityPointerNewestRecord;
	private ArrayList<CardActivityDailyRecord> activityDailyRecords;
	
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardDriverActivity(byte[] datos){
		
		int start=0; // indice del array de byte
		byte[] arrayorigen;
		
		this.activityPointerOldestDayRecord = (int) Number.getShort_16(Arrays.copyOfRange(datos, start, start+=Sizes.ACTIVITYPOINTEROLDESTADAYRECORD.getMax()));
		this.activityPointerNewestRecord = (int) Number.getShort_16(Arrays.copyOfRange(datos, start, start+=Sizes.ACTIVITYPOINTERNEWESTRECORD.getMax()));
		arrayorigen=Arrays.copyOfRange(datos, start, start+=13776);
		
		// preparamos array de datos ya que es una grabacion ciclica y cuando llega al final continua al principio
		// de los datos por lo que el inicio podria estar en medio de los datos 
		byte[] arraybyte;
		if (this.activityPointerNewestRecord<this.activityPointerOldestDayRecord){			
			byte[] array1=Arrays.copyOfRange(arrayorigen, this.activityPointerOldestDayRecord, 13776);
			
			byte[] array2=Arrays.copyOfRange(arrayorigen, 4, this.activityPointerNewestRecord);	
			arraybyte=new byte[array1.length+array2.length];
			System.arraycopy(array1, 0, arraybyte, 0, array1.length);
			System.arraycopy(array2, 0, arraybyte, array1.length-1, array2.length-1);
			
		}else{
			// hay que quitarle los 4 bytes primeros de activityPointerOldestDayRecord y acitivityPointerNewestRecord
			arraybyte=Arrays.copyOfRange(datos, this.activityPointerOldestDayRecord+4, this.activityPointerNewestRecord-this.activityPointerOldestDayRecord+4);	
			
		}	
		int length=0;
		int indice=0;
		CardActivityDailyRecord cadr;
		this.activityDailyRecords =new ArrayList<CardActivityDailyRecord>();		
		while (indice<arraybyte.length){
			length=Number.getShort_16(Arrays.copyOfRange(arraybyte,indice+2, indice+5));			
			if (length>0){				
				byte[] arrayfrom=Arrays.copyOfRange(arraybyte,indice, indice+=length);				
				cadr=new CardActivityDailyRecord(arrayfrom);
				this.activityDailyRecords.add(cadr);				
			}else{
				indice=arraybyte.length+1;
			}
		}	
	}

	/**
	 * Obtenemos elemento que señala el comienzo del espacio de almacenamiento (número de
	 * bytes a partir del principio de la cadena) que corresponde al registro completo más antiguo de ese día en la cadena activityDailyRecords.
	 * El valor máximo viene dado por la longitud de la cadena
	 * @return the activityPointerOldestDayRecord
	 */
	public Integer getActivityPointerOldestDayRecord() {
		return activityPointerOldestDayRecord;
	}

	/**
	 * Asignamos elemento que señala el comienzo del espacio de almacenamiento (número de
	 * bytes a partir del principio de la cadena) que corresponde al registro completo más antiguo de ese día en la cadena activityDailyRecords.
	 * El valor máximo viene dado por la longitud de la cadena
	 * @param activityPointerOldestDayRecord the activityPointerOldestDayRecord to set
	 */
	public void setActivityPointerOldestDayRecord(
			Integer activityPointerOldestDayRecord) {
		this.activityPointerOldestDayRecord = activityPointerOldestDayRecord;
	}

	/**
	 * Obtienes elemento que señala el comienzo del espacio de almacenamiento (número de
	 * bytes a partir del principio de la cadena) que corresponde al registro más reciente de ese día en la cadena activityDailyRecords.
	 * El valor máximo viene dado por la longitud de la cadena.
	 * @return the activityPointerNewestRecord
	 */
	public Integer getActivityPointerNewestRecord() {
		return activityPointerNewestRecord;
	}

	/**
	 * Asignamos elemento que señala el comienzo del espacio de almacenamiento (número de
	 * bytes a partir del principio de la cadena) que corresponde al registro más reciente de ese día en la cadena activityDailyRecords.
	 * El valor máximo viene dado por la longitud de la cadena.
	 * @param activityPointerNewestRecord the activityPointerNewestRecord to set
	 */
	public void setActivityPointerNewestRecord(Integer activityPointerNewestRecord) {
		this.activityPointerNewestRecord = activityPointerNewestRecord;
	}

	/**
	 * Obtenemos el espacio disponible para almacenar los datos sobre la actividad del conductor (estructura de
	 * datos: CardActivityDailyRecord) en cada uno de los días civiles en que se ha utilizado la tarjeta.
	 * @return the activityDailyRecords
	 */
	public ArrayList<CardActivityDailyRecord> getActivityDailyRecords() {
		return activityDailyRecords;
	}

	/**
	 * Asignamos el espacio disponible para almacenar los datos sobre la actividad del conductor (estructura de
	 * datos: CardActivityDailyRecord) en cada uno de los días civiles en que se ha utilizado la tarjeta.
	 * @param activityDailyRecords the activityDailyRecords to set
	 */
	public void setActivityDailyRecords(ArrayList<CardActivityDailyRecord> activityDailyRecords) {
		this.activityDailyRecords = activityDailyRecords;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardDriverActivity [activityPointerOldestDayRecord="
				+ activityPointerOldestDayRecord
				+ ", activityPointerNewestRecord="
				+ activityPointerNewestRecord + ", activityDailyRecords="
				+ activityDailyRecords + "]";
	}

	

	
	
	
}
