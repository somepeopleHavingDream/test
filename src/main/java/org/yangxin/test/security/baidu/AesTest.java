package org.yangxin.test.security.baidu;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author yangxin
 * 2021/9/23 10:08
 */
public final class AesTest {

    /**
     * 向量（密钥偏移量）
     */
    private static final String IV = "5075428636499153";

    public static void main(String[] args) throws Exception {
        test1();
    }

    private static void test1() throws Exception {
        // 原文和密钥
        final String content = "{\"clsId\":\"3年2班\",\"thirdId\":\"1\",\"organCode\":\"20202\",\"xq\":1,\"courseId\":\"0302\"}";
        final String key = "COHeJfoWQgaYBuna";

        String encrypt = encrypt(key, content);
        System.out.println(encrypt);

        System.out.println(decrypt(key, encrypt));
    }

    /*
        向量相关（CBC模式）
     */

    /**
     * aes加密
     *
     * @param strKey 字符串密钥
     * @param strIn 原文
     * @return 密文
     * @throws Exception 异常
     */
    public static String encrypt(String strKey, String strIn) throws Exception {
        SecretKeySpec sKeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
        byte[] encrypted = cipher.doFinal(strIn.getBytes(StandardCharsets.UTF_8));
        return org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
    }

    /**
     * aes解密
     *
     * @param strKey 密钥
     * @param strIn 密文
     * @return 原文
     * @throws Exception 异常
     */
    public static String decrypt(String strKey, String strIn) throws Exception {
        SecretKeySpec sKeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
        byte[] encrypted1 = org.apache.commons.codec.binary.Base64.decodeBase64(strIn);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, StandardCharsets.UTF_8);
    }

    /**
     * 获得密钥规格
     *
     * @param strKey 密钥
     * @return 密钥规格
     */
    private static SecretKeySpec getKey(String strKey) {
        byte[] strKeyBytes = strKey.getBytes(StandardCharsets.UTF_8);
        byte[] arrB = new byte[16];
        for (int i = 0; i < strKeyBytes.length && i < arrB.length; i++) {
            arrB[i] = strKeyBytes[i];
        }
        return new SecretKeySpec(arrB, "AES");
    }
}
