package org.tacografo.file.certificate;

import org.tacografo.tiposdatos.OctetString;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.90. RSAKeyPrivateExponent
 * El exponente privado de un par de claves RSA.
 * RSAKeyPrivateExponent ::= OCTET STRING (SIZE(128))
 * Asignación de valor: sin especificar.
 */
public class RSAKeyPrivateExponent {

    private String RSAKeyPrivateExponent;

    public RSAKeyPrivateExponent() {

    }
    public RSAKeyPrivateExponent(byte[] datos) {
        OctetString os=new OctetString(Arrays.copyOfRange(datos, 0, 128));
        this.RSAKeyPrivateExponent=os.getOctetString();
    }

    public String getRSAKeyPrivateExponent() {
        return RSAKeyPrivateExponent;
    }

    public void setRSAKeyPrivateExponent(String RSAKeyPrivateExponent) {
        this.RSAKeyPrivateExponent = RSAKeyPrivateExponent;
    }
}
