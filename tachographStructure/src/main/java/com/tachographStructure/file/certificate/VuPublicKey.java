package com.tachographStructure.file.certificate;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.146. VuPublicKey
 * La clave pública de una unidad intravehicular.
 * VuPublicKey ::= PublicKey
 */
public class VuPublicKey {

    private PublicKey publicKey;

    public VuPublicKey() {

    }

    public VuPublicKey(byte[] datos){
        this.publicKey=new PublicKey(Arrays.copyOfRange(datos, 0, 136));
    }
    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
