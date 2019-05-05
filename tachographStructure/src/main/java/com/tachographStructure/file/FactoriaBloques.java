package com.tachographStructure.file;
import java.io.IOException;


import com.tachographStructure.file.driverCardBlock.CardBlock;
import com.tachographStructure.file.driverCardBlock.CardCertificate;
import com.tachographStructure.file.driverCardBlock.CardChipIdentification;
import com.tachographStructure.file.driverCardBlock.CardControlActivityDataRecord;
import com.tachographStructure.file.driverCardBlock.CardCurrentUse;
import com.tachographStructure.file.driverCardBlock.CardDriverActivity;
import com.tachographStructure.file.driverCardBlock.CardDrivingLicenceInformation;
import com.tachographStructure.file.driverCardBlock.CardEventData;
import com.tachographStructure.file.driverCardBlock.CardFaultData;
import com.tachographStructure.file.driverCardBlock.CardIccIdentification;
import com.tachographStructure.file.driverCardBlock.CardIdentification;
import com.tachographStructure.file.driverCardBlock.CardPlaceDailyWorkPeriod;
import com.tachographStructure.file.driverCardBlock.CardVehiclesUsed;
import com.tachographStructure.file.driverCardBlock.DriverCardApplicationIdentification;
import com.tachographStructure.file.driverCardBlock.Fid;
import com.tachographStructure.file.driverCardBlock.LastCardDownload;
import com.tachographStructure.file.driverCardBlock.MemberStateCertificate;
import com.tachographStructure.file.driverCardBlock.SpecificConditionRecord;
import com.tachographStructure.file.vuBlocks.Activity;
import com.tachographStructure.file.vuBlocks.EventsFaults;
import com.tachographStructure.file.vuBlocks.Resumen;
import com.tachographStructure.file.vuBlocks.Trep;
import com.tachographStructure.file.vuBlocks.Speed;
import com.tachographStructure.file.vuBlocks.Technical;


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
		
		Block block = null;
		switch (word) {	
			case 0x0002:	
			
				try {
					block = new CardIccIdentification(datos);
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
