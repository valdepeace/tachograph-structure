/**
 * 
 */
package org.tacografo.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tacografo.file.certificate.Signature;

/**
 * @author Andres Carmona Gil
 *
 */
public abstract class Block {

	private String FID;	
	
	private String TRED;
	
	private int size;			
	@JsonIgnore
	private byte[] datos;

	private Signature signature;
	
	
	public String getTRED() {
		return TRED;
	}

	public void setTRED(String tRED) {
		TRED = tRED;
	}

	/** 
	 *  Identificador del bloque segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISI�N
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @return the fID
	 */
	public String getFID() {
		return FID;
	}

	/**
	 * Asigna Identificador del bloque segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISI�N
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @param fID the fID to set
	 */
	public void setFID(String fID) {
		FID = fID;
	}



	/**
	 * Tama�o de cada bloque y sus propiedades segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISI�N
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Asigna tama�o de cada bloque y sus propiedades segun definido en REGLAMENTO (CE) No 1360/2002 DE LA COMISI�N
	 * de 13 de junio de 2002 hoja "L 207/119"
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Retorna array de bytes de los datos del bloque
	 * @return the datos
	 */
	public byte[] getDatos() {
		return datos;
	}

	/**
	 * Asignamos el array de bytes al bloque
	 * @param datos the datos to set
	 */
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}
	
	/**
	 * Imprime por consola los datos en binario usado para el debug
	 */
	public void toBinaryString(){
		String s;
		for (Byte bite:this.datos){
			s = String.format("%8s", Integer.toBinaryString(bite & 0xFF)).replace(' ', '0');
			System.out.println(s);
		}
		//String s = String.format("%8s", Integer.toBinaryString(bite [0] & 0xFF)).replace(' ', '0');
		
	}
	/**
	 * Imprime por consola los datos en hexadecimal usado para el debug
	 */
	public void toHexString(){
		for (Byte bite:this.datos){
			System.out.println(Integer.toHexString(bite));
		}
	}
	/**
	 * Devuelve el bloque mapeado a json
	 * @return json
	 */
	public String toJson(){
		ObjectMapper mapper=new ObjectMapper();
		String str="";
		try {
			str=mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return str;
	}


	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}
	
}
