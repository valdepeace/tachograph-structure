package org.tacografo.file.certificate;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.69. MemberStatePublicKey
 * La clave pública de un Estado miembro.
 * MemberStatePublicKey ::= PublicKey
 *
 */
public class MemberStatePublicKey {

    private PublicKey publicKey;

    public MemberStatePublicKey() {
    }
    public MemberStatePublicKey(byte[] datos) {
        this.publicKey=new PublicKey(Arrays.copyOfRange(datos, 0, 136));
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
