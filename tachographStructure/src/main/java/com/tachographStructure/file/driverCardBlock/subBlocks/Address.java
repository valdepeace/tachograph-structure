package com.tachographStructure.file.driverCardBlock.subBlocks;

/**
 * 2.2. Address
 * An address.
 * Address ::= SEQUENCE{
 *     codePage     INTEGER(0...255),
 *     address      OCTET STRING(SIZE(35))
 * }
 * codePage specifies a character set defined in Chapter 4,
 * address is an address encoded using the specified character set.
 *
 * @author Andres Carmona Gil
 * @version 0.0.2
 */

public class Address {

    private String address;

    public Address(byte[] bytes){
        //TODO develop address is not interesting?
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
