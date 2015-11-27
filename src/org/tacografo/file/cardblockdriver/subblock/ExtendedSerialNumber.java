package org.tacografo.file.cardblockdriver.subblock;
import java.io.IOException;
import java.util.Arrays;
import org.tacografo.tiposdatos.BCDString;
import org.tacografo.tiposdatos.Number;
import org.tacografo.tiposdatos.OctetString;


/**
 * @author Andres Carmona Gil
 * @version 0.0.1
 * 
 * 2.56. ExtendedSerialNumber
 *
 * Identificaci�n exclusiva de un equipo. Tambi�n puede utilizarse como el identificador de clave p�blica de un equipo.
 *
 * ExtendedSerialNumber::= SEQUENCE {
 * serialNumber INTEGER(0..232-1)
 * monthYear BCDString(SIZE(2))
 * type OCTET STRING(SIZE(1))
 * manufacturerCode ManufacturerCode
 * }
 *
 * serialNumber es el n�mero de serie de un equipo; exclusivo para el fabricante, para el tipo de equipo y para el mes a que se refiere la l�nea siguiente.
 * monthYear es la identificaci�n del mes y el a�o de fabricaci�n (o de la asignaci�n del n�mero de serie).
 * Asignaci�n de valor: codificaci�n BCD del mes (dos d�gitos) y el a�o (dos �ltimos d�gitos).
 * type es un identificador del tipo de equipo.
 * Asignaci�n de valor: espec�fica del fabricante, con 'FFh' valor reservado.
 * manufacturerCode es el c�digo num�rico del fabricante del equipo.
 *
 */
public class ExtendedSerialNumber {
	
	public String serialNumber;
	
	public String monthYear;
	
	public String type;
	
	public ManufacturerCode manufacturerCode;

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	
	public ExtendedSerialNumber(byte[] datos) throws IOException {
		//start indice
		int start=0;
		this.serialNumber=String.format("%d",Number.getInteger_32(Arrays.copyOfRange(datos, start, start+=4)));
		
				
		this.monthYear= BCDString.BCDtoString(Arrays.copyOfRange(datos, start, start+=2));
		
		OctetString os=new OctetString(Arrays.copyOfRange(datos, start, start+=1));		
		this.type=(os.getOctetString().equals("ff"))?"valor reservado":os.getOctetString();
		
		this.manufacturerCode=new ManufacturerCode(Arrays.copyOfRange(datos,start,start+=1));
		
		
	}
	public ExtendedSerialNumber(){}
	
	/**
	 * Obtiene el n�mero de serie de un equipo; exclusivo para el fabricante, para el tipo de equipo y para el mes a que se refiere la l�nea siguiente.
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * Asigna el n�mero de serie de un equipo; exclusivo para el fabricante, para el tipo de equipo y para el mes a que se refiere la l�nea siguiente.
	 * 
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * Obtiene la identificaci�n del mes y el a�o de fabricaci�n (o de la asignaci�n del n�mero de serie).
	 *  Asignaci�n de valor: codificaci�n BCD del mes (dos d�gitos) y el a�o (dos �ltimos d�gitos).
	 * @return the monthYear
	 */
	public String getMonthYear() {
		return monthYear;
	}
	/**
	 * Asigna la identificaci�n del mes y el a�o de fabricaci�n (o de la asignaci�n del n�mero de serie).
	 * Asignaci�n de valor: codificaci�n BCD del mes (dos d�gitos) y el a�o (dos �ltimos d�gitos).
	 * @param monthYear the monthYear to set
	 */
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	/**
	 * Obtiene un identificador del tipo de equipo.
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Asigna un identificador del tipo de equipo.
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Obtiene el c�digo num�rico del fabricante del equipo.
	 * @return the manufacturerCode
	 */
	public ManufacturerCode getManufacturerCode() {
		return manufacturerCode;
	}
	/**
	 * Asigna el c�digo num�rico del fabricante del equipo.
	 * @param manufacturerCode the manufacturerCode to set
	 */
	public void setManufacturerCode(ManufacturerCode manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	public String toString(){
		String cadena;
		cadena = "\nserial number: "+this.serialNumber+
				"\nmes y a�o  "+ this.monthYear+
				"\ntipo: "+this.type+
				"\ncodigo fabricante :"+this.manufacturerCode.getManufactureCode();
		return cadena;
	}
	
	
	
}
