/**
 * 
 */
package org.tacografo.file;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

import org.tacografo.file.cardblockdriver.*;
import org.tacografo.file.cardblockdriver.subblock.ExtendedSerialNumber;
import org.tacografo.file.error.ErrorFile;
import org.tacografo.file.vublock.Activity;
import org.tacografo.file.vublock.Resumen;
import org.tacografo.file.vublock.Speed;
import org.tacografo.file.vublock.Technical;
import org.tacografo.file.vublock.VuEventsFaults;
import org.tacografo.tiposdatos.Number;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase encargada de interpretar los bytes de los ficheros de una tarjeta del tacografo
 * he interpretarlo segun REGLAMENTO (CE) No 1360/2002 DE LA COMISI�N de 13 de junio de 2002
 * en los diferentes bloques de datos segun dicho reglamento.
 * 
 * Nota:Existe otra clase para tratar directamente con los bloque de memoria que son tratados como clases y pasados 
 * con propiedad FileBlockTGD.class.
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 */
public class FileTGD {

	

	private String nameFile=null;		
	
	/**
	 * Listado de <key,value> donde key=fid, value=cardBlock
	 */
	private HashMap<String, CardBlock> lista_bloque;

	public FileTGD() {

	}

	/**
	 * Constructor que leera los bytes del fichero pasado para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 * @throws Exception 
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FileTGD(String nombre_fichero) throws Exception {
		this.nameFile=nombre_fichero;
		this.lista_bloque = new HashMap();		
		factorizar_bloques(nombre_fichero);				
	
	}

	/**
	 * Constructor que leera los bytes del fichero pasado como un inpurtStream para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FileTGD(InputStream is, int sizeFile, String filename) throws Exception{
		this.nameFile=filename;
		this.lista_bloque = new HashMap();
		factorizar_bloques(is, sizeFile);
	
		if (this.getLista_bloque().isEmpty()){
			throw new ErrorFile("error archivo no tgd");
		}

	}

	/**
	 * Constructor que leera los bytes del fichero pasado como array de bytes para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FileTGD(byte[] bytes) {
		this.lista_bloque = new HashMap();		
		this.lista_bloque = factorizar_bloques(bytes);		
	
	}
	
	/**
	 * Lectura de los bloques con formato :tag(fid)-longitud-value
	 * 
	 * @param entrada
	 * @throws Exception 
	 * @throws ErrorFile ocurrido cuando no es un fichero tgd o falla en la lectura de algun bloque
	 * porque no encuentre el tag(fid)
	 */
	private void lectura_bloque(DataInputStream entrada) throws Exception{
		
		byte[] datos = new byte[entrada.available()];// = new byte[longitud];				
		entrada.read(datos,0,entrada.available());
		//int salto=100;
		//for (int i=0;i<datos.length;i++){
			//System.out.printf(Integer.toHexString(datos[i]));
			//System.out.printf(" ");
			//System.out.println(Integer.toBinaryString(bytes[i]));
			//if(datos[i]==0x76){
			//	System.out.println("index: "+(i+1) +"Trep: "+Integer.toHexString(datos[i+1])+" index 76: "+i);
				
			//}
		//}
		int start=0;
		while(start<datos.length){	
					
			if(datos[start]==0x76){		
					start+=1;					
					switch (datos[start]) {
					case 0x1:
						Resumen r=new Resumen(Arrays.copyOfRange(datos, start+1, datos.length));
						start+=r.getSize();
						System.out.println("trep 1");
						break;
					case 0x2:
						Activity a=new Activity(Arrays.copyOfRange(datos, start+1, datos.length));						
						start+=a.getSize();
						System.out.println("trep 2");
						break;
					case 0x3:
						VuEventsFaults ef=new VuEventsFaults(Arrays.copyOfRange(datos, start+1, datos.length));
						System.out.println("trep 3");
						start+=ef.getSize();
						break;
					case 0x4:
						Speed s=new Speed(Arrays.copyOfRange(datos, start+1, datos.length));
						System.out.println("trep 4");
						start+=s.getSize();
						break;
					case 0x5:
						Technical t=new Technical(Arrays.copyOfRange(datos, start+1, datos.length));
						System.out.println("trep 5");
						start+=t.getSize();
						break;
					default:
						start+=1;
						break;
					}				    				
			}else{
				start+=1;	
				
			}
			
		}
		
	}

