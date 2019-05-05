package com.tachographStructure.file.certificate;

import com.tachographStructure.file.driverCardBlock.subBlocks.NationNumeric;
import com.tachographStructure.helpers.Number;
import com.tachographStructure.helpers.OctetString;
import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.36. CertificationAuthorityKID
 * Identificador de la clave pública de una autoridad de certificación (un Estado miembro o la autoridad de certificación
 * europea).
 * CertificationAuthorityKID ::= SEQUENCE {
 *      nationNumeric NationNumeric
 *      nationAlpha NationAlpha
 *      keySerialNumber INTEGER(0..255)
 *      additionalInfo OCTET STRING(SIZE(2))
 *      caIdentifier OCTET STRING(SIZE(1))
 * }
 * nationNumeric es el código numérico de nación de la autoridad de certificación.
 * nationAlpha es el código alfanumérico de nación de la autoridad de certificación.
 * keySerialNumber es un número de serie para distinguir las diferentes claves de la autoridad de certificación en caso de
 * que éstas se cambien.
 * additionalInfo es un campo de dos bytes para codificación adicional (específica de la autoridad de certificación).
 * caIdentifier es un identificador para distinguir entre el identificador de clave de una autoridad de certificación y otros
 * identificadores de clave.
 * Asignación de valor: ¡01h¡.
 */
public class CertificationAuthorityKID {

    private String nationNumeric;

    private String nationAlpha;

    private int keySerialNumber;

    private String additionalInfo;

    private String caIdentifier;

    public CertificationAuthorityKID() {
    }

    public CertificationAuthorityKID(byte[] datos) {
        int start=0;
        NationNumeric nn=new NationNumeric(Arrays.copyOfRange(datos, start, start+=1));
        this.nationNumeric=nn.getNationNumeric();
        NationAlpha na=new NationAlpha(Arrays.copyOfRange(datos, start, start+=3));
        this.nationAlpha=na.getNationAlpha();
        this.keySerialNumber= Number.getNumber(Arrays.copyOfRange(datos, start, start+=1));
        OctetString os=new OctetString(Arrays.copyOfRange(datos, start, start+=2));
        this.additionalInfo=os.getOctetString();
        os=new OctetString(Arrays.copyOfRange(datos, start, start+=1));
        this.caIdentifier=os.getOctetString();
    }

    public String getNationNumeric() {
        return nationNumeric;
    }

    public void setNationNumeric(String nationNumeric) {
        this.nationNumeric = nationNumeric;
    }

    public String getNationAlpha() {
        return nationAlpha;
    }

    public void setNationAlpha(String nationAlpha) {
        this.nationAlpha = nationAlpha;
    }

    public int getKeySerialNumber() {
        return keySerialNumber;
    }

    public void setKeySerialNumber(int keySerialNumber) {
        this.keySerialNumber = keySerialNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getCaIdentifier() {
        return caIdentifier;
    }

    public void setCaIdentifier(String caIdentifier) {
        this.caIdentifier = caIdentifier;
    }
}
