/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

/**
 *
 * This data type enables to code, within a two bytes word, a slot status at 00:00 and/or a driver status at 00:00
 * and/or changes of activity and/or changes of driving status and/or changes of card status for a driver or a codriver. This data type is related to Annex 1C requirements 105, 266, 291, 320, 321, 343, and 344.
 * Value assignment — Octet Aligned: ‘scpaattttttttttt’B (16 bits)
 * For Data Memory recordings (or slot status):
 * 		‘s’B Slot:
 *			‘0’B: DRIVER,
 *			‘1’B: CO-DRIVER,
 *		‘c’B Driving status:
 *			‘0’B: SINGLE,
 *			‘1’B: CREW,
 *		‘p’B Driver (or workshop) card status in the relevant slot:
 * 			‘0’B: INSERTED, a card is inserted,
 *			‘1’B: NOT INSERTED, no card is inserted (or a card is withdrawn),
 * 		‘aa’B Activity:
 * 			‘00’B: BREAK/REST,
 * 			‘01’B: AVAILABILITY,
 * 			‘10’B: WORK,
 * 			‘11’B: DRIVING,
 * 		‘ttttttttttt’B Time of the change: Number of minutes since 00h00 on the given day
 * For Driver (or Workshop) card recordings (and driver status):
 * 		‘s’B Slot (not relevant when ‘p’=1 except note below):
 * 			‘0’B: DRIVER,
 * 			‘1’B: CO-DRIVER,
 * 		‘c’B Driving status (case ‘p’=0) or
 * 			Following activity status (case ‘p’=1):
 * 				‘0’B: SINGLE,
 * 				‘0’B: UNKNOWN
 * 				‘1’B: CREW,
 * 				‘1’B: KNOWN (=manually entered)
 * 		‘p’B Card status:
 * 			‘0’B: INSERTED, the card is inserted in a recording equipment,
 * 			‘1’B: NOT INSERTED, the card is not inserted (or the card is withdrawn),
 * 		‘aa’B Activity (not relevant when ‘p’=1 and ‘c’=0 except note below):
 * 			‘00’B: BREAK/REST,
 * 			‘01’B: AVAILABILITY,
 * 			‘10’B: WORK,
 * 			‘11’B: DRIVING,
 * 		‘ttttttttttt’B Time of the change: Number of minutes since 00h00 on the given day.
 * Note for the case ‘card withdrawal’:
 * When the card is withdrawn:
 * — ‘s’ is relevant and indicates the slot from which the card is withdrawn,
 * — ‘c’ must be set to 0,
 * — ‘p’ must be set to 1,
 * — ‘aa’ must code the current activity selected at that time,
 * As a result of a manual entry, the bits ‘c’ and ‘aa’ of the word (stored in a card) may be overwritten later to
 * reflect the entry.
 * @author Andres Carmona Gil
 * @version 0.0.2
 *
 */
public class ActivityChangeInfo {
	
	private String s,c,p,aa,t;
	private int hours;
	private int min;
	
	public ActivityChangeInfo() {}

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */

	@SuppressWarnings("unused")
	public ActivityChangeInfo(byte[] bytes){
				
		
		String s1 = String.format("%8s", Integer.toBinaryString(bytes[0] & 0xFF)).replace(' ', '0');
		String s2 = String.format("%8s", Integer.toBinaryString(bytes[1] & 0xFF)).replace(' ', '0');				
		String s3 = s1+s2;
		
		int s,c,p,aa,t;		
		s = Integer.valueOf(s3.substring(0, 1));
		c = Integer.valueOf(s3.substring(1, 2));
		p = Integer.valueOf(s3.substring(2, 3));
		aa = Integer.valueOf(s3.substring(3, 5));
		t = Short.valueOf(s3.substring(5,16),2); //Obtenemos los minutos a partir de las 00:00
	
		this.s = (s==0)?"driver":"co-driver";
		if (p==0){
			this.c = (c==0)?"single":"crew"; //crew=en equipo
		} else {
			this.c = (c==0)?"unknown":"known"; //manually entered
		}
		this.p = (p==0)?"inserted":"not inserted";
		switch(aa){
			case 00: this.aa="BREAK/REST";break;
			case 01: this.aa="AVAILABILITY";break;
			case 10: this.aa="WORK";break;
			case 11: this.aa="DRIVING";break;
		}
		int hora = 0;
		int min = 0;
		if (t > 60){
			hora = t/60;
			min = t%60;
		} else {
			min = t;
		}
		
		this.hours = hora;
		this.min = min;
		this.t = String.valueOf(hora)+":"+String.valueOf(min);
		
	}

	
	
