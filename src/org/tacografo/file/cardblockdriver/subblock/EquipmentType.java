/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;

/**
 * 2.52. EquipmentType
 *
 * Código para distinguir diferentes tipos de equipos para la aplicación de tacógrafo.
 * 
 * EquipmentType::= INTEGER(0..255)
 * 
 * - - Reserved (0),
 * 
 * - - Driver Card (1),
 * 
 * - - Workshop Card (2),
 * 
 * - - Control Card (3),
 *
 * - - Company Card (4),
 *
 * - - Manufacturing Card (5),
 *
 * - - Vehicle Unit (6),
 *
 * - - Motion Sensor (7),
 *
 * - - RFU (8..255)
 *
 * Asignación de valor: con arreglo a la norma ISO/IEC8824-1.
 * 
 * El valor 0 se reserva para designar a un Estado miembro o a Europa en el campo CHA de los certificados.
 * 
 *
 * @author Andres
 *
 */

public class EquipmentType {
	

	private Short equipmentType;
	
	public EquipmentType(){}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public EquipmentType(byte[] datos) {		
		this.equipmentType=(short) datos[0];
	}

	/**
	 * Obtiene el tipo de equipo para la aplicación de tacógrafo.
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		
		switch(this.equipmentType){
			case 0 :return "Reserved(0)";
			case 1: return "Driver Card (1)";
			case 2: return "Workshop Card(2)";
			case 3: return "Control Card(3)";
			case 4: return "Company Card(4)";
			case 5: return "Manufacturing Card(5)";
			case 6: return "Vehicle Unit(6)";
			case 7: return "Motion Sensor(7)";
			default: return "RFU(8....255)";
		}
		
	}

	/**
	 * Asigna el tipo de equipo para la aplicación de tacógrafo.
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(Short equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	
}
