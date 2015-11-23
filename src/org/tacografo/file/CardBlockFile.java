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

import org.tacografo.file.cardblockdriver.CardBlock;
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
import org.tacografo.file.error.ErrorFile;
import org.tacografo.file.vublock.Sid;
import org.tacografo.tiposdatos.Number;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase encargada de interpretar los bytes de los ficheros de una tarjeta del tacografo
 * he interpretarlo segun REGLAMENTO (CE) No 1360/2002 DE LA COMISIÓN de 13 de junio de 2002
 * en los diferentes bloques de datos segun dicho reglamento.
 * 
 * Nota:Donde podemos obtener directamente los bloques de memoria como propiedades de esta clase
 * para la devolucion de json sera igual que FileTGD solo devuelve nombre de fichero y la lista de bloque
 * en jormato json de la propiedad getJson().
 * 
 * @author Andres Carmona Gil
 * @version 0.0.1
 */
public class CardBlockFile {
	

	private String nameFile=null;
	// block file of driver card
	@JsonIgnore
	private CardIccIdentification icc = null;
	@JsonIgnore
	private CardChipIdentification ic = null;
	@JsonIgnore
	private DriverCardApplicationIdentification application_identification = null;
	@JsonIgnore
	private CardCertificate card_certificate = null;
	@JsonIgnore
	private MemberStateCertificate ca_certificate = null;
	@JsonIgnore
	private CardIdentification identification = null;
	@JsonIgnore
	private LastCardDownload card_download = null;
	@JsonIgnore
	private CardDrivingLicenceInformation driving_lincense_info = null;
	@JsonIgnore
	private CardEventData event_data = null;
	@JsonIgnore
	private CardFaultData fault_data = null;
	@JsonIgnore
	private CardDriverActivity driver_activity_data = null;
	@JsonIgnore
	private CardVehiclesUsed vehicles_used = null;
	@JsonIgnore
	private CardPlaceDailyWorkPeriod places = null;
	@JsonIgnore
	private CardCurrentUse current_usage = null;
	@JsonIgnore
	private CardControlActivityDataRecord control_activity_data = null;
	@JsonIgnore
	private SpecificConditionRecord specific_conditions = null;
	
	private boolean sid=false;
	
	/**
	 * Listado de <key,value> donde key=fid, value=cardBlock
	 */
	private HashMap<String, CardBlock> lista_bloque;

	public CardBlockFile() {

	}

