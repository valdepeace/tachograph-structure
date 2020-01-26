package com.tachographStructure.file.driverCardBlock.subBlocks;

/**
 * 2.120.   RecordType
 *
 * Generation 2:
 *
 * Reference to a record type. This data type is used in RecordArrays.
 *
 *          RecordType ::= OCTET STRING(SIZE(1))
 *
 *
 * @author Andres Carmona Gil
 * @version 0.0.2
 */
public class RecordType {

    private byte recordType;
    private byte value;
    public RecordType(){}

    public RecordType(byte[] bytes){

    }

    public String getRecordType(byte[] bytes){
        String str = "";
        switch (bytes[0]){
            case 0x01: str = "ActivityChangeInfo"; break;
            case 0x02: str = "CardSlotsStatus"; break;
            case 0x03: str = "CurrentDateTime"; break;
            case 0x04: str = "MemberStateCertificate"; break;
            case 0x05: str = "OdometerValueMidnight"; break;
            case 0x06: str = "DateOfDayDownloaded"; break;
            case 0x07: str = "SensorPaired"; break;
            case 0x08: str = "Signature"; break;
            case 0x09: str = "SpecificConditionRecord"; break;
            case 0x0a: str = "VehicleIdentificationNumber"; break;
            case 0x0b: str = "VehicleRegistrationNumber"; break;
            case 0x0c: str = "VuCalibrationRecord"; break;
            case 0x0d: str = "VuCardIWRecord"; break;
            case 0x0e: str = "VuCardRecord"; break;
            case 0x0f: str = "VuCertificate"; break;
            case 0x10: str = "VuCompanyLocksRecord"; break;
            case 0x11: str = "VuControlActivityRecord"; break;
            case 0x12: str = "VuDetailedSpeedBlock"; break;
            case 0x13: str = "VuDownloadblePeriod"; break;
            case 0x14: str = "VuDownloadActivityData"; break;
            case 0x15: str = "VuEventRecord"; break;
            case 0x16: str = "VuGNSSCDRecord"; break;
            case 0x17: str = "VuITSConsentRecord"; break;
            case 0x18: str = "VuFaultRecord"; break;
            case 0x19: str = "VuIdentification"; break;
            case 0x1a: str = "VuOverSpeedingControlData"; break;
            case 0x1b: str = "VuOverSpeedingEventRecord"; break;
            case 0x1c: str = "VuPlaceDailyWorkPeriodRecord"; break;
            case 0x1d: str = "VuTimeAdjustmentRecord"; break;
            case 0x1e: str = "VuPowerSupplyInterruptionRecord"; break;
            case 0x1f: str = "VuPowerSupplyInterruptionRecord"; break;
            case 0x20: str = "SensorPairedRecord"; break;
            case 0x21: str = "SensorExternalGNSSCoupledRecord"; break;
            default:
                if(bytes[0] >= 0x22 && bytes[0] <= 0x7f)
                    str = "RFU";
                if(bytes[0] >= 0x80 && bytes[0] <= 0xff)
                    str = "Manufacturer specific";



        }
        return str;
    }
}
