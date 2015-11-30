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
import org.tacografo.file.vublock.Activity;
import org.tacografo.file.vublock.EventsFaults;
import org.tacografo.file.vublock.Resumen;
import org.tacografo.file.vublock.Trep;
import org.tacografo.file.vublock.Speed;
import org.tacografo.file.vublock.Technical;



/**
 * Factoria de bloques devuelve el fichero elemental del tacografo mapeado
 * a la clase java correspondiendo segun el fid(idendificador de fichero)
 *  
 * @author Andr�s Carmona Gil
 * @version 0.0.1
 *
 */
public class FactoriaBloques {
	
	
	/**
	 * CardBlock segun fid(identificador de fichero
	 * @throws Exception 
	 */
	public static Block getFactoria(int word,byte[] datos) throws Exception  {
		
		Block block=null;
		switch (word) {	
			case 0x0002:	
			
				try {
					block=new CardIccIdentification(datos);
				} catch (IOException e) {				
					e.printStackTrace();
				}				
				block.setFID(Fid.EF_ICC.toString());				
				break;
			case 0x0005:				
				block=new CardChipIdentification(datos);				
				block.setFID(Fid.EF_IC.toString());				
				break;
			case 0x0501:				
				block= new DriverCardApplicationIdentification(datos);				
				block.setFID(Fid.EF_APPLICATION_IDENTIFICATION.toString());
				break;				
			case 0xc100:	
				block=new CardCertificate(datos);								
				block.setFID(Fid.EF_CARD_CERTIFICATE.toString());
				break;
			case 0xc108:
				block=new MemberStateCertificate(datos);								
				block.setFID(Fid.EF_CA_CERTIFICATE.toString());				
				break;		
			case 0x0520:
				block=new CardIdentification(datos);								
				block.setFID(Fid.EF_IDENTIFICATION.toString());								
				break;
			case 0x050E:					
				block = new LastCardDownload(datos);
				block.setFID(Fid.EF_CARD_DOWNLOAD.toString());
				break;
			case 0x0521:
				block=new CardDrivingLicenceInformation(datos);
				block.setFID(Fid.EF_DRIVING_LICENSE_INFO.toString());
				break;
			case 0x0502:
				block=new CardEventData(datos);
				block.setFID(Fid.EF_EVENTS_DATA.toString());				
				break;
			case 0x0503: // Faults data
				block=new CardFaultData(datos);
				block.setFID(Fid.EF_FAULTS_DATA.toString());
				break;
			case 0x0504: // Driver activity data
				block=new CardDriverActivity(datos);
				block.setFID(Fid.EF_DRIVER_ACTIVITY_DATA.toString());
				break;
			case 0x0505:// vehicles uses
				block=new CardVehiclesUsed(datos);
				block.setFID(Fid.EF_VEHICLES_USED.toString());				
				break;
			case 0x0506: // Places
				block=new CardPlaceDailyWorkPeriod(datos);
				block.setFID(Fid.EF_PLACES.toString());
				break;
			case 0x0507: // Currents usage
				block=new CardCurrentUse(datos);
				block.setFID(Fid.EF_CURRENT_USAGE.toString());
				break;
			case 0x0508: // Control activity data
				block=new CardControlActivityDataRecord(datos);
				block.setFID(Fid.EF_CONTROL_ACTIVITY_DATA.toString());				
				break;
			case 0x0522:
				block=new SpecificConditionRecord(datos);
				block.setFID(Fid.EF_SPECIFIC_CONDITIONS.toString());				
				break;
			case 0x7601:
				block=new Resumen(datos);
				block.setTRED(Trep.VU_RESUMEN.toString());				
				break;
			case 0x7602:
				block=new Activity(datos);
				block.setTRED(Trep.VU_ACTIVITY.toString());				
				break;
			case 0x7603:
				block=new EventsFaults(datos);
				block.setTRED(Trep.VU_EVENT_FAULT.toString());								
				break;
			case 0x7604:
				block=new Speed(datos);
				block.setTRED(Trep.VU_SPEED.toString());				
				break;
			case 0x7605:
				block=new Technical(datos);
				block.setTRED(Trep.VU_TECHNICAL.toString());				
				break;
			default:
			break;
		}
		if (block!=null){			
			block.setDatos(datos);
			if(block.getSize()==0){
				block.setSize(datos.length);	
			}
			
		}
		return block;
				
	}

}
