package com.tachographStructure.file.certificate;

import com.tachographStructure.helpers.OctetString;
import java.math.BigInteger;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.91. RSAKeyPublicExponent
 * El exponente público de un par de claves RSA.
 * RSAKeyPublicExponent ::= OCTET STRING (SIZE(8))
 * Asignación de valor: sin especificar
 */
public class RSAKeyPublicExponent {

    private String rsaKeyPublicExponent;

    private BigInteger rsaKeyPublicExponent_bc;

    private byte[] rsaKeyPublicExponentBytes;

    public RSAKeyPublicExponent(){
    }

    public RSAKeyPublicExponent(byte[] datos){
        this.rsaKeyPublicExponentBytes=datos;
        OctetString os=new OctetString(datos);
        this.rsaKeyPublicExponent=os.getOctetString();
        this.rsaKeyPublicExponent_bc=new BigInteger(1,datos);
    }

    public String getRsaKeyPublicExponent() {
        return rsaKeyPublicExponent;
    }

    public void setRsaKeyPublicExponent(String rsaKeyPublicExponent) {
        this.rsaKeyPublicExponent = rsaKeyPublicExponent;
    }

    public BigInteger getRsaKeyPublicExponent_bc() {
        return rsaKeyPublicExponent_bc;
    }

    public void setRsaKeyPublicExponent_bc(BigInteger rsaKeyPublicExponent_bc) {
        this.rsaKeyPublicExponent_bc = rsaKeyPublicExponent_bc;
    }

    public byte[] getRsaKeyPublicExponentBytes() {
        return rsaKeyPublicExponentBytes;
    }

    public void setRsaKeyPublicExponentBytes(byte[] rsaKeyPublicExponentBytes) {
        this.rsaKeyPublicExponentBytes = rsaKeyPublicExponentBytes;
    }
}
