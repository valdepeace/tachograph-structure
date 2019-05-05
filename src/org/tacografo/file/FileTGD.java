/**
 * 
 */
package org.tacografo.file;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tacografo.file.cardblockdriver.DriverCardApplicationIdentification;
import org.tacografo.file.certificate.CertificateContent;
import org.tacografo.file.certificate.KeyIdentifier;
import org.tacografo.file.certificate.PublicKey;
import org.tacografo.file.error.*;

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
	private VuBlockFile vuBlockFile;
	
	private CardBlockFile cardBlockFile;

	private Exception exceptionVerify;

	private String pathErcaKey;

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
		factorizar_bloques(is, sizeFile);			
	}

	/**
	 * Constructor que leera los bytes del fichero pasado como array de bytes para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FileTGD(byte[] bytes) throws Exception {				
		if (bytes[0]==0x76){			
			this.vuBlockFile=new VuBlockFile(bytes);
		}else{
			this.cardBlockFile=new CardBlockFile(bytes); 
		}
	
	}

	
	/**
	 * Lectura de los bloques con formato :tag(fid)-longitud-value
	 * 
	 * @param entrada
	 * @throws Exception 
	 * @throws ErrorFile ocurrido cuando no es un fichero tgd o falla en la lectura de algun bloque
	 * porque no encuentre el tag(fid)
	 */
	private void read_block(DataInputStream entrada) throws Exception{		
		byte[] datos = new byte[entrada.available()];// = new byte[longitud];				
		entrada.read(datos,0,entrada.available());
		if (datos[0]==0x76){			
			this.vuBlockFile=new VuBlockFile(datos);
		}else{
			this.cardBlockFile=new CardBlockFile(datos); 
		}
		try{
			this.verifyCertificate();
		}catch (IOException | NoSuchAlgorithmException | ExceptionSignatureContent | ExceptionSignatureHash
				|ExceptionContentCertificateHash | ExceptionContentCertificateOpen | ExceptionBlockRequired e){
			this.exceptionVerify = e;
		}
	}

	public void verifyCertificate() throws IOException, NoSuchAlgorithmException, ExceptionSignatureContent, ExceptionSignatureHash, ExceptionContentCertificateHash, ExceptionContentCertificateOpen, ExceptionBlockRequired {

		DriverCardApplicationIdentification dai;
		ArrayList<String> require_block;
		CertificateContent ca_certificateContent;
		CertificateContent device_certificateContent = null;
		PublicKey ercaKey = this.loadErcaKey();
		int start = 0;

		if (this.getVuBlockFile() != null) {
			ca_certificateContent = new CertificateContent(this.vuBlockFile.getResumen().getMemberStateCertificate(), ercaKey,"ca certificate");
			device_certificateContent = new CertificateContent(this.vuBlockFile.getResumen().getVUcertificate(), ca_certificateContent.getPublicKey(),"card certificate");
			//dai= (DriverCardApplicationIdentification) this.cardBlockFile.getListBlock().get("EF_APPLICATION_IDENTIFICATION");
			require_block = new ArrayList<String>(Arrays.asList(
					"VU_RESUMEN",
					"VU_ACTIVITY",
					"VU_EVENT_FAULT",
					"VU_SPEED",
					"VU_TECHNICAL"
			));
		} else {
			ca_certificateContent = new CertificateContent(this.cardBlockFile.getCa_certificate().getCertificate(), ercaKey,"ca certificate");
			device_certificateContent = new CertificateContent(this.cardBlockFile.getCard_certificate().getCertificate(), ca_certificateContent.getPublicKey(),"vu certificate");
			//dai= (DriverCardApplicationIdentification) this.cardBlockFile.getListBlock().get("EF_APPLICATION_IDENTIFICATION");
			require_block = new ArrayList<String>(Arrays.asList(
					"EF_APPLICATION_IDENTIFICATION",
					//"EF_CARD_CERTIFICATE",
					//"EF_CA_CERTIFICATE",
					"EF_IDENTIFICATION",
					//"EF_DRIVING_LICENSE_INFO",
					"EF_EVENTS_DATA",
					"EF_FAULTS_DATA",
					"EF_DRIVER_ACTIVITY_DATA",
					"EF_VEHICLES_USED",
					"EF_PLACES",
					"EF_CONTROL_ACTIVITY_DATA",
					"EF_SPECIFIC_CONDITIONS"
			));

		}
		byte[] bytes;

		for (int i = 0; i < require_block.size(); i++) {
			if (this.vuBlockFile != null) {
				if (require_block.get(i).equals("VU_ACTIVITY")) {
					for (int j = 0; j < this.vuBlockFile.getListActivity().getActivity().size(); j++) {
						bytes = this.vuBlockFile.getListActivity().getActivity().get(j).getDatos();
						// error verificacion signature del bloque
						this.vuBlockFile.getListActivity().getActivity().get(j).getSignature().verify(bytes, device_certificateContent.getPublicKey(),require_block.get(i));
					}
				} else {
					bytes = Arrays.copyOfRange(this.vuBlockFile.getListBlock().get(require_block.get(i)).getDatos(), 0, this.vuBlockFile.getListBlock().get(require_block.get(i)).getDatos().length);
					// error verificacion signature del bloque
					this.vuBlockFile.getListBlock().get(require_block.get(i)).getSignature().verify(bytes, device_certificateContent.getPublicKey(),require_block.get(i));
					if (this.vuBlockFile.getListBlock().get(require_block.get(i)) == null) {
						Throwable cause = new Throwable("Block required " + require_block.get(i));
						throw new ExceptionBlockRequired("Block required " + require_block.get(i), cause);
					}
				}
			} else {
				if (this.cardBlockFile.getListBlock().get(require_block.get(i)) == null) {
					Throwable cause = new Throwable("Block required " + require_block.get(i));
					throw new ExceptionBlockRequired("Block required " + require_block.get(i), cause);
				} else {
					// error verificacion signature del bloque

					this.cardBlockFile.getListBlock().get(require_block.get(i))
							.getSignature()
							.verify(this.cardBlockFile.getListBlock().get(require_block.get(i)).getDatos(),	device_certificateContent.getPublicKey(), require_block.get(i));
					if (this.cardBlockFile.getListBlock().get(require_block.get(i)).getDatos().length < Sizes.valueOf(require_block.get(i)).getMin() ||
							this.cardBlockFile.getListBlock().get(require_block.get(i)).getDatos().length > Sizes.valueOf(require_block.get(i)).getMax())
						throw new Error("Error length bytes in block " + require_block.get(i));
				}
			}
		}


	}

	private PublicKey loadErcaKey() throws IOException {
		//load EC_PK public key
		int start=0;
		File f;
		if(this.pathErcaKey==null){
			f = new File("EC_PK.bin");
		}else{
			f = new File(this.pathErcaKey);
		}
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		byte[] ercaKey_bytes = new byte[(int) f.length()];
		dis.readFully(ercaKey_bytes);
		dis.close();
		KeyIdentifier ercaKey_Keyidentifier = new KeyIdentifier(Arrays.copyOfRange(ercaKey_bytes, start, start+=8));
		PublicKey ercaKey=new PublicKey(Arrays.copyOfRange(ercaKey_bytes, start, start+=136));
		return ercaKey;
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
			this.read_block(entrada);
			
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
		try {
			entrada = new DataInputStream(is);
			this.read_block(entrada);		
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e.getMessage());		 
		}
		
	}

	/**
	 * @return the vuBlockFile
	 */
	public VuBlockFile getVuBlockFile() {
		return vuBlockFile;
	}

	/**
	 * @param vuBlockFile the vuBlockFile to set
	 */
	public void setVuBlockFile(VuBlockFile vuBlockFile) {
		this.vuBlockFile = vuBlockFile;
	}

	/**
	 * @return the cardBlockFile
	 */
	public CardBlockFile getCardBlockFile() {
		return cardBlockFile;
	}

	/**
	 * @param cardBlockFile the cardBlockFile to set
	 */
	public void setCardBlockFile(CardBlockFile cardBlockFile) {
		this.cardBlockFile = cardBlockFile;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileTGD [nameFile=" + nameFile + ", vuBlockFile=" + vuBlockFile + ", cardBlockFile=" + cardBlockFile
				+ "]";
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

	

	

}
