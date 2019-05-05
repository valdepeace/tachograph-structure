package org.tacografo.file.certificate;

import org.tacografo.file.cardblockdriver.subblock.ManufacturerCode;
import org.tacografo.tiposdatos.BCDString;
import org.tacografo.tiposdatos.Number;
import org.tacografo.tiposdatos.OctetString;

import java.util.Arrays;

/**
 * Created by Andres Carmona Gil on 18/05/2016.
 *
 * 2.35. CertificateRequestID
 * Identificacion exclusiva de una solicitud de certificado. También puede utilizarse como identificador de la clave pública
 * de una unidad intravehicular si en el momento de generar el certificado se desconoce el número de serie de la unidad
 * intravehicular a la que se refiere la clave.
 * CertificateRequestID ::= SEQUENCE {
 *      requestSerialNumber INTEGER(0..232-1)
 *      requestMonthYear BCDString(SIZE(2))
 *      crIdentifier OCTET STRING(SIZE(1))
 *      manufacturerCode ManufacturerCode
 * }
 * requestSerialNumber es un número de serie para la solicitud de certificado, exclusivo para el fabricante y para el mes
 * a que se refiere la línea siguiente.
 * requestMonthYear es la identificación del mes y el año de la solicitud de certificado.
 * Asignación de valor: codificación BCD del mes (dos dígitos) y el año (dos últimos dígitos).
 * crIdentifier: es un identificador para distinguir entre una solicitud de certificado y un número de serie ampliado.
 * Asignación de valor: ¡FFh¡.
 * manufacturerCode: es el código numérico del fabricante que solicita el certificado
 */
public class CertificateRequestID {

    private int requestSerialNumber;

    private String requestMonthYear;

    private String crIdentifier;

    private String manufactureCode;

    public CertificateRequestID() {
    }
    public CertificateRequestID(byte[] datos) {
        int start=0;
        this.requestSerialNumber= Number.getNumber(Arrays.copyOfRange(datos, start, start+= 1));
        this.requestMonthYear= BCDString.BCDtoString(Arrays.copyOfRange(datos, start, start+= 2));
        OctetString os=new OctetString(Arrays.copyOfRange(datos, start, start+= 1));
        this.crIdentifier= os.getOctetString();
        ManufacturerCode mc=new ManufacturerCode(Arrays.copyOfRange(datos, start, start+= 1));
        this.manufactureCode= mc.getManufactureCode();
    }

    public int getRequestSerialNumber() {
        return requestSerialNumber;
    }

    public void setRequestSerialNumber(int requestSerialNumber) {
        this.requestSerialNumber = requestSerialNumber;
    }

    public String getRequestMonthYear() {
        return requestMonthYear;
    }

    public void setRequestMonthYear(String requestMonthYear) {
        this.requestMonthYear = requestMonthYear;
    }

    public String getCrIdentifier() {
        return crIdentifier;
    }

    public void setCrIdentifier(String crIdentifier) {
        this.crIdentifier = crIdentifier;
    }

    public String getManufactureCode() {
        return manufactureCode;
    }

    public void setManufactureCode(String manufactureCode) {
        this.manufactureCode = manufactureCode;
    }
}