	/**
	 * Obtiene (irrelevante cuando ′p′ = 1, excepto en el caso que se cita en la nota siguiente):
	 *		′0′B: CONDUCTOR,
	 * 		′1′B: SEGUNDO CONDUCTOR,
	 * @return the s
	 */
	public String getS() {
		return s;
	}



	/**
	 * Asigna  (irrelevante cuando ′p′ = 1, excepto en el caso que se cita en la nota siguiente):
	 *		′0′B: CONDUCTOR,
	 * 		′1′B: SEGUNDO CONDUCTOR,
	 * @param s the s to set
	 */
	public void setS(String s) {
		this.s = s;
	}



	/**
	 * Obtiene Régimen de conducción (caso ′p′ = 0): 
	 *			′0′B: EN SOLITARIO, 
	 *			′1′B: EN EQUIPO,
	 *		Régimen en la actividad siguiente (caso ′p′ = 1): 
	 *			′0′B: INDETERMINADO 
	 *			′1′B: DETERMINADO (= entrada manual)
	 * 	
	 * @return the c
	 */
	public String getC() {
		return c;
	}



	/**
	 * Asigna Régimen de conducción (caso ′p′ = 0): 
	 *			′0′B: EN SOLITARIO, 
	 *			′1′B: EN EQUIPO,
	 *		Régimen en la actividad siguiente (caso ′p′ = 1): 
	 *			′0′B: INDETERMINADO 
	 *			′1′B: DETERMINADO (= entrada manual)
	 *  	
	 *			′0′B: EN SOLITARIO, 
	 *			′1′B: EN EQUIPO,
	 * @param c the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}



	/**
	 * Obtiene estado de la tarjeta:
	 * 		′0′B: INSERTADA, la tarjeta está insertada en un equipo de control,
	 * 		′1′B: NO INSERTADA, la tarjeta no está insertada (o se ha extraído),
	 * @return the p
	 */
	public String getP() {
		return p;
	}



	/**
	 * Asigna estado de la tarjeta:
	 * 		′0′B: INSERTADA, la tarjeta está insertada en un equipo de control,
	 * 		′1′B: NO INSERTADA, la tarjeta no está insertada (o se ha extraído),
	 * @param p the p to set
	 */
	public void setP(String p) {
		this.p = p;
	}



	/**
	 * Obtiene Actividad (irrelevante cuando ′p′ = 1 y ′c′ = 0, excepto en el caso citado en la nota siguiente):
	 * 		′00′B: PAUSA/DESCANSO,
 	* 		′01′B: DISPONIBILIDAD,
 	* 		′10′B: TRABAJO,
 	* 		′11′B: CONDUCCIÓN,
	 * @return the aa
	 */
	public String getAa() {
		return aa;
	}



	/**
	 * Asigna Actividad (irrelevante cuando ′p′ = 1 y ′c′ = 0, excepto en el caso citado en la nota siguiente):
	 * 		′00′B: PAUSA/DESCANSO,
 	* 		′01′B: DISPONIBILIDAD,
 	* 		′10′B: TRABAJO,
 	* 		′11′B: CONDUCCIÓN,
	 * @param aa the aa to set
	 */
	public void setAa(String aa) {
		this.aa = aa;
	}



	/**
	 * Obtiene hora del cambio: minutos transcurridos desde las 00h00 de ese día.
	 * @return the t
	 */
	public String getT() {
		return t;
	}



	/**
	 * Asigna hora del cambio: minutos transcurridos desde las 00h00 de ese día.
	 * @param t the t to set
	 */
	public void setT(String t) {
		this.t = t;
	}



	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nActivityChangeInfo [s=" + s + ", c=" + c + ", p=" + p + ", aa="
				+ aa + ", t=" + t + "]";
	}
		
		
		
}
	


