package org.tacografo.file.certificate;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.24. CardPublicKey
 * La clave pública de una tarjeta.
 * CardPublicKey ::= PublicKey
 */
public class CardPublicKey {

    private PublicKey cardPublicKey;
    public CardPublicKey(){

    }
    public CardPublicKey(byte[] datos) {

        this.cardPublicKey = new PublicKey(Arrays.copyOfRange(datos, 0, 136));
    }

    public PublicKey getCardPublicKey() {
        return cardPublicKey;
    }

    public void setCardPublicKey(PublicKey cardPublicKey) {
        this.cardPublicKey = cardPublicKey;
    }
}
