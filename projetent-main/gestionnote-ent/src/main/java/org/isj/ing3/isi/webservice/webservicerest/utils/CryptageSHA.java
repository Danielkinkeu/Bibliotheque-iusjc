package org.isj.ing3.isi.webservice.webservicerest.utils;

import java.security.MessageDigest;

public class CryptageSHA {
    public static String hachage(String a) {
        String msgHash = null;
        try {

            MessageDigest md = MessageDigest.getInstance("sha-512");

            byte[] hash = md.digest(a.getBytes());
            //System.out.println("message:");
            msgHash = toHexString(hash);

            //System.out.println("message hash:" +msgHash);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return msgHash;
    }
    static String toHexString(byte[] array) {
        StringBuilder sb = new StringBuilder(array.length * 2);

        for (byte b : array) {

            int value = 0xFF & b;
            String toAppend = Integer.toHexString(value);
            sb.append(toAppend).append("-");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString().toUpperCase();
    }
}
