/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

import java.util.Arrays;

import org.tacografo.tiposdatos.OctetString;

/**
 * 
 * 2.70. Name
 *
 * Un nombre.
 * 
 * Name::= SEQUENCE {
 *
 * codePage INTEGER (0..255),
 *
 * name OCTET STRING (SIZE(35))
 *
 * }
 * 
 * codePage especifica la parte de la norma ISO/IEC 8859 que se utiliza para codificar el nombre,
 * 
 * name es un nombre codificado con arreglo a la norma ISO/IEC 8859-codePage.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class Name {

	private String name;
	private short codePage;
	
	
	public Name() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public Name(byte[] datos){
	
		this.codePage=datos[0];
				
		OctetString os=new OctetString(Arrays.copyOfRange(datos,1,35),this.codePage);
		this.name=os.getOctetString();
	}

	/**
	 * Obtiene un nombre codificado con arreglo a la norma ISO/IEC 8859-codePage. 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Asigna un nombre codificado con arreglo a la norma ISO/IEC 8859-codePage.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene la parte de la norma ISO/IEC 8859 que se utiliza para codificar el nombre.
	 * @return the codePage
	 */
	public short getCodePage() {
		return codePage;
	}

	/**
	 * Asigna la parte de la norma ISO/IEC 8859 que se utiliza para codificar el nombre.
	 * @param codePage the codePage to set
	 */
	public void setCodePage(short codePage) {
		this.codePage = codePage;
	}
	
}
