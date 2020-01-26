/**
 * 
 */
package com.tachographStructure.file.vuBlocks.subBlocks;

/**
 * @author Andres Carmona Gil
 * @version 0.0.2
 * 
 * 2.4.   CalibrationPurpose
 * 
 * Code explaining why a set of calibration parameters was recorded. This data type is related to Annex 1B
 * requirements 097 and 098 and Annex 1C requirements 119.
 * CalibrationPurpose ::= OCTET STRING (SIZE(1))
 *
 * Value assignment:
 * 		Generation 1:
 * 			‘00’H reserved value,
 * 			‘01’H activation: recording of calibration parameters known, at the moment of the VU activation,
 * 			‘02’H first installation: first calibration of the VU after its activation,
 * 			‘03’H installation: first calibration of the VU in the current vehicle,
 * 			‘04’H periodic inspection.
 * 		Generation 2:
 * 		In addition to generation 1 the following values are used:
 * 			‘05’H entry of VRN by company,
 * 			‘06’H time adjustment without calibration,
 * 			‘07’H to ‘7F’H RFU,
 * 			‘80’H to ‘FF’H Manufacturer specific.
 */
public class CalibrationPurpose {

	
	public static final String getCalibrationPurpose(byte[] bytes){
		String str = "";
		switch (bytes[0]) {
		case 0x00:str = "reserved value"; break;
		case 0x01:str = "activation: recording of calibration parameters known, at the moment of the VU activation"; break;
		case 0x02:str = "first installation: first calibration of the VU after its activation"; break;
		case 0x03:str = "installation: first calibration of the VU in the current vehicle"; break;
		case 0x04:str = "periodic inspection"; break;
		case 0x05:str = "entry of VRN by company"; break;
		case 0x06:str = "time adjustment without calibration"; break;
		default:
			if(bytes[0] >= 0x07 && bytes[0] <= 0x7f)
				str="RFU";
			if(bytes[0] >= 0x80 && bytes[0] <= 0xff)
				str = "Manufacturer specific";
			break;
		}
		return str;
	}
}
