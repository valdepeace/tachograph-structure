/**
 * 
 */
package org.tacografo.file.cardblockdriver.subblock;



/**
 * 2.67. ManufacturerCode Código que identifica a un fabricante.
 * ManufacturerCode ::= INTEGER(0..255) Asignación de valor: ¡00¡H No hay
 * información disponible ¡01¡H Valor reservado ¡02¡H .. ¡0F¡H Reservado para
 * uso futuro ¡10¡H ACTIA ¡11¡H .. ¡17¡H Reservado para fabricantes cuyo nombre
 * comience por ¡A¡ ¡18¡H .. ¡1F¡H Reservado para fabricantes cuyo nombre
 * comience por ¡B¡ ¡20¡H .. ¡27¡H Reservado para fabricantes cuyo nombre
 * comience por ¡C¡ ¡28¡H .. ¡2F¡H Reservado para fabricantes cuyo nombre
 * comience por ¡D¡ ¡30¡H .. ¡37¡H Reservado para fabricantes cuyo nombre
 * comience por ¡E¡ ¡38¡H .. ¡3F¡H Reservado para fabricantes cuyo nombre
 * comience por ¡F¡ ¡40¡H Giesecke & Devrient GmbH ¡41¡H GEM plus ¡42¡H .. ¡47¡H
 * Reservado para fabricantes cuyo nombre comience por ¡G¡ ¡48¡H .. ¡4F¡H
 * Reservado para fabricantes cuyo nombre comience por ¡H¡ ¡50¡H .. ¡57¡H
 * Reservado para fabricantes cuyo nombre comience por ¡I¡ ¡58¡H .. ¡5F¡H
 * Reservado para fabricantes cuyo nombre comience por ¡J¡ ¡60¡H .. ¡67¡H
 * Reservado para fabricantes cuyo nombre comience por ¡K¡ ¡68¡H .. ¡6F¡H
 * Reservado para fabricantes cuyo nombre comience por ¡L¡ ¡70¡H .. ¡77¡H
 * Reservado para fabricantes cuyo nombre comience por ¡M¡ ¡78¡H .. ¡7F¡H
 * Reservado para fabricantes cuyo nombre comience por ¡N¡ ¡80¡H OSCARD ¡81¡H ..
 * ¡87¡H Reservado para fabricantes cuyo nombre comience por ¡O¡ ¡88¡H .. ¡8F¡H
 * Reservado para fabricantes cuyo nombre comience por ¡P¡ ¡90¡H .. ¡97¡H
 * Reservado para fabricantes cuyo nombre comience por ¡Q¡ ¡98¡H .. ¡9F¡H
 * Reservado para fabricantes cuyo nombre comience por ¡R¡ ¡A0¡H SETEC ¡A1¡H
 * SIEMENS VDO ¡A2¡H STONERIDGE ¡A3¡H .. ¡A7¡H Reservado para fabricantes cuyo
 * nombre comience por ¡S¡ ¡AA¡H TACHOCONTROL ¡AB¡H .. ¡AF¡H Reservado para
 * fabricantes cuyo nombre comience por ¡T¡ ¡B0¡H .. ¡B7¡H Reservado para
 * fabricantes cuyo nombre comience por ¡U¡ ¡B8¡H .. ¡BF¡H Reservado para
 * fabricantes cuyo nombre comience por ¡V¡ ¡C0¡H .. ¡C7¡H Reservado para
 * fabricantes cuyo nombre comience por ¡W¡ ¡C8¡H .. ¡CF¡H Reservado para
 * fabricantes cuyo nombre comience por ¡X¡ ¡D0¡H .. ¡D7¡H Reservado para
 * fabricantes cuyo nombre comience por ¡Y¡ ¡D8¡H .. ¡DF¡H Reservado para
 * fabricantes cuyo nombre comience por ¡Z¡
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 *
 */

public class ManufacturerCode {

	private Integer code;

	/**
	 * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta 
	 * segun  el tipo de propiedad.
	 * @param bytes
	 */
	public ManufacturerCode(byte[] bytes) {
		this.code =(int) bytes[0];			

	}
	
	public ManufacturerCode(){}
	/**
	 * Obtiene el fabricante de tacógrafo.
	 * @return manufactureCode
	 */
	public String getManufactureCode() {

		switch (this.code) {
		case 0x00:
			return ("No information available");
		case 0x01:
			return ("Reserved value");
		case 0x10:
			return "Actia S.A.";
		case 0x12:
			return "Austria Card Plastikkarten und Ausweissysteme GmbH";
		case 0x13:
			return "Agencija za komercijalnu djelatnost d.o.o (AKD)";
		case 0x20:
			return "CETIS d.d.";
		case 0x21:
			return "certSIGN";
		case 0x22:
			return "RUE Cryptotech";
		case 0x30:
			return String.format("%s (formerly %s)", "Sdu Identification B.V.",
					"Enschedé/Sdu B.V.");
		case 0x32:
			return "EFKON AG.";
		case 0x38:
			return "Fábrica Nacional de Moneda y Timbre";
		case 0x40:
			return "Giesecke & Devrient GmbH";
		case 0x43:
			return "Giesecke & Devrient GB Ltd.";
		case 0x44:
			return "Giesecke & Devrient sa/nv";
		case 0x48:
			return "Hungarian Banknote Printing Co. Ltd.";
		case 0x50:
			return "Imprimerie Nationale";
		case 0x51:
			return "Imprensa Nacional-Casa da Moeda, SA";
		case 0x52:
			return "InfoCamere S.C.p.A";
		case 0x81:
			return String.format("%s  (formerly %s and before that %s)",
					"Morpho e-documents", "Sagem Orga",
					"ORGA Kartensysteme GmbH");
		case 0x82:
			return "ORGA Zelenograd ZAO";
		case 0x88:
			return String.format("%s (formerly %s)",
					"Asseco Czech Republic a.s.", "PVT a.s.");
		case 0x89:
			return "Polska Wytwórnia Papierów Wartosciowych S.A. - PWPW S.A.";
		case 0xA1:
			return String.format("%s (formerly %s)",
					"Continental Automotive GmbH",
					"Siemens AG - Siemens VDO Automotive");
		case 0xA2:
			return "Stoneridge Electronics AB";
		case 0xA3:
			return String.format("%s  (formerly %s and before that %s)",
					"Gemalto", "Schlumberger SEMA, Axalto");
		case 0xA4:
			return "3M Security Printing and Systems Ltd.";
		case 0xD8:
			return "Union of Chambers and Commodity Exchanges of Turkey - TOBB";
		case 0xAB:
			return "T-Systems International GmbH";
		case 0xAC:
			return "Trüb AG";
		case 0xAD:
			return "Trüb Baltic AS";
		case 0xAE:
			return "TEMPEST a.s.";
		case 0xAF:
			return "Trueb - DEMAX PLC";
		}
		return String.format(
				"Unknown Manufacturer %s or equipment not type approved", code);

	}
	
}
