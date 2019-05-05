package com.tachographStructure.file.driverCardBlock.subBlocks;

/**
 * 2.71. NationAlpha
 * Referencia alfabética a un país, con arreglo a la codificación convencional de países que se utiliza en los adhesivos de
 * parachoques y/o en los documentos de seguro armonizados internacionalmente (tarjeta verde).
 * NationAlpha ::= IA5String(SIZE(3))
 * Asignación de valor:
 * ' ' No hay información disponible
 * 'A' Austria,
 * 'AL' Albania,
 * 'AND' Andorra,
 * 'ARM' Armenia,
 * 'AZ' Azerbaiyán,
 * 'B' Bélgica,
 * 'BG' Bulgaria,
 * 'BIH' Bosnia y Hercegovina,
 * 'BY' Bielorrusia,
 * 'CH' Suiza,
 * 'CY' Chipre,
 * 'CZ' República Checa,
 * 'D' Alemania,
 * 'DK' Dinamarca,
 * 'E' España,
 * 'EST' Estonia,
 * 'F' Francia,
 * 'FIN' Finlandia,
 * 'FL' Liechtenstein,
 * 'FR' Islas Feroe,
 * 'UK' Reino Unido, Alderney, Guernsey, Jersey, Isla de Man, Gibraltar,
 * 'GE' Georgia,
 * 'GR' Grecia,
 * 'H' Hungría,
 * 'HR' Croacia,
 * 'I' Italia,
 * 'IRL' Irlanda,
 * 'IS' Islandia,
 * 'KZ' Kazajistán,
 * 'L' Luxemburgo,
 * 'LT' Lituania,
 * 'LV' Letonia,
 * 'M' Malta,
 * 'MC' Mónaco
 * 'MD' República de Moldavia,
 * 'MK' Macedonia,
 * 'N' Noruega,
 * 'NL' Países Bajos,
 * 'P' Portugal,
 * 'PL' Polonia,
 * 'RO' Rumania,
 * 'RSM' San Marino,
 * 'RUS' Federación Rusa,
 * 'S' Suecia,
 * 'SK' Eslovaquia,
 * 'SLO' Eslovenia,
 * 'TM' Turkmenistán,
 * 'TR' Turquía,
 * 'UA' Ucrania,
 * 'V' Vaticano,
 * 'YU' Yugoslavia,
 * 'UNK' Desconocido,
 * 'EC' Comunidad Europea,
 * 'EUR' Resto de Europa,
 * 'WLD' Resto del mundo.
 */

public class NationAlpha {

    private short nationAlpha;

