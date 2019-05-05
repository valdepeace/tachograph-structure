/**
 * 
 */
package com.tachographStructure.file.driverCardBlock.subBlocks;


/**
 * 
 * 2.72. NationNumeric
 * Referencia num�rica a un pa�s.
 * 
 * NationNumeric ::= INTEGER(0..255)
 * 
 * Asignaci�n de valor:
 * 
 * - - No hay informaci�n disponible (00)H,
 * - - Austria (01)H,
 * - - Albania (02)H,
 * - - Andorra (03)H,
 * - - Armenia (04)H,
 * - - Azerbaiy�n (05)H,
 * - - B�lgica (06)H,
 * - - Bulgaria (07)H,
 * - - Bosnia y Hercegovina (08)H,
 * - - Bielorrusia (09)H,
 * - - Suiza (0A)H,
 * - - Chipre (0B)H,
 * - - Rep�blica Checa (0C)H,
 * - - Alemania (0D)H,
 * - - Dinamarca (0E)H,
 * - - Espa�a (0F)H,
 * - - Estonia (10)H,
 * - - Francia (11)H,
 * - - Finlandia (12)H,
 * - - Liechtenstein (13)H,
 * - - Islas Feroe (14)H,
 * - - Reino Unido (15)H,
 * - - Georgia (16)H,
 * - - Grecia (17)H,
 * - - Hungr�a (18)H,
 * - - Croacia (19)H,
 * - - Italia (1A)H,
 * - - poikl Irlanda (1B)H,
 * - - Islandia (1C)H,
 * - - Kazajist�n (1D)H,
 * - - Luxemburgo (1E)H,
 * - - Lituania (1F)H,
 * - - Letonia (20)H,
 * - - Malta (21)H,
 * - - M�naco (22)H,
 * - - Rep�blica de Moldavia (23)H,
 * - - Macedonia (24)H,
 * - - Noruega (25)H,
 * - - Pa�ses Bajos (26)H,
 * - - Portugal (27)H,
 * - - Polonia (28)H,
 * - - Rumania (29)H,
 * - - San Marino (2A)H,
 * - - Federaci�n Rusa (2B)H,
 * - - Suecia (2C)H,
 * - - Eslovaquia (2D)H,
 * - - Eslovenia (2E)H,
 * - - Turkmenist�n (2F)H,
 * - - Turqu�a (30)H,
 * - - Ucrania (31)H,
 * - - Vaticano (32)H,
 * - - Yugoslavia (33)H,
 * - - RFU (34..FC)H,
 * - - Comunidad Europea (FD)H,
 * - - Resto de Europa (FE)H,
 * - - Resto del mundo (FF)H
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */

public class NationNumeric {
	

	private short nationNumeric;
	
