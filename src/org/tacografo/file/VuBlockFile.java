/**
 * 
 */
package org.tacografo.file;

import java.util.Arrays;
import java.util.HashMap;

import org.tacografo.file.vublock.Activity;
import org.tacografo.file.vublock.EventsFaults;
import org.tacografo.file.vublock.Resumen;
import org.tacografo.file.vublock.Speed;
import org.tacografo.file.vublock.Technical;


/**
 * @author Andres Carmona Gil
 *
 */
public class VuBlockFile {

	
	private HashMap<String,Block> listBlock;
	
	
	public VuBlockFile(byte[] datos) throws Exception{
		int start=0;
		while(start<datos.length){	
					
			if(datos[start]==0x76){		
					start+=1;
					byte num=0;
					if(datos[start]<0){											
						num=(byte) (256-datos[start]&0xFF);
						System.out.println("trep :"+datos[start-1]+"  num: "+num+" start ("+datos[start]+"): "+start);
					}{
						num=datos[start];					
					}
					if (datos[start]==0xb4)
						System.out.println("-76");
					
					switch (datos[start]) {
					case 0x1:
						Resumen r=new Resumen(Arrays.copyOfRange(datos, start+1, datos.length));
						start+=r.getSize();
						System.out.println("trep 1 :"+start);
						break;
					case 0x2:
						Activity a=new Activity(Arrays.copyOfRange(datos, start+1, datos.length));						
						start+=a.getSize();
						System.out.println("trep 2 :"+start);
						break;
					case 0x3:					
						EventsFaults ef=new EventsFaults(Arrays.copyOfRange(datos, start+1, datos.length));																								
						start+=ef.getSize();
						System.out.println("trep 3 :"+start);
						break;
					case 0x4:						
						Speed s=new Speed(Arrays.copyOfRange(datos, start+1, datos.length));
						System.out.println("trep 4 :"+start);
						start+=s.getSize();
						break;
					case 0x5:						
						Technical t=new Technical(Arrays.copyOfRange(datos, start+1, datos.length));					
						start+=t.getSize();
						System.out.println("trep 5 :"+start);
						break;	
					
					}
					
			}else{
				
				//System.out.println(start);
				start+=1;	
				
			}
			
		}
	}
}
