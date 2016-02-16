/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 2.1.   ActivityChangeInfo
 *
 * Este tipo de datos permite codificar, en una palabra de dos bytes, 
 * el estado de la ranura a las 00.00 o el estado del conductor a las 00.00 y/o 
 * los cambios de actividad y/o los cambios del régimen de conducción y/o 
 * los cambios del estado de la tarjeta para un conductor o un segundo conductor. 
 * Este tipo de datos está relacionado con los requisitos 084, 109a, 199 y 219.
 * 
 * ActivityChangeInfo ::= OCTET STRING (SIZE(2))
 * Asignación de valor — Alineación de octeto:′scpaattttttttttt′B (16 bits)
 * 
 * Para registros en la memoria de datos (o estado de la ranura):
 * ′s′B Ranura:
 * 		′0′B: CONDUCTOR,
 * 		′1′B: SEGUNDO CONDUCTOR,
 * ′c′B Régimen de conducción:
 * 		′0′B: EN SOLITARIO,
 * 		′1′B: EN EQUIPO,
 * 	′p′B Estado de la tarjeta del conductor (o del centro de ensayo) en la ranura que corresponda:
 * 		′0′B: INSERTADA, hay una tarjeta insertada,
 * 		′1′B: NO INSERTADA, no hay tarjeta insertada (o se ha extraído una tarjeta),
 * 	′aa′B Actividad:
 *		′00′B: PAUSA/DESCANSO,
 * 		′01′B: DISPONIBILIDAD,
 * 		′10′B: TRABAJO,
 * 		′11′B: CONDUCCIÓN,
 * 	′ttttttttttt′B Hora del cambio: minutos transcurridos desde las 00.00 horas de ese día.
 * 
 * Para registros en la tarjeta del conductor (o del centro de ensayo) (y estado del conductor):
 * 
 * ′s′B Ranura (irrelevante cuando ′p′ = 1, excepto en el caso que se cita en la nota siguiente):
 *		′0′B: CONDUCTOR,
 * 		′1′B: SEGUNDO CONDUCTOR,
 * ′c′B : 
 * 		Régimen de conducción (caso ′p′ = 0): 
 *			′0′B: EN SOLITARIO, 
 *			′1′B: EN EQUIPO,
 *		Régimen en la actividad siguiente (caso ′p′ = 1): 
 *			′0′B: INDETERMINADO 
 *			′1′B: DETERMINADO (= entrada manual)
 * 
 * ′p′B Estado de la tarjeta:
 * 		′0′B: INSERTADA, la tarjeta está insertada en un equipo de control,
 * 		′1′B: NO INSERTADA, la tarjeta no está insertada (o se ha extraído),
 * ′aa′B Actividad (irrelevante cuando ′p′ = 1 y ′c′ = 0, excepto en el caso citado en la nota siguiente):
 * 		′00′B: PAUSA/DESCANSO,
 * 		′01′B: DISPONIBILIDAD,
 * 		′10′B: TRABAJO,
 * 		′11′B: CONDUCCIÓN,
 * ′ttttttttttt′B Hora del cambio: minutos transcurridos desde las 00h00 de ese día.
 * 
 * Nota sobre el caso «extracción de la tarjeta»:
 * 
 * Cuando se extrae la tarjeta:
 * 		— ′s′ es relevante e indica la ranura de la que se extrae la tarjeta,
 *		— ′c′ debe configurarse a 0,
 *		— ′p′ debe configurarse a 1,
 * 		— ′aa′ debe codificar la actividad que esté seleccionada en ese momento,
 * Como resultado de una entrada manual, los bits ′c′ y ′aa′ de la palabra (almacenada en una tarjeta) se pueden sobrescribir posteriormente para reflejar la entrada.
 * @author Andrés Carmona Gil
 * @version 0.0.1
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
		String s3=s1+s2;
		
		int s,c,p,aa,t;		
		s=Integer.valueOf(s3.substring(0, 1));
		c=Integer.valueOf(s3.substring(1, 2));
		p=Integer.valueOf(s3.substring(2, 3));
		aa=Integer.valueOf(s3.substring(3, 5));
		t=Short.valueOf(s3.substring(5,16),2);
	
		this.s=(s==0)?"conductor":"segundo conductor";
		if (p==0){
			this.c=(s==0)?"solitario":"en equipo";
		}else{
			this.c=(s==0)?"indeterminado":"entrada manual";
		}
		this.p=(s==0)?"insertada":"no insertada";
		switch(aa){
			case 00: this.aa="PAUSA/DESCANSO";break;
			case 01: this.aa="DISPONIBILIDAD";break;
			case 10: this.aa="TRABAJO";break;
			case 11: this.aa="CONDUCCIÓN";break;
		}
		int hora=0;
		int min=0;
		if(t>60){
			hora=t/60;
			min=t%60;
		}else{
			min=t;
		}
		
		this.hours=hora;
		this.min=min;
		this.t=String.valueOf(hora)+":"+String.valueOf(min);
		
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
	


