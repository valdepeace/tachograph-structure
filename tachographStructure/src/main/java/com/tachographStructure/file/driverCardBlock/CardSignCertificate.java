package com.tachographStructure.file.driverCardBlock;

import com.tachographStructure.file.Block;
import com.tachographStructure.file.driverCardBlock.subBlocks.Certificate;

/**
 * 2.32.   CardSignCertificate
 *
 * Generation 2:
 *
 * Certificate of the card public key for signature. The structure of this certificate is specified in Appendix 11.
 *
 *          CardSignCertificate ::= Certificate
 *
 * @author Andres Carmona Gil
 * @version 0.0.2
 */
public class CardSignCertificate extends Block implements CardBlock {
    private Certificate certificate;
    public CardSignCertificate(){}
    public CardSignCertificate(byte[] bytes){
        this.certificate = new Certificate(bytes);
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
}
