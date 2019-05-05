package com.tachograph;

public enum APDUCommand {
    SELECT_FILE                 (0xa4),
    READ_BINARY                 (0xb0),
    UPDATE_BINARY               (0xD6),
    GET_CHALLENGE               (0x84),
    VERIFY                      (0x20),
    GET_RESPONSE                (0xc0),
    PERFORM_SECURITY_OPERATION  (0x2a),
    //VERIFY CERTIFICATE
    //COMPUTE DIGITAL SIGNATURE
    //VERIFY DIGITAL SIGNATURE
    //HASH
    INTERNAL_AUTHENTICATE       (0x88),
    EXTERNAL_AUTHENTICATE       (0x82),
    MANAGE_SECURITY_ENVIROMENT  (0x22),
    //SETTING A KEY
    PERFORM_HASH_OF_FILE        (0x2a);
    private final int command;

    APDUCommand(int command){
        this.command = command;
    }
    public int getCommand(){return this.command;}

}
