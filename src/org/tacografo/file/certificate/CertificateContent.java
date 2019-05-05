package org.tacografo.file.certificate;

import org.tacografo.file.cardblockdriver.subblock.Certificate;
import org.tacografo.file.error.ExceptionContentCertificateHash;
import org.tacografo.file.error.ExceptionContentCertificateOpen;
import org.tacografo.tiposdatos.Number;
import org.tacografo.tiposdatos.RealTime;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;


/**
 * Created by Andres Carmona Gil on 18/05/2016.
 * El contenido (sin cifrar) del certificado de una clave pública, según lo dispuesto en el Apéndice 11 Mecanismos de
 * seguridad comunes.
 * CertificateContent ::= SEQUENCE {
 *          certificateProfileIdentifier INTEGER(0..255),
 *          certificationAuthorityReference KeyIdentifier,
 *          certificateHolderAuthorisation CertificateHolderAuthorisation,
 *          certificateEndOfValidity TimeReal,
 *          certificateHolderReference KeyIdentifier,
 *          publicKey PublicKey
 * }
 * certificateProfileIdentifier es la versión del certificado que corresponda.
 *           Asignación de valor: ¡01h¡ para esta versión.
 * CertificationAuthorityReference identifica a la autoridad de certificación que expide el certificado. También es una
 *          referencia a la clave pública de dicha autoridad de certificación.
 * certificateHolderAuthorisation identifica los derechos que asisten al titular del certificado.
 * certificateEndOfValidity es la fecha en que el certificado caduca administrativamente.
 * certificateHolderReference identifica al titular del certificado. También es una referencia a su clave pública.
 * publicKey es la clave pública que se certifica con este certificado.
 */
public class CertificateContent { // total size 186

    private int certificateProfileIdentifier; // 1

    private CertificationAuthorityKID certificationAuthorityReference; // 8

    private CertificateHolderAuthorisation certificateHolderAuthorisation; // 7

    private Date certificateEndOfValidity; // 4

    private KeyIdentifier certificateHolderReference; //22

    private PublicKey publicKey; // 136

    public CertificateContent() {
    }

    /**
     * Basado en el punto 3.3.3 Verificación y apertura del certificado
     * @param bytes
     * @param publicKey
     * @throws NoSuchAlgorithmException
     */
    public CertificateContent(byte[] bytes,PublicKey publicKey) throws NoSuchAlgorithmException, ExceptionContentCertificateOpen, ExceptionContentCertificateHash {
        int start=0;
        byte[] sign=Arrays.copyOfRange(bytes,start,start+=128);
        byte[] Cn=Arrays.copyOfRange(bytes,start,start+=58);
        byte[] CAR=Arrays.copyOfRange(bytes,start,start+=8);
        byte[] sr=publicKey.recover(sign);
        boolean err=false;
        if(sr[0]!=0x6a || (sr[127] & 0x000000ff)!=0xbc){
            err=true;
            throw new ExceptionContentCertificateOpen("Open Certificate",new Throwable("Sr != '6A' or Sr != 'BC' or public key not valid"));
            // error abriendo archivo o la llave publica no es valida
        }else{
            // 2. Calcular Cr y H a partir de Sr= 6a || Cr || H || bc
            start=1;
            byte[] Cr=Arrays.copyOfRange(sr, start, start+=106);
            byte[] H=Arrays.copyOfRange(sr, start, start+=20);
            // recuperar C del certificado = Cr || Cn
            byte[] C=new byte[Cr.length+Cn.length];
            System.arraycopy(Cr,0,C,0,Cr.length);
            System.arraycopy(Cn,0,C,Cr.length,Cn.length);
            // comprobacion de Hash(c)= h
            MessageDigest md=MessageDigest.getInstance("SHA-1");
            md.update(C);
            byte[] hashc=md.digest();
            for (int i=0;i<hashc.length-1;i++){
                if (hashc[i]!=H[i]){
                    err=true;
                    throw new ExceptionContentCertificateHash("Hash datas not equals certificate",new Throwable(" Sr = 6A + Cr(106 bytes) + H(20 bytes) + BC -- C=Cr+Cn -- Hash(C)=H"));
                    // error en comprobacion hash del certificado o la llave publica no es valida
                }
            }
            if(!err){
                start = 0;
                this.certificateProfileIdentifier= Number.getNumber(Arrays.copyOfRange(C, start,start += 1));
                this.certificationAuthorityReference= new CertificationAuthorityKID(Arrays.copyOfRange(C, start, start+= 8));
                //this.certificationAuthorityReference=Number.getNumber(Arrays.copyOfRange(datos, start,start += 1));
                this.certificateHolderAuthorisation=new CertificateHolderAuthorisation(Arrays.copyOfRange(C, start,start+= 7));
                this.certificateEndOfValidity= RealTime.getRealTime(Arrays.copyOfRange(C, start, start+=4));
                this.certificateHolderReference=new KeyIdentifier(Arrays.copyOfRange(C, start, start+=8));
                this.publicKey=new PublicKey(Arrays.copyOfRange(C, start, start+=136));
            }
        }
    }
    public CertificateContent(Certificate certificate, PublicKey publicKey, String block) throws NoSuchAlgorithmException, ExceptionContentCertificateOpen, ExceptionContentCertificateHash {
        int start=0;
        byte[] sign=Arrays.copyOfRange(certificate.getBytes(),start,start+=128);
        byte[] Cn=Arrays.copyOfRange(certificate.getBytes(),start,start+=58);
        byte[] CAR=Arrays.copyOfRange(certificate.getBytes(),start,start+=8);
        byte[] sr=publicKey.recover(sign);
        boolean err=false;
        if(sr[0]!=0x6a || (sr[127] & 0x000000ff)!=0xbc){
            err=true;
            throw new ExceptionContentCertificateOpen("Open Certificate for "+block,new Throwable("Sr != '6A' or Sr != 'BC' or public key not valid"));
            // error abriendo archivo o la llave publica no es valida
        }else{
            // 2. Calcular Cr y H a partir de Sr= 6a || Cr || H || bc
            start=1;
            byte[] Cr=Arrays.copyOfRange(sr, start, start+=106);
            byte[] H=Arrays.copyOfRange(sr, start, start+=20);
            // recuperar C del certificado = Cr || Cn
            byte[] C=new byte[Cr.length+Cn.length];
            System.arraycopy(Cr,0,C,0,Cr.length);
            System.arraycopy(Cn,0,C,Cr.length,Cn.length);
            // comprobacion de Hash(c)= h
            MessageDigest md=MessageDigest.getInstance("SHA-1");
            md.update(C);
            byte[] hashc=md.digest();
            for (int i=0;i<hashc.length-1;i++){
                if (hashc[i]!=H[i]){
                    err=true;
                    throw new ExceptionContentCertificateHash("Hash datas not equals certificate for "+block,new Throwable(" Sr = 6A + Cr(106 bytes) + H(20 bytes) + BC -- C=Cr+Cn -- Hash(C)=H"));
                    // error en comprobacion hash del certificado o la llave publica no es valida
                }
            }
            if(!err){
                start = 0;
                this.certificateProfileIdentifier= Number.getNumber(Arrays.copyOfRange(C, start,start += 1));
                this.certificationAuthorityReference= new CertificationAuthorityKID(Arrays.copyOfRange(C, start, start+= 8));
                //this.certificationAuthorityReference=Number.getNumber(Arrays.copyOfRange(datos, start,start += 1));
                this.certificateHolderAuthorisation=new CertificateHolderAuthorisation(Arrays.copyOfRange(C, start,start+= 7));
                this.certificateEndOfValidity= RealTime.getRealTime(Arrays.copyOfRange(C, start, start+=4));
                this.certificateHolderReference=new KeyIdentifier(Arrays.copyOfRange(C, start, start+=8));
                this.publicKey=new PublicKey(Arrays.copyOfRange(C, start, start+=136));
            }
        }
    }

