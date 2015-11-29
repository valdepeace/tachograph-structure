package org.tacografo.file.vublock;

/**
 * @author Andres Carmona Gil
 *
 */

public enum Sizes {
	
	
	TREP_1(491),     							//Resumen
		MEMBERSTATECERTIFICATE(194),			//Certificados de seguridad de la VU
		VUCERTIFICATE(194),				
		VEHICLEINDENTIFICATIONNUMBER(17), 		//Identificacion de vehiculo
		VEHICLEREGISTRATIONIDENTIFICATION_TREP1(15),
			VEHICLEREGISTRATIONNATION_TREP1(1),
			VEHICLEREGISTRATIONNUMBER_TREP1(14),
		CURRENTDATETIME(4),						//fecha y hora actuales de la VU
		VUDOWNLOADABLEPERIOD(8),				//periodo transferible
			MINDOWNLOADLETIME(4),
			MAXDOWNLOADLETIME(4),
		CARDSLOTSSTATUS(1),						
		VUDOWNLOADACITIVITYDATA(58),            //Transferencia anterior de la VU
			DOWNLOADINGTIME(4),
			//FULLCARDNUMBER YA DECLARADO
			COMPANYORWORKSHOPNAME(36),			
		VUCOMPANYLOCKSDATA(99),					//Todos los bloqueos de empresa almacenado.
			NOOFLOCKS(1),						//Si la seccion esta vacia, tan solo se envia noOfLocks=0
			VUCOMPANYLOCKSRECORD(98),
				LOCKINTITME(4),
				LOCKOUTTIME(4),
				COMPANYNAME(36),
				COMPANYADDRESS(36),
				COMPANYCARDNUMBER(18),
		VUCONTROLACTIVITYDATA(32),				//Todos los registros de control almacenado en la vu.
			NOOFCONTROLS(1),					//Si la seccion esta vacia, tan solo se envia noOfControls=0
			VUCONTROLACTIVITYRECORD(31),
				CONTROLTYPE(1),
				CONTROLTIME(4),
				CONTROLCARDNUMBER(18),
				DOWNLOADPERIODBEGINTIME(4),
				DOWNLOADPERIODENDTIME(4),
		SIGNATURE_TREP1(128),							//Firma RSA de todos los datos(except los certificados),desde el 
												//VehicleIdentificationNumber hasta el ultimo byte del ultimo VuControlActivityRecord
	
	
	TREP_2(7),									//Actividades
		TIMEREAL(4),							//Fecha correspondiente al dia cuyos datos se transfieren
		ODOMETERVALUEMINDNIGHT(3),				//Lectura del cuantakilometros al terminar el dia cuyos datos se transfieren
		VUCARDWDATA(131),						//Datos sobre los ciclos de insercion y extraccion de tarjetas.
			NOOFVUCARDIWRECORDS(2),				//-Si esta seccion no contiene datos disponibles, tan solo se envia noOfVuCardIWRecords=0
			VUCARDIWRECORD(129),				//-Cuando un registro VuCardIWRecord es anterior a las 00:00(la tarjeta se insert� el dia de antes
			CARDHOLDERNAME(72),					//o posterior a las 24:00(la tarjeta se extrajo el dia despues), debera constar en los dias.
				HOLDERSURNAME(36),
				HOLDERFIRSTNAMES(36),
			FULLCARDNUMBER(18),
			CARDEXPIRYTDATE(4),
			CARDINSERTIONTIME(4),
			VEHICLEODOMETERVALUEATINSERTION(3),
			CARDSLOTNUMBER(1),
			CARDWITHDRAWALTIME_VUCARDWDATA(4),
			VEHICLEODOMETERVALUEATWITHDRAWAL(3),
			PREVIOUSVEHICLEINFO(19),
				VEHICLEREGISTRATIONIDENTIFICATION_TREP2(15),
				//VEHICLEREGISTRATIONNUMBER_TREP2(14),
				CARDWITHDRAWALTIME_VEHICLEODOMETER(4),
			MANUALINPUTFLAG(1),
		VUACTIVITYDAILYDATA(4),					//Estado de la ranura a las 00:00 y cambios de actividad registrados
			NOOFACTIVITYCHANGES(2),				//durante el dia cuyos datos se transfieren
			ACTIVITYCHANGEINFO(2),
		VUPLACEDAILYWORKPERIODDATA(29),			//Datos relativos a lugares y registros durante el dia cuyos datos se
			NOOFPLACERECORDS(2),				//transfieren. Si la seccion esta vacia tan solo se envia noOfPlaceRecords=0
			VUPLACEDAILYWORKPERIODRECORD(28),
				FULLCARDNUMBER_TREP2(18),
				PLACERECORD(10),
					ENTRYTIME_PLACERECORD(4),
					ENTRYTYPEDAILYWORKPERIOD(1),
					DAILYWORKPERIODCOUNTRY(1),
					DAILYWORKPERIODREGION(1),
					VIHICLEODOMETERVALUE(3),
		VUSPECIFICCONDITIONDATA(7),
			NOOFSPECIFICCONDITIONSRECORDS(2),
			SPECIFICCONDITIONRECORD(5),
				ENTRYTIME_SPECIFICCIONDITIONRECORD(4),
				SPECIFICCONDITIONTYPE(1),
		SIGNATURE_TREP2(128),					//Firma RSA de todos los datos, desde TimeReal hasta el ultimo byte
												//del ultimo registro de una condicion especifica
	
