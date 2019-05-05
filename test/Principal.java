package test;


import java.io.File;
import java.io.IOException;

import org.tacografo.file.CardBlockFile;
import org.tacografo.file.FileTGD;
import org.tacografo.file.cardblockdriver.CardCertificate;
import org.tacografo.file.cardblockdriver.CardPlaceDailyWorkPeriod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

/**
 * 
 */

/**
 * @author Andres Carmona Gil
 *
 */
public class Principal {

	/**
	 * @param args
	 */
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub	
		//FileTGD a=new FileTGD("a.TGD");
		//FileTGD b=new FileTGD("EC_PK.bin");
		//FileTGD tgd=new FileTGD("D_Agafontsev_Nikolay_RUD00000179546_01.02.2016_555.DDD");
		//FileTGD tgd3=new FileTGD("C_E00398972Z000000_E_20091005_0907.TGD");
		//FileTGD tgd1=new FileTGD("C_0000000790345011_P_20151223_1623-ERROR.TGD");
		//FileTGD tgd=new FileTGD("C_0000000790345011_P_20151203_2006-ERROR.TGD");
		//FileTGD tgd2=new FileTGD("c-ERROR.TGD");
		FileTGD tgd1=new FileTGD("C_E48351403K000001_E_20150216_2117.TGD");
		System.out.println();
		//FileTGD ftgd=new FileTGD(".project");
		//System.out.println(ftgd.getJson());
		//System.out.println(ftgd.getVuBlockFile().geteActivity().toString());
		//FileTGD tgd=new FileTGD("7a.TGD");
		//FileBlockTGD fbt=new FileBlockTGD("C_E10580949Y000001_E_20150512_0940.TGD");
		//FileBlockTGD fbt=new FileBlockTGD("C_E10802572R000001_E_20150525_1026.TGD");
		//CardBlockFile fbt=new CardBlockFile("76a.TGD");
		//FileBlockTGD fbt=new FileBlockTGD("b.TGD");
		
		//if(tgd.getLista_bloque().isEmpty())
		//	System.err.println("lista vacia");
		//tgd=new FileTGD("a.TGD");
		//mostrar(tgd.getVehicles_used().toJson());
		//mostrar(tgd.getPlaces().toJson());
		//ObjectMapper mapper=new ObjectMapper();
		//System.out.println(mapper.writeValueAsString(tgd));			
		//mapper.writeValue(new File("tgd.json"),tgd);		
		//mostrar(tgd.getJson());
		//FileTGD otrotgd=mapper.readValue(str, FileTGD.class);
		//CardPlaceDailyWorkPeriod ca=mapper.readValue(str,CardPlaceDailyWorkPeriod.class);
		/*ObjectMapper mapper=new ObjectMapper();
		String str="";
		try {
			str=mapper.writeValueAsString(tgd.getPlaces());
			mostrar(str);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
					
		//mostrar(fbt.getDriver_activity_data().getActivityDailyRecords().toString());
		System.out.println("terminado");
	}
	
	private static FileTGD FileTGD() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void mostrar(String str){
		System.out.println(str);
	}
	
	
	
	
}