	/**
	 * Constructor que leera los bytes del fichero pasado para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 * @throws ErrorFile 
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CardBlockFile(String nombre_fichero) throws ErrorFile {
		this.nameFile=nombre_fichero;
		this.lista_bloque = new HashMap();		
		factorizar_bloques(nombre_fichero);				
		this.asignarBloques();
		if (this.getLista_bloque().isEmpty()){		
			throw new ErrorFile("error archivo no tgd");
		}
	}

	/**
	 * Constructor que leera los bytes del fichero pasado como un inpurtStream para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CardBlockFile(InputStream is, int sizeFile, String filename) throws ErrorFile{
		this.nameFile=filename;
		this.lista_bloque = new HashMap();
		factorizar_bloques(is, sizeFile);
		this.asignarBloques();
		if (this.getLista_bloque().isEmpty()){
			throw new ErrorFile("error archivo no tgd");
		}

	}

	/**
	 * Constructor que leera los bytes del fichero pasado como array de bytes para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CardBlockFile(byte[] bytes) {
		this.lista_bloque = new HashMap();		
		this.lista_bloque = factorizar_bloques(bytes);
		if (!sid)
		this.asignarBloques();
	}
	/**
	 * Metodo encargado de asignar a cada propiedad que seran los diferentes bloques que 
	 * componen la estructura de fichero de una tarjeta(por ahora sin uso tanto de las propiedades
	 * como del metodo, posibles usos futuros) 
	 */
	private void asignarBloques() {
		
		this.icc = (CardIccIdentification) this.lista_bloque.get(Fid.EF_ICC
				.toString());
		this.ic = (CardChipIdentification) this.lista_bloque.get(Fid.EF_IC
				.toString());				
		this.application_identification = (DriverCardApplicationIdentification) this.lista_bloque
				.get(Fid.EF_APPLICATION_IDENTIFICATION.toString());				
		this.card_certificate = (CardCertificate) this.lista_bloque
				.get(Fid.EF_CARD_CERTIFICATE.toString());
		this.ca_certificate = (MemberStateCertificate) this.lista_bloque
				.get(Fid.EF_CA_CERTIFICATE.toString());
		this.identification = (CardIdentification) this.lista_bloque
				.get(Fid.EF_IDENTIFICATION.toString());
		this.card_download = (LastCardDownload) this.lista_bloque
				.get(Fid.EF_CARD_DOWNLOAD.toString());
		this.driving_lincense_info = (CardDrivingLicenceInformation) this.lista_bloque
				.get(Fid.EF_DRIVING_LICENSE_INFO.toString());
		this.event_data = (CardEventData) this.lista_bloque
				.get(Fid.EF_EVENTS_DATA.toString());
		this.fault_data = (CardFaultData) this.lista_bloque
				.get(Fid.EF_FAULTS_DATA.toString());
		this.driver_activity_data = (CardDriverActivity) this.lista_bloque
				.get(Fid.EF_DRIVER_ACTIVITY_DATA.toString());		
		this.vehicles_used = (CardVehiclesUsed) this.lista_bloque
				.get(Fid.EF_VEHICLES_USED.toString());
		this.vehicles_used.setNoOfCardVehicleRecords(this.application_identification.getNoOfCardVehicleRecords().getNoOfCardVehicleRecords());
		this.places = (CardPlaceDailyWorkPeriod) this.lista_bloque
				.get(Fid.EF_PLACES.toString());		
		if (this.places!=null)
		this.places.setNoOfCArdPlaceRecords(this.application_identification
				.getNoOfCardPlaceRecords().getNoOfCardPlaceRecords());
		this.current_usage = (CardCurrentUse) this.lista_bloque
				.get(Fid.EF_CURRENT_USAGE.toString());
		this.control_activity_data = (CardControlActivityDataRecord) this.lista_bloque
				.get(Fid.EF_CONTROL_ACTIVITY_DATA.toString());
		this.specific_conditions = (SpecificConditionRecord) this.lista_bloque
				.get(Fid.EF_SPECIFIC_CONDITIONS.toString());
	}
	/**
	 * Lectura de los bloques con formato :tag(fid)-longitud-value
	 * 
	 * @param entrada
	 * @throws IOException
	 * @throws ErrorFile ocurrido cuando no es un fichero tgd o falla en la lectura de algun bloque
	 * porque no encuentre el tag(fid)
	 */
	private void lectura_bloque(DataInputStream entrada) throws IOException, ErrorFile{
		boolean existe_fid=true;
		while (existe_fid) {
			// la lectura tiene que ser con readUnsignedShort debido a que
			// los fid c108 y c100
			// los detecta con signo y me los rellenas como ffffc108 y
			// ffffc100
			int fid = entrada.readUnsignedShort();	
			System.out.printf("%h\n",fid);
			existe_fid=this.existe_Fid(fid);			
			if (existe_fid) {
				// tipo de archivo 0 = bloque de dato -- 1 = certificado
				byte tipo = entrada.readByte();
				Integer longitud = Integer.valueOf(entrada.readChar());
				byte[] datos = new byte[longitud];
				
				entrada.read(datos, 0, longitud);
				// tipo de bloque
				if (tipo == 0) {
					CardBlock block = FactoriaBloques.getFactoria(fid,
							datos);
					if (block != null) {
						this.lista_bloque.put(block.getFID(), block);
					}

				}
			}else{
				throw new ErrorFile();
			}
		}
	}