		TREP_3(0),								//Indices y fallos
			VUFAULTDATA(83),					//Todos los fallos almacenados o en curso en la VU. Si la seccion esta vacia,
				NOOFVUFAULTS(1),				//tan solo se envia noOfVuFaults=0
				VUFAULTRECORD(82),
					FAULTTYPE(1),
					FAULTRECORDPURPOSE(1),
					FAULTBEGINTIME(4),
					FAULTENDTIME(4),
					CARDNUMBERDRIVERSLOTBEGIN_FAULT(18),
					CARDNUMBERCODRIVERSLOTBEGIN_FAULT(18),
					CARDNUMBERDRIVERSLOTEND_FAULT(18),
					CARDNUMBERCODRIVERSLOTEND_FAULT(18),
				VUEVENTDATA(84),				//Todos los incidentes(excepto los de exceso de velocidad) almacenados o en curso
					NOOFVUEVENTS(1),			// en la VU. Si la seccion esta vacia, tan solo se envia noOfVuEvents=0
					VUEVENTRECORD(83),
						EVENTTYPE(1),
						EVENTRECORDPURPOSE(1),
						EVENTBEGINTIME(4),
						EVENTENDTIME(4),
						CARDNUMBERDRIVERSLOTBEGIN_EVENT(18),
						CARDNUMBERCODRIVERSLOTBEGIN_EVENT(18),
						CARDNUMBERDRIVERSLOTEND_EVENT(18),
						CARDNUMBERCODRIVERSLOTEND_EVENT(18),
						//SIMILAREVENTSNUMBER(1),
				VUOVERSPEEDINGCONTROLDATA(9),	//Datos relativos al ultimo control del exceso de velocidad(si no hay datos 
					LASTOVERSPEEDCONTROLTIME(4),//se indica un valor por defecto)
					FIRSTOVERSPEEDSINCE(4),
					NUMBEROFOVERSPEEDSINCE(1),
				VUOVERSPEEDINGEVENTDATA(32),	//Todos los incidentes de exceso de velocidad almacenados en la VU.
					NOOFVUOVERSPEEDINGEVENTS(1),//Si la seccion esta vacia, tan solo se envia noOfVuOverSpeedingEvents=0
					VUOVERSPEEDINGEVENTRECORD(31),
						EVENTTYPE_VUOVERSPEED(1),
						EVENTRECORDPURPOSE_VUOVERSPEED(1),
						EVENTBEGINTIME_VUOVERSPEED(4),
						EVENTENDTIME_VUOVERSPEED(4),
						MAXSPEEDVALUE(1),
						AVARAGESPEEDVALUE(1),
						CARDNUMBERDRIVERSLOTBEGIN_VUOVERSPEED(18),
						SIMILAREVENTSNUMBER(1),
				VUTIMEADJUSTMENTDATA(99),		//Todos los ajustes de hora almacenados en la VU(fuera del marco de un calibrado
					NOOFVUTIMEADJRECORDS(1),	//completo). Si la seccion esta vacia, tan solo se envia noOfVuTimeAdjRecors=0
						VUTIMEADJUSTMENTRECORD(98),
							OLDTIMEVALUE_VUTIMEADJUSTMENTDATA(4),
							NEWTIMEVALUE_VUTIMEADJUSTMENTDATA(4),
							WORKSHOPNAME_VUTIMEADJUSTMENTADATA(36),
							WORKSHOPADDRESS_VUTIMEADJUSTMENTADATA(36),
							WORKSHOPCARDNUMBER_VUTIMEADJUSTMENTADATA(18),
				SIGNATURE_TREP3(128),
				
