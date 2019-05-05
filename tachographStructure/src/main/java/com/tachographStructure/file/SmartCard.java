package com.tachographStructure.file;

import javax.smartcardio.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import com.tachographStructure.file.CardBlockFile.*;
import com.tachographStructure.file.driverCardBlock.Fid;
import com.tachographStructure.file.driverCardBlock.subBlocks.APDUCommand;
import com.tachographStructure.helpers.OperationHelper;

public class SmartCard {

    private TerminalFactory factory;

    private List<CardTerminal> terminals;

    private CardTerminal terminal;

    private Card card;

    private CardChannel channel;

    private int numSocket;

    /**
     * TODO TODO descripcion implementar errores de la smarcard segun documentacion
     */
    public SmartCard() {

        this.factory = TerminalFactory.getDefault();
        this.terminals = null;
        try {
            this.terminals = this.factory.terminals().list();
        } catch (CardException e1) {
            e1.printStackTrace();
        }
        System.out.println("Terminals: " + terminals);
        // get the first terminal
        this.terminal = this.terminals.get(0);
        // establish a connection with the card
        this.card = null;
        try {
            this.card = this.terminal.connect("T=0");
        } catch (CardException e1) {
            e1.printStackTrace();
        }
        System.out.println("card: " + card);
        this.channel = this.card.getBasicChannel();
    }

