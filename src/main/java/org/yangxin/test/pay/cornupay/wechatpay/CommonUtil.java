package org.yangxin.test.pay.cornupay.wechatpay;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * @author yangxin
 */
@SuppressWarnings({"DuplicatedCode", "unused"})
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

        char[] output_array = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++)
        {
            int number = bytes[j] & 0xFF;

            output_array[j * 2] = allHexArray[(number >> 4) & 0x0F];
            output_array[j * 2 + 1] = allHexArray[number & 0x0F];

        }
        return new String(output_array);
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

        char[] hex_array = hex.toCharArray();

        byte[] output = new byte[hex_array.length / 2];
        for (int j = 0; j < hex_array.length; j += 2)
        {
            char byteHigh_hex = hex_array[j];
            char byteLow_hex = hex_array[j + 1];

            int byteHigh = (allHex.indexOf(byteHigh_hex) & 0x0F) << 4;
            int byteLow = allHex.indexOf(byteLow_hex) & 0x0F;

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

    // bytes数组长度为4
    public static int byteToInt(byte[] dataInput)
    {
        if (dataInput.length != 4)
        {
            throw new RuntimeException("length != 4");
        }

        int dataOutput_xx000000 = (dataInput[0] & 0xFF) << 24;
        int dataOutput_00xx0000 = (dataInput[1] & 0xFF) << 16;
        int dataOutput_0000xx00 = (dataInput[2] & 0xFF) << 8;
        int dataOutput_000000xx = (dataInput[3] & 0xFF);
        return dataOutput_xx000000 | dataOutput_00xx0000 | dataOutput_0000xx00 | dataOutput_000000xx;
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

    public static String mapToString(Map<String, Object> map) {
        StringBuilder param = new StringBuilder();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            if (e.getValue() == null || isEmpty(e.getValue().toString())) {
                continue;
            }
            param.append(e.getKey()).append("=")
                    .append(e.getValue()).append("&");
        }
        return param.substring(0, param.toString().length() - 1);
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static String getRandomString(int len){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<len;i++){
            stringBuilder.append((int)(Math.random()*10));
        }
        return stringBuilder.toString();
    }

}
