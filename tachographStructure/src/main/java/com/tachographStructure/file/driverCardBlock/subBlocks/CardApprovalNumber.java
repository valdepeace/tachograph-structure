package com.tachographStructure.file.driverCardBlock.subBlocks;

import java.io.IOException;
import com.tachographStructure.helpers.IA5String;

/**
 * 2.7. CardApprovalNumber 
 *N�mero de homologaci�n de la tarjeta.
 *CardApprovalNumber ::= IA5String(SIZE(8))
 * 
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class CardApprovalNumber {
	
	private String number;
	
	public CardApprovalNumber(){}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public CardApprovalNumber(byte[] datos) throws IOException{		
		
		IA5String ia5=new IA5String(datos);
		this.number=ia5.getiA5String();
		
	}
	
	public String toString(){
		String cadena="";
		cadena="\nnumber: "+this.number;
		return cadena;
	}
	/**
	 * Obtiene el numero de homologaci�n de la tarjeta
	 * @return String
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Asigna el numero de homologaci�n de la tarjeta
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	

}
