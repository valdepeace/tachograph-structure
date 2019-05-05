package org.tacografo.file.certificate;

import org.tacografo.file.cardblockdriver.subblock.EquipmentType;
import org.tacografo.tiposdatos.OctetString;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.34. CertificateHolderAuthorisation
 * Identificación de los derechos que asisten al titular de un certificado.
 * CertificateHolderAuthorisation ::= SEQUENCE {
 *           tachographApplicationID OCTET STRING(SIZE(6))
 *           equipmentType EquipmentType
 * }
 * tachographApplicationID es el identificador de la aplicación de tacógrafo.
 * Asignación de valor: ¡FFh¡ ¡54h¡ ¡41h¡ ¡43h¡ ¡48h¡ ¡4Fh¡. Este AID es un identificador propio y no registrado de la
 * aplicación, con arreglo a la norma ISO/IEC 7816-5.
 * equipmentType es la identificación del tipo de equipo al que se refiere el certificado.
 * Asignación de valor: de acuerdo con el tipo de datos EquipmentType. 0 si el certificado es de un Estado miembro.
 */
public class CertificateHolderAuthorisation {

    private String tachographApplicationId;

    private String equipmentType;

    public CertificateHolderAuthorisation() {
    }
    public CertificateHolderAuthorisation(byte[] datos) {
        int start=0;
        OctetString os=new OctetString(Arrays.copyOfRange(datos, start,start+= 6));//6
        this.tachographApplicationId=os.getOctetString();
        EquipmentType et=new EquipmentType(Arrays.copyOfRange(datos, start,start+= 1)); // 1
        this.equipmentType=et.getEquipmentType();
    }
    public String getTachographApplicationId() {
        return tachographApplicationId;
    }

    public void setTachographApplicationId(String tachographApplicationId) {
        this.tachographApplicationId = tachographApplicationId;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
}
