package com.tachographStructure.file.certificate;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.23. CardPrivateKey
 * La clave privada de una tarjeta.
 * CardPrivateKey ::= RSAKeyPrivateExponent
 */

public class CardPrivateKey {

    private RSAKeyPrivateExponent CardPrivateKey;  //RSAKeyPrivateExponent

    public CardPrivateKey() {
    }
    public CardPrivateKey(byte[] datos) {
        this.CardPrivateKey=new RSAKeyPrivateExponent(Arrays.copyOfRange(datos, 0, 128));
    }

    public RSAKeyPrivateExponent getCardPrivateKey() {
        return CardPrivateKey;
    }

    public void setCardPrivateKey(RSAKeyPrivateExponent cardPrivateKey) {
        CardPrivateKey = cardPrivateKey;
    }
}