    protected NationAlpha() {
    }
    /**
     * Constructor que asigna los bytes que le corresponda a cada propiedad y lo interpreta
     * segun  el tipo de propiedad.
     * @param datos array de bytes
     */
    public  NationAlpha(byte[] datos) {
        this.nationAlpha=datos[0];
    }
    public String getNationWord(){
        String cadena="";
        switch (this.nationAlpha) {
            case 0x00:
                cadena = "No hay informacion disponible (00)H";
                break;
            case 0x01:
                cadena = "A"; //"Austria (01)H";
                break;
            case 0x02:
                cadena = "AL"; //"Albania (02)H";
                break;
            case 0x03:
                cadena = "AND"; //"Andorra (03)H";
                break;
            case 0x04:
                cadena = "ARM"; //"Armenia (04)H";
                break;
            case 0x05:
                cadena = "AZ"; //"Azerbaiyan (05)H";
                break;
            case 0x06:
                cadena = "B"; //"Belgica (06)H";
                break;
            case 0x07:
                cadena = "BG"; //"Bulgaria (07)H";
                break;
            case 0x08:
                cadena = "BIH"; //"Bosnia y Hercegovina (08)H";
                break;
            case 0x09:
                cadena = "BY"; //"Bielorrusia (09)H";
                break;
            case 0x0a:
                cadena = "CH"; //"Suiza (0A)H";
                break;
            case 0x0b:
                cadena = "CY"; //"Chipre (0B)H";
                break;
            case 0x0c:
                cadena = "CZ"; //"Republica Checa (0C)H";
                break;
            case 0x0d:
                cadena = "D"; //"Alemania (0D)H";
                break;
            case 0x0e:
                cadena = "DK"; //"Dinamarca (0E)H";
                break;
            case 0x0f:
                cadena = "E"; //"España (0F)H";
                break;
            case 0x10:
                cadena = "EST"; //"Estonia (10)H";
                break;
            case 0x11:
                cadena = "F"; //"Francia (11)H";
                break;
            case 0x12:
                cadena = "FIN"; //"Finlandia (12)H";
                break;
            case 0x13:
                cadena = "FL"; //"Liechtenstein (13)H";
                break;
            case 0x14:
                cadena = "FR"; //"Islas Feroe (14)H";
                break;
            case 0x15:
                cadena = "UK"; //"Reino Unido (15)H";
                break;
            case 0x16:
                cadena = "GE"; //"Georgia (16)H";
                break;
            case 0x17:
                cadena = "GR"; //"Grecia (17)H";
                break;
            case 0x18:
                cadena = "H"; //"Hungria (18)H,";
                break;
            case 0x19:
                cadena = "HR"; //"Croacia (19)H";
                break;
            case 0x1a:
                cadena = "I"; //"Italia (1A)H";
                break;
            case 0x1b:
                cadena = "IRL"; //"poikl Irlanda (1B)H";
                break;
            case 0x1c:
                cadena = "IS"; //"Islandia (1C)H";
            case 0x1d:
                cadena = "KZ"; //"Kazajistan (1D)H";
                break;
            case 0x1e:
                cadena = "L"; //"Luxemburgo (1E)H";
                break;
            case 0x1f:
                cadena = "LT"; //"Lituania (1F)H";
                break;
            case 0x20:
                cadena = "LV"; //"Letonia (20)H";
                break;
            case 0x21:
                cadena = "M"; //"Malta (21)H";
                break;
            case 0x22:
                cadena = "MC"; //"Monaco (22)H";
                break;
            case 0x23:
                cadena = "MD"; //"Republica de Moldavia (23)H";
                break;
            case 0x24:
                cadena = "MK"; //"Macedonia (24)H";
                break;
            case 0x25:
                cadena = "N"; //"Noruega (25)H";
                break;
            case 0x26:
                cadena = "NL"; //"Paises Bajos (26)H";
                break;
            case 0x27:
                cadena = "P"; //"Portugal (27)H";
                break;
            case 0x28:
                cadena = "PL"; //"Polonia (28)H";
                break;
            case 0x29:
                cadena = "RO"; //"Rumania (29)H";
                break;
            case 0x2a:
                cadena = "RSM"; //"San Marino (2A)H";
                break;
            case 0x2b:
                cadena = "RUS"; //"Federacion Rusa (2B)H";
                break;
            case 0x2c:
                cadena = "S"; //"Suecia (2C)H";
                break;
            case 0x2d:
                cadena = "SK"; //"Eslovaquia (2D)H";
                break;
            case 0x2e:
                cadena = "SLO"; //"Eslovenia (2E)H";
                break;
            case 0x2f:
                cadena = "TM"; //"Turkmenistan (2F)H";
                break;
            case 0x30:
                cadena = "TR"; //"Turquia (30)H";
                break;
            case 0x31:
                cadena = "UA"; //"Ucrania (31)H";
                break;
            case 0x32:
                cadena = "V";//"Vaticano (32)H";
                break;
            case 0x33:
                cadena = "YU";//"Yugoslavia (33)H";
                break;
            case (byte) 0xFC:
                cadena = "DDD"; //"RFU (34..FC)H";
                break;
            case (byte) 0xfd:
                cadena = "EC"; //"Comunidad Europea (FD)H";
                break;
            case (byte) 0xfe:
                cadena = "EUR"; //"Resto de Europa (FE)H";
                break;
            case (byte) 0xff:
                cadena = "WLD"; //"Resto del mundo (FF)H";
                break;
                default:
                    cadena = "UNK"; // Desconocido
                    break;
        }

        return cadena;
    }
}