	protected NationNumeric() {
	}
	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param datos array de bytes
	 */
	public NationNumeric(byte[] datos) {
		this.nationNumeric=datos[0];
	}
	/**
	 * Obtiene naci�n la referencia numerica de este.
	 * @return Nation
	 */
	public String getNationNumeric(){
		String cadena="";
		switch(this.nationNumeric){
			case 0x00:cadena="No hay informacion disponible (00)H";break;
			case 0x01:cadena="Austria (01)H";break;
			case 0x02:cadena="Albania (02)H";break;
			case 0x03:cadena="Andorra (03)H";break;
			case 0x04:cadena="Armenia (04)H";break;
			case 0x05:cadena="Azerbaiyan (05)H";break;
			case 0x06:cadena="Belgica (06)H";break;
			case 0x07:cadena="Bulgaria (07)H";break;
			case 0x08:cadena="Bosnia y Hercegovina (08)H";break;
			case 0x09:cadena="Bielorrusia (09)H";break;
			case 0x0a:cadena="Suiza (0A)H";break;
			case 0x0b:cadena="Chipre (0B)H";break;
			case 0x0c:cadena="Republica Checa (0C)H";break;
			case 0x0d:cadena="Alemania (0D)H";break;
			case 0x0e:cadena="Dinamarca (0E)H";break;
			case 0x0f:cadena="España (0F)H";break;
			case 0x10:cadena="Estonia (10)H";break;
			case 0x11:cadena="Francia (11)H";break;
			case 0x12:cadena="Finlandia (12)H";break;
			case 0x13:cadena="Liechtenstein (13)H";break;
			case 0x14:cadena="Islas Feroe (14)H";break;
			case 0x15:cadena="Reino Unido (15)H";break;
			case 0x16:cadena="Georgia (16)H";break;
			case 0x17:cadena="Grecia (17)H";break;
			case 0x18:cadena="Hungria (18)H,";break;
			case 0x19:cadena="Croacia (19)H";break;
			case 0x1a:cadena="Italia (1A)H";break;
			case 0x1b:cadena="poikl Irlanda (1B)H";break;
			case 0x1c:cadena="Islandia (1C)H";
			case 0x1d:cadena=" Kazajistan (1D)H";break;
			case 0x1e:cadena="Luxemburgo (1E)H";break;
			case 0x1f:cadena="Lituania (1F)H";break;
			case 0x20:cadena="Letonia (20)H";break;
			case 0x21:cadena="Malta (21)H";break;
			case 0x22:cadena="Monaco (22)H";break;
			case 0x23:cadena="Republica de Moldavia (23)H";break;
			case 0x24:cadena="Macedonia (24)H";break;
			case 0x25:cadena="Noruega (25)H";break;
			case 0x26:cadena="Paises Bajos (26)H";break;
			case 0x27:cadena="Portugal (27)H";break;
			case 0x28:cadena="Polonia (28)H";break;
			case 0x29:cadena="Rumania (29)H";break;
			case 0x2a:cadena="San Marino (2A)H";break;
			case 0x2b:cadena="Federacion Rusa (2B)H";break;
			case 0x2c:cadena="Suecia (2C)H";break;
			case 0x2d:cadena="Eslovaquia (2D)H";break;
			case 0x2e:cadena="Eslovenia (2E)H";break;
			case 0x2f:cadena="Turkmenistan (2F)H";break;
			case 0x30:cadena="Turquia (30)H";break;
			case 0x31:cadena="Ucrania (31)H";break;
			case 0x32:cadena="Vaticano (32)H";break;
			case 0x33:cadena="Yugoslavia (33)H";break;
			case (byte) 0xFC:cadena="RFU (34..FC)H";break;
			case (byte) 0xfd:cadena="Comunidad Europea (FD)H";break;
			case (byte) 0xfe:cadena="Resto de Europa (FE)H";break;
			case (byte) 0xff:cadena="Resto del mundo (FF)H";break;
		}
		
		return cadena;
	}

