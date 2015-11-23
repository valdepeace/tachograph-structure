/**
 * 
 */
package org.tacografo.tiposdatos;

import java.util.Arrays;

/**
 * 
 * Fecha expresada en un formato numérico fácil de imprimir.
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
 * Asignación de valor:
 *
 * yyyy Año
 *
 * mm Mes
 *
 * dd Día
 * 
 * '00000000'H denota explícitamente la ausencia de fecha.
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
	 * Obtiene el año.
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Asigna el año.
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
