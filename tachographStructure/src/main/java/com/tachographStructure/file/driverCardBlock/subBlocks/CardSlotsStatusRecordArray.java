package com.tachographStructure.file.driverCardBlock.subBlocks;

/**
 *
 * 2.35.   CardSlotsStatusRecordArray
 *
 * Generation 2:
 *
 * The CardSlotsStatus plus metadata as used in the download protocol.
 *          CardSlotsStatusRecordArray ::= SEQUENCE {
 *              recordType          RecordType,
 *              recordSize          INTEGER(1...65535),
 *              noOfRecords         INTEGER(0...65534),
 *              records             SET SIZE(noOfRecords) OF CardSlotsStatus
 *          }
 * recordType denotes the type of the record (CardSlotsStatus). Value Assignment: See RecordType
 *
 * recordSize is the size of the CardSlotsStatus in bytes.
 *
 * noOfRecords is the number of records in the set records.
 *
 * records is the set of CardSlotsStatus records.
 * @author Andres Carmona Gil
 * @version 0.0.2
 */
public class CardSlotsStatusRecordArray {
    private RecordType recordType;
    private int recordSize;
    private int noOfRecords;
    private String records;

    public CardSlotsStatusRecordArray(){}
    public CardSlotsStatusRecordArray(byte[] bytes){

    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }
}