	/**
	 *
	 * @return
	 */
	public String getNationExtensionFile(){
		String cadena="";
		switch (this.nationNumeric) {
			case 0x00:
				cadena = "No hay informacion disponible (00)H";
				break;
			case 0x01:
				cadena = "DDD"; //"Austria (01)H";
				break;
			case 0x02:
				cadena = "DDD"; //"Albania (02)H";
				break;
			case 0x03:
				cadena = "TGD"; //"Andorra (03)H";
				break;
			case 0x04:
				cadena = "DDD"; //"Armenia (04)H";
				break;
			case 0x05:
				cadena = "DDD"; //"Azerbaiyan (05)H";
				break;
			case 0x06:
				cadena = "DDD"; //"Belgica (06)H";
				break;
			case 0x07:
				cadena = "DDD"; //"Bulgaria (07)H";
				break;
			case 0x08:
				cadena = "DDD"; //"Bosnia y Hercegovina (08)H";
				break;
			case 0x09:
				cadena = "DDD"; //"Bielorrusia (09)H";
				break;
			case 0x0a:
				cadena = "DDD"; //"Suiza (0A)H";
				break;
			case 0x0b:
				cadena = "DDD"; //"Chipre (0B)H";
				break;
			case 0x0c:
				cadena = "DDD"; //"Republica Checa (0C)H";
				break;
			case 0x0d:
				cadena = "DDD"; //"Alemania (0D)H";
				break;
			case 0x0e:
				cadena = "DDD"; //"Dinamarca (0E)H";
				break;
			case 0x0f:
				cadena = "TGD"; //"España (0F)H";
				break;
			case 0x10:
				cadena = "DDD"; //"Estonia (10)H";
				break;
			case 0x11:
				cadena = "C1B"; //"Francia (11)H";
				break;
			case 0x12:
				cadena = "DDD"; //"Finlandia (12)H";
				break;
			case 0x13:
				cadena = "DDD"; //"Liechtenstein (13)H";
				break;
			case 0x14:
				cadena = "DDD"; //"Islas Feroe (14)H";
				break;
			case 0x15:
				cadena = "DDD"; //"Reino Unido (15)H";
				break;
			case 0x16:
				cadena = "DDD"; //"Georgia (16)H";
				break;
			case 0x17:
				cadena = "DDD"; //"Grecia (17)H";
				break;
			case 0x18:
				cadena = "DDD"; //"Hungria (18)H,";
				break;
			case 0x19:
				cadena = "DDD"; //"Croacia (19)H";
				break;
			case 0x1a:
				cadena = "DDD"; //"Italia (1A)H";
				break;
			case 0x1b:
				cadena = "DDD"; //"poikl Irlanda (1B)H";
				break;
			case 0x1c:
				cadena = "DDD"; //"Islandia (1C)H";
			case 0x1d:
				cadena = "DDD"; //"Kazajistan (1D)H";
				break;
			case 0x1e:
				cadena = "DDD"; //"Luxemburgo (1E)H";
				break;
			case 0x1f:
				cadena = "DDD"; //"Lituania (1F)H";
				break;
			case 0x20:
				cadena = "DDD"; //"Letonia (20)H";
				break;
			case 0x21:
				cadena = "DDD"; //"Malta (21)H";
				break;
			case 0x22:
				cadena = "DDD"; //"Monaco (22)H";
				break;
			case 0x23:
				cadena = "DDD"; //"Republica de Moldavia (23)H";
				break;
			case 0x24:
				cadena = "DDD"; //"Macedonia (24)H";
				break;
			case 0x25:
				cadena = "DDD"; //"Noruega (25)H";
				break;
			case 0x26:
				cadena = "DDD"; //"Paises Bajos (26)H";
				break;
			case 0x27:
				cadena = "DDD"; //"Portugal (27)H";
				break;
			case 0x28:
				cadena = "DDD"; //"Polonia (28)H";
				break;
			case 0x29:
				cadena = "DDD"; //"Rumania (29)H";
				break;
			case 0x2a:
				cadena = "DDD"; //"San Marino (2A)H";
				break;
			case 0x2b:
				cadena = "DDD"; //"Federacion Rusa (2B)H";
				break;
			case 0x2c:
				cadena = "DDD"; //"Suecia (2C)H";
				break;
			case 0x2d:
				cadena = "DDD"; //"Eslovaquia (2D)H";
				break;
			case 0x2e:
				cadena = "DDD"; //"Eslovenia (2E)H";
				break;
			case 0x2f:
				cadena = "DDD"; //"Turkmenistan (2F)H";
				break;
			case 0x30:
				cadena = "DDD"; //"Turquia (30)H";
				break;
			case 0x31:
				cadena = "DDD"; //"Ucrania (31)H";
				break;
			case 0x32:
				cadena = "DDD";//"Vaticano (32)H";
				break;
			case 0x33:
				cadena = "DDD";//"Yugoslavia (33)H";
				break;
			case (byte) 0xFC:
				cadena = "DDD"; //"RFU (34..FC)H";
				break;
			case (byte) 0xfd:
				cadena = "DDD"; //"Comunidad Europea (FD)H";
				break;
			case (byte) 0xfe:
				cadena = "DDD"; //"Resto de Europa (FE)H";
				break;
			case (byte) 0xff:
				cadena = "DDD"; //"Resto del mundo (FF)H";
				break;
		}

		return cadena;
	}

