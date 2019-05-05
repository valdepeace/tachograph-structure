package com.tachographStructure.file.certificate;

import com.tachographStructure.file.error.ExceptionSignatureContent;
import com.tachographStructure.file.error.ExceptionSignatureHash;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 01/07/2016.
 * @author Andres Carmona Gil
 *
 * 2.101. Signature
 * Una firma digital.
 * Signature ::= OCTET STRING (SIZE(128))
 * Asignación de valor: con arreglo a lo dispuesto en el Apéndice 11 (Mecanismos de seguridad comunes).
 *
 */
public class Signature {

    private String signature;
    private byte[] signature_bytes;
    private boolean verified=false;
    public Signature(byte[] bytes) {
        this.signature_bytes=bytes;
        StringBuffer sb=new StringBuffer();
        for (byte b:bytes){
            sb.append(String.format("%x",b));
        }
        this.signature = sb.toString();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public byte[] getSignature_bytes() {
        return signature_bytes;
    }

    public void setSignature_bytes(byte[] signature_bytes) {
        this.signature_bytes = signature_bytes;
    }

    /**
     * p.251
     * @param datas
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public boolean verify(byte[] datas,PublicKey publicKey,String block) throws NoSuchAlgorithmException, ExceptionSignatureHash, ExceptionSignatureContent {
        boolean verify=true;
        byte[] sr=publicKey.recover(this.getSignature_bytes());
        byte[] H= Arrays.copyOfRange(sr,107,128);
        // comprobacion de Hash(c)= h
        byte[] random={0x30,0x21,0x30,0x09,0x06,0x05,0x2b,0x0e,0x03,0x02,0x1a,0x05,0x00,0x04,0x14};
        MessageDigest md=MessageDigest.getInstance("SHA-1");
        md.update(datas);
        byte[] hashc=md.digest();
        for (int i=0;i<hashc.length-1;i++){
            if (hashc[i]!=H[i]){
                verify=false;
                throw new ExceptionSignatureHash("hash datas not equal hash in signature for block"+block,new Throwable("signature (128 bytes) = content(106 bytes) + hash (20 bytes)"));
                //throw new Error("hash file certificate not equal");
            }
        }
        // Firma = EQT.SK [00 || 01 || PS || 00 || DER(SHA-1(datos))]
        for (int i=1;i<91;i++){
            if(sr[i]!=-1){
                verify=false;
                // error por Firma = EQT.SK [00 + 01 + PS + 00 + DER(SHA-1(datos))]
                throw new ExceptionSignatureContent("content signature not equals FF for block "+block,new Throwable(" signature = EQT.SK [00 || 01 || PS(91 bytes) || 00 || DER(SHA-1(datos))(15 bytes)] PS=FF"));
            }
        }
        for (int i=0;i<15;i++){
            if(sr[i+92]!=random[i]){
                verify=false;
                //error hash de la firma no se corresponde con el de los datos {0x30,0x21,0x30,0x09,0x06,0x05,0x2b,0x0e,0x03,0x02,0x1a,0x05,0x00,0x04,0x14}
                throw new ExceptionSignatureContent("content signature not equals FF for block"+block,new Throwable("DER(SHA-1({0x30,0x21,0x30,0x09,0x06,0x05,0x2b,0x0e,0x03,0x02,0x1a,0x05,0x00,0x04,0x14})"));
            }

        }

        this.verified=verify;
        return verify;
    }
}
