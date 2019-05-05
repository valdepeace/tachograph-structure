package org.tacografo.file.certificate;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.145. VuPrivateKey
 * La clave privada de una unidad intravehicular.
 * VuPrivateKey ::= RSAKeyPrivateExponent
 *
 */
public class VuPrivateKey {

    private RSAKeyPrivateExponent rsaKeyPrivateExponent;

    public VuPrivateKey() {
    }
    public VuPrivateKey(byte[] datos) {
        this.rsaKeyPrivateExponent=new RSAKeyPrivateExponent(Arrays.copyOfRange(datos, 0, 128));
    }

    public RSAKeyPrivateExponent getRsaKeyPrivateExponent() {
        return rsaKeyPrivateExponent;
    }

    public void setRsaKeyPrivateExponent(RSAKeyPrivateExponent rsaKeyPrivateExponent) {
        this.rsaKeyPrivateExponent = rsaKeyPrivateExponent;
    }
}
