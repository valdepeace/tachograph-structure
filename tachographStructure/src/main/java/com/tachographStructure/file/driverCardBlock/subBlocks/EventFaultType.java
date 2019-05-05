/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;

/**
 * 
 * 2.54. EventFaultType
 *
 * C�digo que califica un incidente o un fallo.
 *
 * EventFaultType::= OCTET STRING (SIZE(1))
 * 
 * Asignaci�n de valor:
 *
 * '0x'H Incidentes de car�cter general,
 * '00'H No hay m�s informaci�n,
 * '01'H Inserci�n de una tarjeta no v�lida,
 * '02'H Conflicto de tarjetas,
 * '03'H Solapamiento temporal,
 * '04'H Conducci�n sin tarjeta adecuada,
 * '05'H Inserci�n de tarjeta durante la conducci�n,
 * '06'H Error al cerrar la �ltima sesi�n de la tarjeta,
 * '07'H Exceso de velocidad,
 * '08'H Interrupci�n del suministro el�ctrico,
 * '09'H Error en datos de movimiento,
 * '0A'H.. '0F'H RFU,
 * '1x'H Intentos de violaci�n de la seguridad relacionados con la unidad intravehicular,
 * '10'H No hay m�s informaci�n,
 * '11'H Fallo de autentificaci�n del sensor de movimiento,
 * '12'H Fallo de autentificaci�n de la tarjeta de tac�grafo,
 * '13'H Cambio no autorizado del sensor de movimiento,
 * '14'H Error de integridad en la entrada de los datos de la tarjeta
 * '15'H Error de integridad en los datos de usuario almacenados,
 * '16'H Error en una transferencia interna de datos,
 * '17'H Apertura no autorizada de la carcasa,
 * '18'H Sabotaje del hardware, 
 * '19'H.. '1F'H RFU,
 * '2x'H Intentos de violaci�n de la seguridad relacionados con el sensor,
 * '20'H No hay m�s informaci�n,
 * '21'H Fallo de autentificaci�n,
 * '22'H Error de integridad en los datos almacenados,
 * '23'H Error en una transferencia interna de datos,
 * '24'H Apertura no autorizada de la carcasa,
 * '25'H Sabotaje del hardware,
 * '26'H.. '2F'H RFU,
 * '3x'H Fallos del aparato de control,
 * '30'H No hay m�s informaci�n,
 * '31'H Fallo interno de la VU,
 * '32'H Fallo de la impresora,
 * '33'H Fallo de la pantalla,
 * '34'H Fallo de transferencia,
 * '35'H Fallo del sensor,
 * '36'H.. '3F'H RFU,
 * '4x'H Fallos de las tarjetas,
 * '40'H No hay m�s informaci�n,
 * '41'H.. '4F'H RFU,
 * '50'H.. '7F'H RFU,
 * '80'H.. 'FF'H Espec�ficos del fabricante.
 *
 *
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */
public class EventFaultType {
	

	private int eventFaultType;
	
	
	public EventFaultType() {}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public EventFaultType(byte[] bytes) {
		this.eventFaultType=bytes[0];
	}
	/**
	 * Obtiene el incidente o fallo.
	 * @return tipo de incidente o fallo
	 */
	public String getEventFaultType(){
		String cadena="RFU";
		switch(this.eventFaultType){
			//'0x'H Incidentes de car�cter general
			case 0x00:cadena="No hay m�s informaci�n";break;
			case 0x01:cadena="Inserci�n de una tarjeta no v�lida";break;		 
			case 0x02:cadena="Conflicto de tarjetas";break;
			case 0x03:cadena="Solapamiento temporal";break;
			case 0x04:cadena="Conducci�n sin tarjeta adecuada";break;
			case 0x05:cadena="Inserci�n de tarjeta durante la conducci�n";break;
			case 0x06:cadena="Error al cerrar la �ltima sesi�n de la tarjeta";break;
			case 0x07:cadena="Exceso de velocidad";break;
			case 0x08:cadena="Interrupci�n del suministro el�ctrico";break;
			case 0x09:cadena="Error en datos de movimiento";break;			
			//'1x'H Intentos de violaci�n de la seguridad relacionados con la unidad intravehicular,
			case 0x10:cadena="No hay m�s informaci�n";break;
			case 0x11:cadena="Fallo de autentificaci�n del sensor de movimiento";break;
			case 0x12:cadena="Fallo de autentificaci�n de la tarjeta de tac�grafo";break;
			case 0x13:cadena="Cambio no autorizado del sensor de movimiento";break;
			case 0x14:cadena="Error de integridad en la entrada de los datos de la tarjeta";break;
			case 0x15:cadena="Error de integridad en los datos de usuario almacenados";break;
			case 0x16:cadena="Error en una transferencia interna de datos";break;
			case 0x17:cadena="Apertura no autorizada de la carcasa";break;
			case 0x18:cadena="Sabotaje del hardware";break; 
			// '2x'H Intentos de violaci�n de la seguridad relacionados con el sensor,
			case 0x20:cadena="No hay m�s informaci�n";break;
			case 0x21:cadena="Fallo de autentificaci�n";break;
			case 0x22:cadena="Error de integridad en los datos almacenados";break;
			case 0x23:cadena="Error en una transferencia interna de datos";break;
			case 0x24:cadena="Apertura no autorizada de la carcasa";break;
			case 0x25:cadena="Sabotaje del hardware";break;			
			//'3x'H Fallos del aparato de control,
			case 0x30:cadena="No hay m�s informaci�n";break;
			case 0x31:cadena="Fallo interno de la VU";break;
			case 0x32:cadena="Fallo de la impresora";break;
			case 0x33:cadena="Fallo de la pantalla";break;
			case 0x34:cadena="Fallo de transferencia";break;
			case 0x35:cadena="Fallo del sensor";break;			
			// '4x'H Fallos de las tarjetas,
			case 0x40:cadena="No hay m�s informaci�n";break;
			//case 0x41:H.. '4F'H RFU,
			// '50'H.. '7F'H RFU,
			// '80'H.. 'FF'H Espec�ficos del fabricante.
		}
		
		if (this.eventFaultType>=0x0a && this.eventFaultType<=0x0f ||
				this.eventFaultType>=0x1a && this.eventFaultType<=0x1f ||
				this.eventFaultType>=0x26 && this.eventFaultType<=0x2f ||
				this.eventFaultType>=0x36 && this.eventFaultType<=0x3f ||
				this.eventFaultType>=0x41 && this.eventFaultType<=0x4f 
				){
			cadena="RFU";
		}else if(this.eventFaultType>=0x80 && this.eventFaultType<=0xff ){
			cadena="Espec�ficos del fabricante";
		}
		return cadena;
	}
}
