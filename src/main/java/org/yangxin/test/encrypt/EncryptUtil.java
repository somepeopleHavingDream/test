package org.yangxin.test.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类
 *
 * @author yangxin
 * 2019/06/27 18:10
 */
public final class EncryptUtil {
    /**
     * MD5加密
     */
    public static String encodedByMD5(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] results = md.digest(source.getBytes());
            String result = bytesToHex(results);
            if (result != null) {
                return result.toUpperCase();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    /**
     * 将字节数组转换成十六进制字符的字符串
     */
    private static String bytesToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0)
            return null;
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(encodedByMD5("111111"));
    }
}