    /**
     * TODO descripcion implementar errores de la smarcard segun documentacion
     * @return
     */
    public byte[] getByteToSmardCard(){
        ResponseAPDU r = null;
        byte[] b = null;
        byte[] headerBlock = new byte[5];
        ByteArrayOutputStream fileTGD = new ByteArrayOutputStream();
        for (Fid fid : Fid.values()) {
            System.out.println(fid.getId());
            if(fid.getId() != 0x3f00 && fid.getId() != 0x0500){
                //byte[] htba = OperationHelper.hexToByteAr(fid.getId());
                byte[] htba = ByteBuffer.allocate(4).putInt(fid.getId()).array();
                b = readCard(r, channel, Arrays.copyOfRange(htba,2,4));
                byte[] sizeByte = ByteBuffer.allocate(4).putInt(b.length).array();
                headerBlock[0] = htba[2];       // id file byte 1
                headerBlock[1] = htba[3];       // id file byte 2
                headerBlock[2] = 0;             // tipy file data
                headerBlock[3] = sizeByte[2];   // size file byte 1
                headerBlock[4] = sizeByte[3];   // size file byte 2
                try{
                    fileTGD.write(headerBlock);
                    fileTGD.write(b);
                    // add signature file
                    if (fid.getId() != 0x0002 && fid.getId() != 0x0005
                            && fid.getId() != 0xc100 && fid.getId() != 0xc108
                            && fid.getId() != 0x050e){
                        performHashFile(r, channel);
                        b = signature(r, channel);
                        sizeByte = ByteBuffer.allocate(4).putInt(b.length).array();
                        headerBlock[0] = htba[2];
                        headerBlock[1] = htba[3];
                        headerBlock[2] = 1;
                        headerBlock[3] = sizeByte[2];
                        headerBlock[4] = sizeByte[3];
                        fileTGD.write(headerBlock);
                        fileTGD.write(b);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return fileTGD.toByteArray();
    }
    /**
     * TODO descripcion implementar errores de la smarcard segun documentacion
     * @return
     */
    public static byte[] readCard( ResponseAPDU r, CardChannel channel, byte[] fid){
        ByteArrayOutputStream dataResponseBuffer = new ByteArrayOutputStream();
        try {
            if ((fid[0] != 0x00 && fid[1] != 0x02) && (fid[0] != 0x00 && fid[1] != 0x05))
                //select MF - Solo para posicionarse
                r = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x04, 0x0C, OperationHelper.hexToByteAr("ff,54,41,43,48,4f"), 0x00, 0x06));
            // select EF
            r = channel.transmit(new CommandAPDU(0x00, APDUCommand.SELECT_FILE.getCommand(), 0x02, 0x0C, fid, 0x00, 0x02));
            boolean end = true;
            int p1 = 0x00;
            int p2 = 0x00;
            Byte le = Byte.valueOf((byte) 255);
            int size = 0x00;
            do {
                r = channel.transmit(new CommandAPDU(0x00, APDUCommand.READ_BINARY.getCommand(), p1, p2, (le < 0) ? le & 255 : le));
                switch (r.getSW1()) {
                    case 0x90:
                        dataResponseBuffer.write(r.getData());
                        size += (le < 0) ? le.intValue() & 255 : le.intValue();
                        byte[] offsetarray = ByteBuffer.allocate(4).putInt(size).array();
                        p1 = (offsetarray[2] < 0) ? offsetarray[2] & 255 : offsetarray[2];
                        p2 = (offsetarray[3] < 0) ? offsetarray[3] & 255 : offsetarray[3];
                        //p1 += lee;
                        //p2 += le.intValue();
                        break;
                    case 0x67: // dec 103
                        break;
                    // normal process XX = number of bytes for response enabled
                    case 0x61:
                    /*
                     nuevaLong = Byte.valueOf(codigoError[1]);
                     */
                        break;
                    // incorrect parameters (out of EF)
                    case 0x6a:
                        if (r.getSW2() == 0x86)
                            System.out.println("Parametros P1-P2 incorrectos");
                        break;
                    // incorrect long, sw2 = exact long. If not return field data
                    case 0x6c: //dec 108
                        //nuevaLong = Byte.valueOf(codigoError[1]);
                        if (r.getSW2() == 0x86)
                            System.out.println("Parametros P1-P2 incorrectos");
                        break;
                    case 0x69: //dec 108
                        end = false;
                        break;
                    case 0x6b: //dec 107
                        end = false;
                        /*
                        int div = (le < 0)? le.intValue() & 255: le.intValue() ;
                        size -= div;
                        le = Byte.valueOf((byte) (div / 2));
                        size += le;
                        if (le.byteValue() == (byte) 0) {
                            le = Byte.valueOf((byte) 1);
                            end = false;
                        }
                        offsetarray = ByteBuffer.allocate(4).putInt(size).array();
                         entero = Integer.valueOf(byteArraySize4ToInt(offsetarray) );
                        p1 = (offsetarray[2] < 0)? offsetarray[2] & 255: offsetarray[2];
                        p2 = (offsetarray[3] < 0)? offsetarray[3] & 255: offsetarray[3];
                        */
                        break;
                    /*
                    nuevaLong = Byte.valueOf((byte) ((abs(nuevaLong.intValue()) / 2) & 255));
                            if (nuevaLong.byteValue() == (byte) 0) {
                                ahora = true;
                                nuevaLong = Byte.valueOf((byte) 1);
                            }
                     */
                    default:
                        break;


                }
            } while (end);


        } catch (CardException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataResponseBuffer.toByteArray();
    }
    /**
     * TODO descripcion implementar errores de la smarcard segun documentacion
     * @return
     */
    public static void performHashFile(ResponseAPDU r, CardChannel channel) {
        try {
            r = channel.transmit(new CommandAPDU(0x80, APDUCommand.PERFORM_HASH_OF_FILE.getCommand(), 0x90, 0x00));

            switch (r.getSW1()) {
                case 0x90:
                    break;
                case 0x67: // dec 103
                    break;
                // normal process XX = number of bytes for response enabled
                case 0x61:
                    /*
                     nuevaLong = Byte.valueOf(codigoError[1]);
                     */
                    break;
                // incorrect parameters (out of EF)
                case 0x6a:
                    if (r.getSW2() == 0x86)
                        System.out.println("Parametros P1-P2 incorrectos");
                    break;
                // incorrect long, sw2 = exact long. If not return field data
                case 0x6c: //dec 108
                    //nuevaLong = Byte.valueOf(codigoError[1]);
                    if (r.getSW2() == 0x86)
                        System.out.println("Parametros P1-P2 incorrectos");
                    break;
                case 0x69: //dec 108

                    break;
                case 0x6b: //dec 107

                        /*
                        int div = (le < 0)? le.intValue() & 255: le.intValue() ;
                        size -= div;
                        le = Byte.valueOf((byte) (div / 2));
                        size += le;
                        if (le.byteValue() == (byte) 0) {
                            le = Byte.valueOf((byte) 1);
                            end = false;
                        }
                        offsetarray = ByteBuffer.allocate(4).putInt(size).array();
                         entero = Integer.valueOf(byteArraySize4ToInt(offsetarray) );
                        p1 = (offsetarray[2] < 0)? offsetarray[2] & 255: offsetarray[2];
                        p2 = (offsetarray[3] < 0)? offsetarray[3] & 255: offsetarray[3];
                        */
                    break;
                    /*
                    nuevaLong = Byte.valueOf((byte) ((abs(nuevaLong.intValue()) / 2) & 255));
                            if (nuevaLong.byteValue() == (byte) 0) {
                                ahora = true;
                                nuevaLong = Byte.valueOf((byte) 1);
                            }
                     */
                default:
                    break;
            }
        } catch (CardException e1) {
            e1.printStackTrace();
        }
    }
    /**
     * TODO descripcion implementar errores de la smarcard segun documentacion
     * @return
     */
    public static byte[] signature(ResponseAPDU r, CardChannel channel) {
        try {
            r = channel.transmit(new CommandAPDU(0x00, 0x2a, 0x9e, 0x9a, 0x80));
            switch (r.getSW1()) {
                case 0x90:
                    break;
                case 0x67: // dec 103
                    break;
                // normal process XX = number of bytes for response enabled
                case 0x61:
                    /*
                     nuevaLong = Byte.valueOf(codigoError[1]);
                     */
                    break;
                // incorrect parameters (out of EF)
                case 0x6a:
                    if (r.getSW2() == 0x86)
                        System.out.println("Parametros P1-P2 incorrectos");
                    break;
                // incorrect long, sw2 = exact long. If not return field data
                case 0x6c: //dec 108
                    //nuevaLong = Byte.valueOf(codigoError[1]);
                    if (r.getSW2() == 0x86)
                        System.out.println("Parametros P1-P2 incorrectos");
                    break;
                case 0x69: //dec 108

                    break;
                case 0x6b: //dec 107

                    break;

                default:
                    break;
            }
        } catch (CardException e1) {
            e1.printStackTrace();
        }
        return r.getData();
    }
}
