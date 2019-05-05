/**
 * 
 */
package com.tachographStructure.helpers;

import java.util.Arrays;

/**
 * 
 * Fecha expresada en un formato num�rico f�cil de imprimir.
 * 
 * Datef::= SEQUENCE {
 *
 * year BCDString(SIZE(2)),
 * 
 * month BCDString(SIZE(1)),
 *
 * day BCDString(SIZE(1))
 *
 * }
 *
 * Asignaci�n de valor:
 *
 * yyyy A�o
 *
 * mm Mes
 *
 * dd D�a
 * 
 * '00000000'H denota expl�citamente la ausencia de fecha.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class Datef {
	
	private String year;
	private String month;
	private String day;
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public Datef(byte[] bytes) {
		int start=0;
		this.year = BCDString.BCDtoString(Arrays.copyOfRange(bytes, start, start+=2));
		this.month = BCDString.BCDtoString(Arrays.copyOfRange(bytes, start, start+=1));
		this.day = BCDString.BCDtoString(Arrays.copyOfRange(bytes, start, start+=1));
	}

	/**
	 * Obtiene el a�o.
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Asigna el a�o.
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Obtiene el mes.
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Asigna el mes.
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * Obtiene el dia.
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * Asigna el dia.
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	
}
