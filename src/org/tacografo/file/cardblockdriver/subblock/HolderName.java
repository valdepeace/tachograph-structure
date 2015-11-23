/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.Arrays;

import org.tacografo.file.cardblockdriver.Sizes;

/**
 * 2.60. HolderName
 * 
 * El nombre y apellidos del titular de una tarjeta.
 * 
 * HolderName::= SEQUENCE {
 * 
 * holderSurname Name,
 * 
 * holderFirstNames Name
 * 
 * } 
 *
 * holderSurname son los apellidos del titular, sin incluir sus t�tulos.
 *
 * Asignaci�n de valor: cuando una tarjeta no es personal, holderSurname contiene la misma informaci�n que companyName o workshopName o controlBodyName.
 *
 * holderFirstNames es el nombre y las iniciales del titular.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class HolderName {
	
	
	private String holderSurname;
	private String holderFirstNames;
	
	public HolderName() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public HolderName(byte[] bytes) {
		int start=0;
		Name n=new Name(Arrays.copyOfRange(bytes,start,start+=Sizes.HOLDERSURNAME.getMax()));
		this.holderSurname=n.getName().trim();
		n=new Name(Arrays.copyOfRange(bytes,start,start+=Sizes.HOLDERFIRTSNAMES.getMax()));
		this.holderFirstNames=n.getName().trim();
	}

	/**
	 * Obtiene los apellidos del titular, sin incluir sus t�tulos.
	 * @return the holderSurname
	 */
	public String getHolderSurname() {
		return holderSurname;
	}

	/**
	 * Asigna los apellidos del titular, sin incluir sus t�tulos.
	 * @param holderSurname the holderSurname to set
	 */
	public void setHolderSurname(String holderSurname) {
		this.holderSurname = holderSurname;
	}

	/**
	 * Obtiene el nombre y las iniciales del titular.
	 * @return the holderFirstNames
	 */
	public String getHolderFirstNames() {
		return holderFirstNames;
	}

	/**
	 * Asigna el nombre y las iniciales del titular.
	 * @param holderFirstNames the holderFirstNames to set
	 */
	public void setHolderFirstNames(String holderFirstNames) {
		this.holderFirstNames = holderFirstNames;
	}
	
}