	/**
	 * Se encarga de leer los bytes del fichero he introducirlo en un
	 * hasmap<FID,array bytes> los bloques bienen formado por TLV =
	 * tag(FID)-longitud-value
	 * @throws Exception 
	 */
	private void factorizar_bloques(String nombre_fichero) throws Exception {
		
		FileInputStream fis = null;		
		DataInputStream entrada = null;						
		try {
			fis = new FileInputStream(nombre_fichero);
			entrada = new DataInputStream(fis);
			//lectura de bloques siempre y cuando la lectura del fid exista
			this.lectura_bloque(entrada);
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (EOFException e) {
			// se produce cuando se llega al final del fichero
			//System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	/**
	 * Se encarga de leer los bytes del fichero he introducirlo en un
	 * hasmap<FID,array bytes> los bloques bienen formado por TLV =
	 * tag-longitud-value
	 * @throws Exception 
	 **/
	@SuppressWarnings("unchecked")
	private void factorizar_bloques(InputStream is, int sizeFile) throws Exception {
				
		DataInputStream entrada = null;
		@SuppressWarnings({ "unused", "rawtypes" })
		HashMap<String, CardBlock> lista = new HashMap();

		try {
			entrada = new DataInputStream(is);
			this.lectura_bloque(entrada);
			

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e.getMessage());		 
		}
		
	}
	
	/**
	 * Comprueba si el identificador de fichero existe
	 * @param fid
	 * @return the boolean
	 */
	private boolean existe_Fid(int fid){
		Fid[] list_fid=Fid.values();
		boolean ok=false;				
		for (int i=0;i<list_fid.length;i++) {
				if (list_fid[i].getId()==fid){
					ok=true;
					i=list_fid.length;
				}
				
		}
		return ok; 
	}
	/**
	 * Se encarga de leer los bytes del fichero he introducirlo en un
	 * hasmap<FID,array bytes> los bloques bienen formado por TLV =
	 * tag-longitud-value
	 */
	@SuppressWarnings("rawtypes")
	private HashMap factorizar_bloques(byte[] bytes) {
		@SuppressWarnings("unchecked")
		HashMap<String, CardBlock> lista = new HashMap();

		try {

			for (int start = 0; start < bytes.length; start++) {

				// la lectura tiene que ser con readUnsignedShort debido a que
				// los fid c108 y c100
				// los detecta con signo y me los rellenas como ffffc108 y
				// ffffc100
				int fid = Number.getShort_16(Arrays.copyOfRange(bytes, start,
						start += 2));
				// tipo de archivo 0 = bloque de dato -- 1 = certificado
				
				byte tipo = bytes[start];
				start += 1;

				Integer longitud = (int) Number.getShort_16(Arrays.copyOfRange(
						bytes, start, start += 2));
				byte[] datos = new byte[longitud];
				datos = Arrays.copyOfRange(bytes, start, start += longitud);

				if (tipo == 0) {
					CardBlock block = FactoriaBloques.getFactoria(fid, datos);
					if (block != null) {
						lista.put(block.getFID(), block);
					}

				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}


	/**
	 * Devuelve la lista_bloque <fid, cardblock>
	 * @return the lista_bloque
	 */
	public HashMap<String, CardBlock> getLista_bloque() {
		return lista_bloque;
	}

	/**
	 * Asigna lista_bloque <fid,cardblock>
	 * @param lista_bloque
	 *            the lista_bloque to set
	 */
	public void setLista_bloque(HashMap<String, CardBlock> lista_bloque) {
		this.lista_bloque = lista_bloque;
	}

	/**
	 * Devuelve el Nombre de fichero
	 * @return the nameFile
	 */

	
	public String getNameFile() {
		return nameFile;
	}

	/**
	 * Asignamos el nombre del fichero
	 * @param nameFile the nameFile to set
	 */
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}


	/**
	 * Mapeamos la clase fileTGD a json solo con las propiedades nameFile y lista_bloque
	 * @return the string json
	 */
	@JsonIgnore
	public String getJson() {
		ObjectMapper mapper = new ObjectMapper();
		String str = "";
		try {
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		return str;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileTGD [nameFile=" + nameFile + ", lista_bloque="
				+ lista_bloque + "]";
	}
	

}
