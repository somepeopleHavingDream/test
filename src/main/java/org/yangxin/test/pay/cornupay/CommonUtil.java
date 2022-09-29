package org.yangxin.test.pay.cornupay;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author yangxin
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public class CommonUtil
{

    public static String bytesToBase64(byte[] bytes)
    {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] base64ToBytes(String base64)
    {
        return Base64.getDecoder().decode(base64);
    }

    public static String bytesToHex(byte[] bytes)
    {
        final char[] allHexArray = "0123456789ABCDEF".toCharArray();

        char[] outputArray = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++)
        {
            int number = bytes[j] & 0xFF;

            outputArray[j * 2] = allHexArray[(number >> 4) & 0x0F];
            outputArray[j * 2 + 1] = allHexArray[number & 0x0F];

        }
        return new String(outputArray);
    }

    public static byte[] hexToBytes(String hex)
    {
        final String allHex = "0123456789ABCDEF";

        if (hex.length() % 2 != 0)
        {
            throw new RuntimeException("length % 2 != 0");
        }

        // 转换为大写
        hex = hex.toUpperCase();

        char[] hexArray = hex.toCharArray();

        byte[] output = new byte[hexArray.length / 2];
        for (int j = 0; j < hexArray.length; j += 2)
        {
            char byteHighHex = hexArray[j];
            char byteLowHex = hexArray[j + 1];

            int byteHigh = (allHex.indexOf(byteHighHex) & 0x0F) << 4;
            int byteLow = allHex.indexOf(byteLowHex) & 0x0F;

            output[j / 2] = (byte) (byteHigh | byteLow);
        }
        return output;
    }

    public static String base64ToHex(String base64)
    {
        return bytesToHex(base64ToBytes(base64));
    }

    public static String hexToBase64(String hex)
    {
        return bytesToBase64(hexToBytes(hex));
    }

    /**
     * "ISO-8859-1"
     */
    public static String base64ToStr(String base64)
    {

        return new String(base64ToBytes(base64), StandardCharsets.ISO_8859_1);

    }

    /**
     * "ISO-8859-1"
     */
    public static String strToBase64(String str)
    {
        return bytesToBase64(str.getBytes(StandardCharsets.ISO_8859_1));

    }

    public static int byteToInt(byte[] dataInput)
    {
        if (dataInput.length != 4)
        {
            throw new RuntimeException("length != 4");
        }

        int dataOutputXx000000 = (dataInput[0] & 0xFF) << 24;
        int dataOutput00Xx0000 = (dataInput[1] & 0xFF) << 16;
        int dataOutput0000Xx00 = (dataInput[2] & 0xFF) << 8;
        int dataOutput000000Xx = (dataInput[3] & 0xFF);
        return dataOutputXx000000 | dataOutput00Xx0000 | dataOutput0000Xx00 | dataOutput000000Xx;
    }

    public static byte[] intToByte(int dataInput)
    {
        byte[] dataOutput = new byte[4];
        dataOutput[0] = (byte) ((dataInput >> 24) & 0xFF);
        dataOutput[1] = (byte) ((dataInput >> 16) & 0xFF);
        dataOutput[2] = (byte) ((dataInput >> 8) & 0xFF);
        dataOutput[3] = (byte) ((dataInput) & 0xFF);
        return dataOutput;
    }

}