	public String getNationWord(){
		String cadena="";
		switch (this.nationNumeric) {
			case 0x00:
				cadena = "No hay informacion disponible (00)H";
				break;
			case 0x01:
				cadena = "DDD"; //"Austria (01)H";
				break;
			case 0x02:
				cadena = "DDD"; //"Albania (02)H";
				break;
			case 0x03:
				cadena = "TGD"; //"Andorra (03)H";
				break;
			case 0x04:
				cadena = "DDD"; //"Armenia (04)H";
				break;
			case 0x05:
				cadena = "DDD"; //"Azerbaiyan (05)H";
				break;
			case 0x06:
				cadena = "DDD"; //"Belgica (06)H";
				break;
			case 0x07:
				cadena = "DDD"; //"Bulgaria (07)H";
				break;
			case 0x08:
				cadena = "DDD"; //"Bosnia y Hercegovina (08)H";
				break;
			case 0x09:
				cadena = "DDD"; //"Bielorrusia (09)H";
				break;
			case 0x0a:
				cadena = "DDD"; //"Suiza (0A)H";
				break;
			case 0x0b:
				cadena = "DDD"; //"Chipre (0B)H";
				break;
			case 0x0c:
				cadena = "DDD"; //"Republica Checa (0C)H";
				break;
			case 0x0d:
				cadena = "DDD"; //"Alemania (0D)H";
				break;
			case 0x0e:
				cadena = "DDD"; //"Dinamarca (0E)H";
				break;
			case 0x0f:
				cadena = "TGD"; //"España (0F)H";
				break;
			case 0x10:
				cadena = "DDD"; //"Estonia (10)H";
				break;
			case 0x11:
				cadena = "C1B"; //"Francia (11)H";
				break;
			case 0x12:
				cadena = "DDD"; //"Finlandia (12)H";
				break;
			case 0x13:
				cadena = "DDD"; //"Liechtenstein (13)H";
				break;
			case 0x14:
				cadena = "DDD"; //"Islas Feroe (14)H";
				break;
			case 0x15:
				cadena = "DDD"; //"Reino Unido (15)H";
				break;
			case 0x16:
				cadena = "DDD"; //"Georgia (16)H";
				break;
			case 0x17:
				cadena = "DDD"; //"Grecia (17)H";
				break;
			case 0x18:
				cadena = "DDD"; //"Hungria (18)H,";
				break;
			case 0x19:
				cadena = "DDD"; //"Croacia (19)H";
				break;
			case 0x1a:
				cadena = "DDD"; //"Italia (1A)H";
				break;
			case 0x1b:
				cadena = "DDD"; //"poikl Irlanda (1B)H";
				break;
			case 0x1c:
				cadena = "DDD"; //"Islandia (1C)H";
			case 0x1d:
				cadena = "DDD"; //"Kazajistan (1D)H";
				break;
			case 0x1e:
				cadena = "DDD"; //"Luxemburgo (1E)H";
				break;
			case 0x1f:
				cadena = "DDD"; //"Lituania (1F)H";
				break;
			case 0x20:
				cadena = "DDD"; //"Letonia (20)H";
				break;
			case 0x21:
				cadena = "DDD"; //"Malta (21)H";
				break;
			case 0x22:
				cadena = "DDD"; //"Monaco (22)H";
				break;
			case 0x23:
				cadena = "DDD"; //"Republica de Moldavia (23)H";
				break;
			case 0x24:
				cadena = "DDD"; //"Macedonia (24)H";
				break;
			case 0x25:
				cadena = "DDD"; //"Noruega (25)H";
				break;
			case 0x26:
				cadena = "DDD"; //"Paises Bajos (26)H";
				break;
			case 0x27:
				cadena = "DDD"; //"Portugal (27)H";
				break;
			case 0x28:
				cadena = "DDD"; //"Polonia (28)H";
				break;
			case 0x29:
				cadena = "DDD"; //"Rumania (29)H";
				break;
			case 0x2a:
				cadena = "DDD"; //"San Marino (2A)H";
				break;
			case 0x2b:
				cadena = "DDD"; //"Federacion Rusa (2B)H";
				break;
			case 0x2c:
				cadena = "DDD"; //"Suecia (2C)H";
				break;
			case 0x2d:
				cadena = "DDD"; //"Eslovaquia (2D)H";
				break;
			case 0x2e:
				cadena = "DDD"; //"Eslovenia (2E)H";
				break;
			case 0x2f:
				cadena = "DDD"; //"Turkmenistan (2F)H";
				break;
			case 0x30:
				cadena = "DDD"; //"Turquia (30)H";
				break;
			case 0x31:
				cadena = "DDD"; //"Ucrania (31)H";
				break;
			case 0x32:
				cadena = "DDD";//"Vaticano (32)H";
				break;
			case 0x33:
				cadena = "DDD";//"Yugoslavia (33)H";
				break;
			case (byte) 0xFC:
				cadena = "DDD"; //"RFU (34..FC)H";
				break;
			case (byte) 0xfd:
				cadena = "DDD"; //"Comunidad Europea (FD)H";
				break;
			case (byte) 0xfe:
				cadena = "DDD"; //"Resto de Europa (FE)H";
				break;
			case (byte) 0xff:
				cadena = "DDD"; //"Resto del mundo (FF)H";
				break;
		}

		return cadena;
	}
	
}
