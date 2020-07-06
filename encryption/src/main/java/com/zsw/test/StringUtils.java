package com.zsw.test;

/**
 * @author ZhangShaowei on 2020/5/20 14:45
 */
public class StringUtils {

    private static final char[] hexArray = "0123456789abcdef".toCharArray(); //$NON-NLS-1$


    public static void main(String[] args) {
        byte[] bytes = "123456789".getBytes();
        System.out.println(bytesToHexString(bytes));
        System.err.println(bytesToHexString1(bytes));
    }

    public static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String bytesToHexString1(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


}
