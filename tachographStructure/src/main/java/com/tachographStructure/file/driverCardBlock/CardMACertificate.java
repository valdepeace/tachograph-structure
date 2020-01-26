package com.tachographStructure.file.driverCardBlock;

import com.tachographStructure.file.Block;
import com.tachographStructure.file.driverCardBlock.subBlocks.Certificate;

/**
 * 2.25.   CardMACertificate
 *
 * Generation 2:
 *
 * Certificate of the card public key for mutual authentication with a VU. The structure of this certificate is specified in Appendix 11.
 *
 *          CardMACertificate ::= Certificate
 *
 * @author Andres Carmona Gil
 * @version 0.0.2
 */
public class CardMACertificate extends Block implements CardBlock{
    private Certificate certificate;

    public CardMACertificate(){

    }

    public CardMACertificate(byte[] bytes){
        this.certificate= new Certificate(bytes);
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
}
