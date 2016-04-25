/**
 * 
 */
package org.tacografo.file;

import java.io.IOException;
import java.util.*;

import com.thingtrack.parse.*;
import com.thingtrack.parse.ActivityChangeInfo;
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
import org.tacografo.file.cardblockdriver.subblock.*;
import org.tacografo.tiposdatos.Number;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase encargada de interpretar los bytes de los ficheros de una tarjeta del tacografo
 * he interpretarlo segun REGLAMENTO (CE) No 1360/2002 DE LA COMISI�N de 13 de junio de 2002
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

	private Tacho tachos;

	private HashMap<String,Vehicle> vehicles;

	private HashMap<String,Driver> drivers;
	private String organizationId=null;
	/**
	 * @return the vehicles
	 */
	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}



	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(HashMap<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}



	/**
	 * @return the drivers
	 */
	public HashMap<String, Driver> getDrivers() {
		return drivers;
	}



	/**
	 * @param drivers the drivers to set
	 */
	public void setDrivers(HashMap<String, Driver> drivers) {
		this.drivers = drivers;
	}



	private boolean sid=false;
	
	/**
	 * Listado de <key,value> donde key=fid, value=cardBlock
	 */
	@JsonIgnore
	private HashMap<String, Block> listBlock;
	
	
	
	public CardBlockFile() {

	}

	
	
	/**
	 * Constructor que leera los bytes del fichero pasado como array de bytes para interpretar los
	 * datos y asignarlo a los bloque correspondientes
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CardBlockFile(byte[] bytes) throws Exception {
		HashMap<String, Block> lista = new HashMap();
		this.listBlock=new HashMap();
		try {
			int start=0;
			while( start < bytes.length){
				int fid = Number.getNumber(Arrays.copyOfRange(bytes, start, start += 2));								
				if(this.existe_Fid(fid)){
					byte tipo = bytes[start];
					start += 1;
					Integer longitud = (int) Number.getNumber(Arrays.copyOfRange(bytes, start, start += 2));
					byte[] datos = new byte[longitud];
					datos = Arrays.copyOfRange(bytes, start, start += longitud);
					// tipo de archivo 0 = bloque de dato -- 1 = certificado
					if (tipo == 0) {
						Block block = (Block) FactoriaBloques.getFactoria(fid, datos);
						if (block != null) {
							this.listBlock.put(block.getFID(), (Block) block);							
							
						}
					}
				}else{
					throw new Error("Block not found");
				}
				
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		this.asignarBloques();
		this.tacho();
		
		
	}
	public CardBlockFile(byte[] bytes,String organizationId,String namefile) throws Exception {
		this.nameFile=namefile;
		this.organizationId=organizationId;
		HashMap<String, Block> lista = new HashMap();
		this.listBlock=new HashMap();
		try {
			int start=0;
			while( start < bytes.length){
				int fid = Number.getNumber(Arrays.copyOfRange(bytes, start, start += 2));								
				if(this.existe_Fid(fid)){
					byte tipo = bytes[start];
					start += 1;
					Integer longitud = (int) Number.getNumber(Arrays.copyOfRange(bytes, start, start += 2));
					byte[] datos = new byte[longitud];
					datos = Arrays.copyOfRange(bytes, start, start += longitud);
					// tipo de archivo 0 = bloque de dato -- 1 = certificado
					if (tipo == 0) {
						Block block = (Block) FactoriaBloques.getFactoria(fid, datos);
						if (block != null) {
							this.listBlock.put(block.getFID(), (Block) block);														
						}
					}
				}else{
					throw new Error("Block not found");
				}
				
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		this.asignarBloques();
		this.tacho();
		
		
	}
	private void tacho() {
		this.drivers=new HashMap<String, Driver>();
		this.vehicles=new HashMap<String,Vehicle>();
		Driver d=new Driver(this.identification);
		Date limit = new Date(2000,1,1);		
		this.drivers.put(this.identification.getCardNumber().getDriverIdentification() + this.identification.getCardNumber().getDrivercardRenewalIndex() + this.identification.getCardNumber().getDrivercardReplacementIndex(),d);
		this.tachos=new Tacho();
		Iterator activitys=this.driver_activity_data.getActivity().entrySet().iterator();
		ActivityChangeInfo aci;
		// activitys por dia
		while(activitys.hasNext()){
			Map.Entry activity_File=(Map.Entry)activitys.next();
			Activity value_activity= (Activity) activity_File.getValue();
			// dates 1970-01-01
			if(value_activity.getDate().getTime()>0){
				Activity activity_Tacho=new Activity();
				activity_Tacho.setDate(value_activity.getDate());
				ArrayList<VehicleChangeInfo> list_vehicleUsed=this.vehicles_used.getListVehicle().get(activity_File.getKey());

				if (list_vehicleUsed != null) { // vehiculo con actividad en ese dia
					// varios vehiculos en un dia, recorro los vehiculo por dia para la actividad
					for (int j = 0; j < list_vehicleUsed.size(); j++) {
						VehicleChangeInfo vehicleUsed = list_vehicleUsed.get(j);
						activity_Tacho.setCardNumber(this.identification.getCardNumber().getDriverIdentification() +
													this.identification.getCardNumber().getDrivercardRenewalIndex() +
													this.identification.getCardNumber().getDrivercardReplacementIndex());
						activity_Tacho.setRegistration(vehicleUsed.getRegistration());
						activity_Tacho.setDistance(vehicleUsed.getDistance());
						activity_Tacho.getVehicles().add(vehicleUsed);
						// add activityChangeInfo for vehicle
						for (int i = 0; i < value_activity.getActivityChangeInfo().size(); i++) {
							aci = value_activity.getActivityChangeInfo().get(i);
							if (vehicleUsed.getFromDate().getTime() <= aci.getFromTime().getTime()) {
								if (aci.getFromTime().getTime() <= vehicleUsed.getToDate().getTime()) {
									if (aci.getType() != "indeterminado")
										activity_Tacho.getActivityChangeInfo().add(aci);
								}

							}
						}
						if(this.vehicles.get(vehicleUsed.getRegistration())==null){
							Vehicle v = new Vehicle();
							v.setRegistration(vehicleUsed.getRegistration());
							v.setActive(true);
							v.setDescription("Create vehicle");
							this.vehicles.put(vehicleUsed.getRegistration(),v);
						}
						if (this.places.getPlaces().get(activity_File.getKey()) != null) {
							ArrayList<Places> list_places = this.places.getPlaces().get(activity_File.getKey());
							if(list_places!=null){
								for (int k = 0; k < list_places.size(); k++) {
									if (vehicleUsed.getFromDate().equals(list_places.get(k).getFromDate()) || vehicleUsed.getToDate().equals(list_places.get(k).getToDate())){
										activity_Tacho.getPlaces().add(list_places.get(k));
									}
								}
							}
						}
					}
				} else { // activity without vehicle
					for (int i = 0; i < value_activity.getActivityChangeInfo().size(); i++) {
						aci = value_activity.getActivityChangeInfo().get(i);
						if (aci.getType() != "indeterminado")
							activity_Tacho.getActivityChangeInfo().add(aci);

					}
					ArrayList<Places> list_places = this.places.getPlaces().get(activity_File.getKey());
					if(list_places!=null){
						for (int k = 0; k < list_places.size(); k++) {
							activity_Tacho.getPlaces().add(list_places.get(k));
						}
					}

				}

				activity_Tacho.getFiles().add(this.nameFile);
				activity_Tacho.setOrganizationId(this.organizationId);
				activity_Tacho.setCardNumber(this.identification.getCardNumber().getDriverIdentification() +
											this.identification.getCardNumber().getDrivercardRenewalIndex() +
											this.identification.getCardNumber().getDrivercardReplacementIndex());
				this.tachos.getActivity().add(activity_Tacho);
			}


		}

		
	}



	/**
	 * Metodo encargado de asignar a cada propiedad que seran los diferentes bloques que 
	 * componen la estructura de fichero de una tarjeta(por ahora sin uso tanto de las propiedades
	 * como del metodo, posibles usos futuros) 
	 */
	private void asignarBloques() {
		
		this.icc = (CardIccIdentification) this.listBlock.get(Fid.EF_ICC
				.toString());
		this.ic = (CardChipIdentification) this.listBlock.get(Fid.EF_IC
				.toString());				
		this.application_identification = (DriverCardApplicationIdentification) this.listBlock
				.get(Fid.EF_APPLICATION_IDENTIFICATION.toString());				
		this.card_certificate = (CardCertificate) this.listBlock
				.get(Fid.EF_CARD_CERTIFICATE.toString());
		this.ca_certificate = (MemberStateCertificate) this.listBlock
				.get(Fid.EF_CA_CERTIFICATE.toString());
		this.identification = (CardIdentification) this.listBlock
				.get(Fid.EF_IDENTIFICATION.toString());
		this.card_download = (LastCardDownload) this.listBlock
				.get(Fid.EF_CARD_DOWNLOAD.toString());
		this.driving_lincense_info = (CardDrivingLicenceInformation) this.listBlock
				.get(Fid.EF_DRIVING_LICENSE_INFO.toString());
		this.event_data = (CardEventData) this.listBlock
				.get(Fid.EF_EVENTS_DATA.toString());
		this.fault_data = (CardFaultData) this.listBlock
				.get(Fid.EF_FAULTS_DATA.toString());
		this.driver_activity_data = (CardDriverActivity) this.listBlock
				.get(Fid.EF_DRIVER_ACTIVITY_DATA.toString());		
		this.vehicles_used = (CardVehiclesUsed) this.listBlock
				.get(Fid.EF_VEHICLES_USED.toString());
		this.vehicles_used.setNoOfCardVehicleRecords(this.application_identification.getNoOfCardVehicleRecords().getNoOfCardVehicleRecords());
		this.places = (CardPlaceDailyWorkPeriod) this.listBlock
				.get(Fid.EF_PLACES.toString());		
		if (this.places!=null)
		this.places.setNoOfCArdPlaceRecords(this.application_identification
				.getNoOfCardPlaceRecords().getNoOfCardPlaceRecords());
		this.current_usage = (CardCurrentUse) this.listBlock
				.get(Fid.EF_CURRENT_USAGE.toString());
		this.control_activity_data = (CardControlActivityDataRecord) this.listBlock
				.get(Fid.EF_CONTROL_ACTIVITY_DATA.toString());
		this.specific_conditions = (SpecificConditionRecord) this.listBlock
				.get(Fid.EF_SPECIFIC_CONDITIONS.toString());
		
		
		
	}



	
	/**
	 * Comprueba si el identificador de fichero existe
	 * @param fid
	 * @return the boolean
	 */
	private boolean existe_Fid(int fid) {
		Fid[] list_fid = Fid.values();
		boolean ok = false;
		for (int i = 0; i < list_fid.length; i++) {
			if (list_fid[i].getId() == fid) {
				ok = true;
				i = list_fid.length;
			}
		}

		return ok;
	}
	

	/**
	 * @return the tacho
	 */
	public Tacho getTachos() {
		return tachos;
	}



	/**
	 * @param tacho the tacho to set
	 */
	public void setTachos(Tacho tacho) {
		this.tachos = tacho;
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

	

	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardBlockFile [nameFile=" + nameFile + ", icc=" + icc + ", ic=" + ic + ", application_identification="
				+ application_identification + ", card_certificate=" + card_certificate + ", ca_certificate="
				+ ca_certificate + ", identification=" + identification + ", card_download=" + card_download
				+ ", driving_lincense_info=" + driving_lincense_info + ", event_data=" + event_data + ", fault_data="
				+ fault_data + ", driver_activity_data=" + driver_activity_data + ", vehicles_used=" + vehicles_used
				+ ", places=" + places + ", current_usage=" + current_usage + ", control_activity_data="
				+ control_activity_data + ", specific_conditions=" + specific_conditions + ", sid=" + sid
				+ ", listBlock=" + listBlock + "]";
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
	 * @return the sid
	 */
	public boolean isSid() {
		return sid;
	}



	/**
	 * @param sid the sid to set
	 */
	public void setSid(boolean sid) {
		this.sid = sid;
	}



	/**
	 * @return the listBlock
	 */
	public HashMap<String, Block> getListBlock() {
		return listBlock;
	}



	/**
	 * @param listBlock the listBlock to set
	 */
	public void setListBlock(HashMap<String, Block> listBlock) {
		this.listBlock = listBlock;
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
