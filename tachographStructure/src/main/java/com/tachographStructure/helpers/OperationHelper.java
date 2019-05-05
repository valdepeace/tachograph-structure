package com.tachographStructure.helpers;

public class OperationHelper {

    /**
     * Anteriormente llamado "tramaabytes"
     * @param s
     * @return
     */
    public static byte[] hexToByteAr(String s) {
        String[] tramas = s.split(",");
        byte[] tramaint = new byte[tramas.length];
        for (int i = 0; i < tramas.length; i++) {
            tramaint[i] = hexToByte(tramas[i]);
        }
        return tramaint;
    }

    /**
     * Anteriormente llamado "byteFromHex"
     * @param s
     * @return
     * @throws IllegalArgumentException
     */
    public static byte hexToByte(String s) throws IllegalArgumentException {
        int i = Integer.parseInt(s, 16);
        if (i >= 0 && i <= 255) {
            return (byte) i;
        }
        throw new IllegalArgumentException("input string " + s + " does not fit into a Byte");
    }
}
