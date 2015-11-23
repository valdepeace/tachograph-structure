package org.tacografo.file;
import java.io.IOException;





import java.io.UnsupportedEncodingException;

import org.tacografo.file.cardblockdriver.CardBlock;
import org.tacografo.file.cardblockdriver.CardBlockDriver;
import org.tacografo.file.cardblockdriver.CardCertificate;
import org.tacografo.file.cardblockdriver.CardChipIdentification;
import org.tacografo.file.cardblockdriver.CardControlActivityDataRecord;
import org.tacografo.file.cardblockdriver.CardCurrentUse;
import org.tacografo.file.cardblockdriver.CardDriverActivity;
import org.tacografo.file.cardblockdriver.CardDrivingLicenceInformation;
import org.tacografo.file.cardblockdriver.CardEventData;
import org.tacografo.file.cardblockdriver.CardFaultData;
import org.tacografo.file.cardblockdriver.CardIccIdentification;
import org.tacografo.file.cardblockdriver.CardIdentification;
import org.tacografo.file.cardblockdriver.CardPlaceDailyWorkPeriod;
import org.tacografo.file.cardblockdriver.CardVehiclesUsed;
import org.tacografo.file.cardblockdriver.DriverCardApplicationIdentification;
import org.tacografo.file.cardblockdriver.Fid;
import org.tacografo.file.cardblockdriver.LastCardDownload;
import org.tacografo.file.cardblockdriver.MemberStateCertificate;
import org.tacografo.file.cardblockdriver.SpecificConditionRecord;


/**
 * Factoria de bloques devuelve el fichero elemental del tacografo mapeado
 * a la clase java correspondiendo segun el fid(idendificador de fichero)
 *  
 * @author Andrés Carmona Gil
 * @version 0.0.1
 *
 */
public class FactoriaBloques {
	
	
	/**
	 * CardBlock segun fid(identificador de fichero
	 */
	public static CardBlock getFactoria(int word,byte[] datos) throws UnsupportedEncodingException  {
		CardBlockDriver bloque = null;
		
		switch (word) {	
			case 0x0002:	
				
				try {
					bloque=new CardIccIdentification(datos);
				} catch (IOException e) {				
					e.printStackTrace();
				}				
				bloque.setFID(Fid.EF_ICC.toString());				
				break;
			case 0x0005:				
				bloque=new CardChipIdentification(datos);				
				bloque.setFID(Fid.EF_IC.toString());				
				break;
			case 0x0501:				
				bloque= new DriverCardApplicationIdentification(datos);				
				bloque.setFID(Fid.EF_APPLICATION_IDENTIFICATION.toString());
				break;				
			case 0xc100:	
				bloque=new CardCertificate(datos);								
				bloque.setFID(Fid.EF_CARD_CERTIFICATE.toString());
				break;
			case 0xc108:
				bloque=new MemberStateCertificate(datos);								
				bloque.setFID(Fid.EF_CA_CERTIFICATE.toString());				
				break;		
			case 0x0520:
				bloque=new CardIdentification(datos);								
				bloque.setFID(Fid.EF_IDENTIFICATION.toString());								
				break;
			case 0x050E:					
				bloque = new LastCardDownload(datos);
				bloque.setFID(Fid.EF_CARD_DOWNLOAD.toString());
				break;
			case 0x0521:
				bloque=new CardDrivingLicenceInformation(datos);
				bloque.setFID(Fid.EF_DRIVING_LICENSE_INFO.toString());
				break;
			case 0x0502:
				bloque=new CardEventData(datos);
				bloque.setFID(Fid.EF_EVENTS_DATA.toString());				
				break;
			case 0x0503: // Faults data
				bloque=new CardFaultData(datos);
				bloque.setFID(Fid.EF_FAULTS_DATA.toString());
				break;
			case 0x0504: // Driver activity data
				bloque=new CardDriverActivity(datos);
				bloque.setFID(Fid.EF_DRIVER_ACTIVITY_DATA.toString());
				break;
			case 0x0505:// vehicles uses
				bloque=new CardVehiclesUsed(datos);
				bloque.setFID(Fid.EF_VEHICLES_USED.toString());				
				break;
			case 0x0506: // Places
				bloque=new CardPlaceDailyWorkPeriod(datos);
				bloque.setFID(Fid.EF_PLACES.toString());
				break;
			case 0x0507: // Currents usage
				bloque=new CardCurrentUse(datos);
				bloque.setFID(Fid.EF_CURRENT_USAGE.toString());
				break;
			case 0x0508: // Control activity data
				bloque=new CardControlActivityDataRecord(datos);
				bloque.setFID(Fid.EF_CONTROL_ACTIVITY_DATA.toString());				
				break;
			case 0x0522:
				bloque=new SpecificConditionRecord(datos);
				bloque.setFID(Fid.EF_SPECIFIC_CONDITIONS.toString());				
				break;
			case 0x7601:
				System.out.println("Resumen");
				break;
			case 0x7602:
				System.out.println("Actividades");
				break;
			case 0x7603:
				System.out.println("Incidentes y fallos");
				break;
			case 0x7604:
				System.out.println("Datos pormenorizados de la velocidad");
				break;
			case 0x7605:
				System.out.println("Datos técnicos");
				break;
			default:
			break;

		}
		if (bloque!=null){
			bloque.setDatos(datos);
			bloque.setSize(datos.length);
		}
		return (CardBlock) bloque;
		
	}

}
