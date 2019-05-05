package com.tachographStructure.file.certificate;

import com.tachographStructure.file.driverCardBlock.subBlocks.ExtendedSerialNumber;
import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 19/05/2016.
 *
 * Un identificador exclusivo de una clave pública, empleado para hacer referencia a dicha clave y seleccionarla. También
 * identifica al titular de la clave.
 * KeyIdentifier ::= CHOICE {
 *      extendedSerialNumber ExtendedSerialNumber,
 *      certificateRequestID CertificateRequestID,
 *      certificationAuthorityKID CertificationAuthorityKID
 * }
 * La primera opción sirve para hacer referencia a la clave pública de una unidad intravehicular o de una tarjeta de tacó-
 * grafo.
 * La segunda opción sirve para hacer referencia a la clave pública de una unidad intravehicular (en caso de que el número
 * de serie de dicha unidad intravehicular no pueda conocerse en el momento de generarse el certificado).
 * La tercera opción sirve para hacer referencia a la clave pública de un Estado miembro.
 */
public class KeyIdentifier {  //21

    private ExtendedSerialNumber extendedSerialNumber; // 8

    private CertificateRequestID certificateRequestID; // 5 vu

    private CertificationAuthorityKID certificationAuthorityKID; //8 card

    public KeyIdentifier(){

    }

    public KeyIdentifier(byte[] datos) {
        int start=0;
        //this.extendedSerialNumber=new ExtendedSerialNumber(Arrays.copyOfRange(datos, start, start+=8));
        //this.certificateRequestID=new CertificateRequestID(Arrays.copyOfRange(datos, start, start+=5)); //VU
        this.certificationAuthorityKID=new CertificationAuthorityKID(Arrays.copyOfRange(datos, start, start+=8));

    }

    public ExtendedSerialNumber getExtendedSerialNumber() {
        return extendedSerialNumber;
    }

    public void setExtendedSerialNumber(ExtendedSerialNumber extendedSerialNumber) {
        this.extendedSerialNumber = extendedSerialNumber;
    }

    public CertificateRequestID getCertificateRequestID() {
        return certificateRequestID;
    }

    public void setCertificateRequestID(CertificateRequestID certificateRequestID) {
        this.certificateRequestID = certificateRequestID;
    }

    public CertificationAuthorityKID getCertificationAuthorityKID() {
        return certificationAuthorityKID;
    }

    public void setCertificationAuthorityKID(CertificationAuthorityKID certificationAuthorityKID) {
        this.certificationAuthorityKID = certificationAuthorityKID;
    }
}
