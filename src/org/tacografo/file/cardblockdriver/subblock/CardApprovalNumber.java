
/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;


import java.io.IOException;





import org.tacografo.tiposdatos.IA5String;




/**
 * 2.7. CardApprovalNumber 
 *Número de homologación de la tarjeta.
 *CardApprovalNumber ::= IA5String(SIZE(8))
 * 
 * @author Andrés Carmona Gil
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
	 * Obtiene el numero de homologación de la tarjeta
	 * @return String
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * Asigna el numero de homologación de la tarjeta
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	

}