	/**
	 * Se encarga de leer los bytes del fichero he introducirlo en un
	 * hasmap<FID,array bytes> los bloques bienen formado por TLV =
	 * tag(FID)-longitud-value
	 * @throws ErrorFile ocurrido cuando no es un fichero tgd o falla en la lectura de algun bloque
	 * porque no encuentre el tag(fid)
	 */
	private void factorizar_bloques(String nombre_fichero) throws ErrorFile {
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
	 * @throws ErrorFile 
	 **/
	@SuppressWarnings("unchecked")
	private void factorizar_bloques(InputStream is, int sizeFile) throws ErrorFile {
				
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
		Sid[] list_sid=Sid.values();
		
		boolean ok=false;				
		for (int i=0;i<list_fid.length;i++) {
				if (list_fid[i].getId()==fid){
					ok=true;
					i=list_fid.length;
				}				
		}
		for (int i=0;i<list_sid.length;i++) {
			if (list_sid[i].getId()==fid){
				ok=true;
				i=list_sid.length;
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
				// bloque fijo 76 de del SID de los archivo de vehiculos
				if (bytes[start]==76){
					this.sid=true;
					int sid= Number.getShort_16(Arrays.copyOfRange(bytes, start,
							start += 2));	
					System.out.println(sid);
					FactoriaBloques.getFactoria(sid, bytes);
				}else{
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
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	/**
	 * @return the icc
	 */
	public CardIccIdentification getIcc() {
		return icc;
	}

	/**
	 * @param icc
	 *            the icc to set
	 */
	public void setIcc(CardIccIdentification icc) {
		this.icc = icc;
	}

	/**
	 * @return the ic
	 */
	public CardChipIdentification getIc() {
		return ic;
	}

	/**
	 * @param ic
	 *            the ic to set
	 */
	public void setIc(CardChipIdentification ic) {
		this.ic = ic;
	}

	/**
	 * @return the application_identification
	 */
	public DriverCardApplicationIdentification getApplication_identification() {
		return application_identification;
	}

	/**
	 * @param application_identification
	 *            the application_identification to set
	 */
	public void setApplication_identification(
			DriverCardApplicationIdentification application_identification) {
		this.application_identification = application_identification;
	}

	/**
	 * @return the card_certificate
	 */
	public CardCertificate getCard_certificate() {
		return card_certificate;
	}

	/**
	 * @param card_certificate
	 *            the card_certificate to set
	 */
	public void setCard_certificate(CardCertificate card_certificate) {
		this.card_certificate = card_certificate;
	}

	/**
	 * @return the ca_certificate
	 */
	public MemberStateCertificate getCa_certificate() {
		return ca_certificate;
	}

	/**
	 * @param ca_certificate
	 *            the ca_certificate to set
	 */
	public void setCa_certificate(MemberStateCertificate ca_certificate) {
		this.ca_certificate = ca_certificate;
	}

	/**
	 * @return the identification
	 */
	public CardIdentification getIdentification() {
		return identification;
	}

	/**
	 * @param identification
	 *            the identification to set
	 */
	public void setIdentification(CardIdentification identification) {
		this.identification = identification;
	}

	/**
	 * @return the card_download
	 */
	public LastCardDownload getCard_download() {
		return card_download;
	}

	/**
	 * @param card_download
	 *            the card_download to set
	 */
	public void setCard_download(LastCardDownload card_download) {
		this.card_download = card_download;
	}

	/**
	 * @return the driving_lincense_info
	 */
	public CardDrivingLicenceInformation getDriving_lincense_info() {
		return driving_lincense_info;
	}

	/**
	 * @param driving_lincense_info
	 *            the driving_lincense_info to set
	 */
	public void setDriving_lincense_info(
			CardDrivingLicenceInformation driving_lincense_info) {
		this.driving_lincense_info = driving_lincense_info;
	}

	/**
	 * @return the event_data
	 */
	public CardEventData getEvent_data() {
		return event_data;
	}

	/**
	 * @param event_data
	 *            the event_data to set
	 */
	public void setEvent_data(CardEventData event_data) {
		this.event_data = event_data;
	}

	/**
	 * @return the fault_data
	 */
	public CardFaultData getFault_data() {
		return fault_data;
	}

	/**
	 * @param fault_data
	 *            the fault_data to set
	 */
	public void setFault_data(CardFaultData fault_data) {
		this.fault_data = fault_data;
	}

	/**
	 * @return the driver_activity_data
	 */
	public CardDriverActivity getDriver_activity_data() {
		return driver_activity_data;
	}

	/**
	 * @param driver_activity_data
	 *            the driver_activity_data to set
	 */
	public void setDriver_activity_data(CardDriverActivity driver_activity_data) {
		this.driver_activity_data = driver_activity_data;
	}

	/**
	 * @return the vehicles_used
	 */
	public CardVehiclesUsed getVehicles_used() {
		return vehicles_used;
	}

	/**
	 * @param vehicles_used
	 *            the vehicles_used to set
	 */
	public void setVehicles_used(CardVehiclesUsed vehicles_used) {
		this.vehicles_used = vehicles_used;
	}

	/**
	 * @return the places
	 */
	public CardPlaceDailyWorkPeriod getPlaces() {
		return places;
	}

	/**
	 * @param places
	 *            the places to set
	 */
	public void setPlaces(CardPlaceDailyWorkPeriod places) {
		this.places = places;
	}

	/**
	 * @return the current_usage
	 */
	public CardCurrentUse getCurrent_usage() {
		return current_usage;
	}

	/**
	 * @param current_usage
	 *            the current_usage to set
	 */
	public void setCurrent_usage(CardCurrentUse current_usage) {
		this.current_usage = current_usage;
	}

	/**
	 * @return the control_activity_data
	 */
	public CardControlActivityDataRecord getControl_activity_data() {
		return control_activity_data;
	}

	/**
	 * @param control_activity_data
	 *            the control_activity_data to set
	 */
	public void setControl_activity_data(
			CardControlActivityDataRecord control_activity_data) {
		this.control_activity_data = control_activity_data;
	}

	/**
	 * @return the specific_conditions
	 */
	public SpecificConditionRecord getSpecific_conditions() {
		return specific_conditions;
	}

	/**
	 * @param specific_conditions
	 *            the specific_conditions to set
	 */
	public void setSpecific_conditions(
			SpecificConditionRecord specific_conditions) {
		this.specific_conditions = specific_conditions;
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
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileTGD [icc=" + icc + ", ic=" + ic
				+ ", application_identification=" + application_identification
				+ ", card_certificate=" + card_certificate
				+ ", ca_certificate=" + ca_certificate + ", identification="
				+ identification + ", card_download=" + card_download
				+ ", driving_lincense_info=" + driving_lincense_info
				+ ", event_data=" + event_data + ", fault_data=" + fault_data
				+ ", driver_activity_data=" + driver_activity_data
				+ ", vehicles_used=" + vehicles_used + ", places=" + places
				+ ", current_usage=" + current_usage
				+ ", control_activity_data=" + control_activity_data
				+ ", specific_conditions=" + specific_conditions + "]";
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

}
