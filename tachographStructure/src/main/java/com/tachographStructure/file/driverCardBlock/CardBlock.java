/**
 * 
 */
package com.tachographStructure.file.driverCardBlock;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Inteface en la que definimos el comportamiento de la diferentes bloques
 * y que haremos uso intenso con la factoria de bloques
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public interface CardBlock {

	
	
	public String toString();
	
	public void toHexString();
	
	public void toBinaryString();
	
	public String getFID();
	@JsonIgnore
	public byte[] getDatos();
	
	
}