    public CertificateContent(byte[] datos) throws IOException {
        int start=0;
        this.certificateProfileIdentifier= Number.getNumber(Arrays.copyOfRange(datos, start,start += 1));
        this.certificationAuthorityReference= new CertificationAuthorityKID(Arrays.copyOfRange(datos, start, start+= 8));
        //this.certificationAuthorityReference=Number.getNumber(Arrays.copyOfRange(datos, start,start += 1));
        this.certificateHolderAuthorisation=new CertificateHolderAuthorisation(Arrays.copyOfRange(datos, start,start+= 7));
        this.certificateEndOfValidity= RealTime.getRealTime(Arrays.copyOfRange(datos, start, start+=4));
        this.certificateHolderReference=new KeyIdentifier(Arrays.copyOfRange(datos, start, start+=8));
        this.publicKey=new PublicKey(Arrays.copyOfRange(datos, start, start+=136));
    }

    public int getCertificateProfileIdentifier() {
        return certificateProfileIdentifier;
    }

    public void setCertificateProfileIdentifier(int certificateProfileIdentifier) {
        this.certificateProfileIdentifier = certificateProfileIdentifier;
    }

    public CertificationAuthorityKID getCertificationAuthorityReference() {
        return certificationAuthorityReference;
    }

    public void setCertificationAuthorityReference(CertificationAuthorityKID certificationAuthorityReference) {
        this.certificationAuthorityReference = certificationAuthorityReference;
    }

    public CertificateHolderAuthorisation getCertificateHolderAuthorisation() {
        return certificateHolderAuthorisation;
    }

    public void setCertificateHolderAuthorisation(CertificateHolderAuthorisation certificateHolderAuthorisation) {
        this.certificateHolderAuthorisation = certificateHolderAuthorisation;
    }

    public Date getCertificateEndOfValidity() {
        return certificateEndOfValidity;
    }

    public void setCertificateEndOfValidity(Date certificateEndOfValidity) {
        this.certificateEndOfValidity = certificateEndOfValidity;
    }

    public KeyIdentifier getCertificateHolderReference() {
        return certificateHolderReference;
    }

    public void setCertificateHolderReference(KeyIdentifier certificateHolderReference) {
        this.certificateHolderReference = certificateHolderReference;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
    public void setPublicKey(byte[] datos) {
        this.publicKey=new PublicKey(datos);
    }

}