				TREP_4(0), 						//Velocidad
					VUDETAILEDSPEEDDATA(66),
						NOOFSPEEDBLOCKS(2),		//Todos los datos pormenorizados almacenados en la Vu y relativos a la velociad del
						VUDETAILEDSPEEDBLOCK(64),//vehiculo (un bloque de datos por cad minuto que haya estado el vehiculo en 
							SPEEDBLOCKBEGINDATE(4),//movimiento) 60 valores de velocidad por cada minuto(un valor por segundo)
							SPEEDSPERSECOND(60),
					SIGNATURE_TREP4(128),		// Firma RSA de todos los datos, desde noOfSpeedBlocks hsta el ultimo byte del
												//ultimo bloque con datos de velocidad
										
				TREP_5(208),						//Datos tecnicos
					VUIDENTIFICATION(116),
						VUMANUFACTURERNAME(36),
						VUMANUFACTURERADDRESS(36),						
						VUPARTNUMBER(16),
						VUSERIALNUMBER(8),
						VUSOFTWAREIDENTIFICATION(8),
							VUSOFTWAREVERSION(4),
							VUSOFTWAREINSTALLATIONDATE(4),
						VUMANUFATURINGDATE(4),
						VUAPPROVALNUMBER(8),
					SENSORPAIRED(20),
						SENSORSERIALNUMBER(8),
						SENSORAPPROVALNUMBER(8),
						SENSORPAIRINGDATEFIRST(4),
					VUCALIBRATIONDATA(165),			//Todos los registros de calibrado almacenados en la VU
						NOOFVUCALIBRATIONRECORDS(1),
						VUCALIBRATIONRECORD(164),
							CALIBRATIONPURPOSE(1),
							WORKSHOPNAME_VUCALIBRATIONDATA(36),
							WORKSHOPADDRESS_VUCALIBRATIONDATA(36),
							WORKSHOPCARDNUMBER_VUCALIBRATIONDATA(18),
							WORKSHOPCARDEXPIRYDATE(4),
							VEHICLEIDENTIFICATIONNUMBER(17),
							VEHICLEREGISTRATIONIDENTIFICATION_TREP5(15),
								VEHICLEREGISTRATIONNATION_TREP5(1),
								VEHICLEREGISTRATIONNUMBER_TREP5(14),
							WVEHICLECHARACTERISTICCONSTANT(2),
							KCONSTANTOFRECORDINGEQUIPMENT(2),
							LTYRECIRCUMFERENCE(2),
							TYRESIZE(15),
							AUTHORISEDSPEED(1),
							OLDODOMETERVALUE(3),
							NEWODOMETERVALUE(3),
							OLDTIMEVALUE_VUCALIBRATIONDATA(4),
							NEWTIMEVALUE_VUCALIBRATIONDATA(4),
							NEXTCALIBRATIONDATE(4),
					SIGNATURE_TREP5(128);			//Firma RSA de todos los datos, desde vuManufacturerName hasta el ultimo
													// byte del ultimo VuCalibrationRecord
	
	private int size;
	
	
	Sizes(int size){
		
		this.size=size;				
	}
	
	

	/**
	 * Obtiene el tama�o de la clase en bytes
	 * @return the min
	 */
	public int getSize() {
		return size;
	}

	

}
