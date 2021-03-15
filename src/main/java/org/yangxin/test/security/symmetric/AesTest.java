package org.yangxin.test.security.symmetric;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author yangxin
 * 2020/09/29 17:23
 */
@SuppressWarnings("CommentedOutCode")
public class AesTest {

    private static final String SRC = "imooc security aes";

    /**
     * 向量（密钥偏移量）
     */
    private static final String IV = "5075428636499153";

    /*
        慕课代码
     */

    private static void jdkAes() {
        try {
            // 生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // key转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(SRC.getBytes());
            System.out.println("jdk aes encrypt: " + Base64.encodeBase64String(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("jdk aes decrypt: " + new String(result));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    /*
        不带向量加密
     */

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, getAesKey(encryptKey)));
    }

    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] aesEncryptToBytes(String content, byte[] encryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenInit(keyGenerator, encryptKey);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));

        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }

    /*
        不带向量解密
     */

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), getAesKey(decryptKey));
    }

    public static byte[] base64Decode(String base64Code) {
        return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, byte[] decryptKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenInit(keyGenerator, decryptKey);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes, StandardCharsets.UTF_8);
    }

    public static void keyGenInit(KeyGenerator keyGen, byte[] bytes)
            throws NoSuchAlgorithmException {
        // 1.防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(bytes);
        // 2.根据密钥初始化密钥生成器
        keyGen.init(128, secureRandom);
    }

    public static byte[] getAesKey(String encodingAesKey) {
        return Base64.decodeBase64(encodingAesKey + "=");
    }

    /*
        带向量加密
     */

    public static String encrypt(String strKey, String strIn) throws Exception {
        SecretKeySpec sKeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
        byte[] encrypted = cipher.doFinal(strIn.getBytes(StandardCharsets.UTF_8));
        return org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
    }

    private static SecretKeySpec getKey(String strKey) {
        byte[] strKeyBytes = strKey.getBytes(StandardCharsets.UTF_8);
        byte[] arrB = new byte[16];
        for (int i = 0; i < strKeyBytes.length && i < arrB.length; i++) {
            arrB[i] = strKeyBytes[i];
        }
        return new SecretKeySpec(arrB, "AES");
    }

    /*
        带向量解密
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

    public static void main(String[] args) throws Exception {
//        jdkAes();
//        aesWithoutIv();
//        aesWithIv();
    }

    private static void aesWithIv() throws Exception {
        final String content = "{\"clsId\":\"3年2班\",\"thirdId\":\"1\",\"organCode\":\"20202\",\"xq\":1,\"courseId\":\"0302\"}";
        final String key = "COHeJfoWQgaYBuna";
        String encrypt = encrypt(key, content);
        System.out.println(encrypt);

        System.out.println(decrypt(key, encrypt));
    }

    private static void aesWithoutIv() throws Exception {
        final String content = "{\"clsId\":\"3年2班\",\"thirdId\":\"1\",\"organCode\":\"20202\",\"xq\":1,\"courseId\":\"0302\"}";
        final String key = "COHeJfoWQgaYBuna";
        String encryptStr = aesEncrypt(content, key);
        System.out.println(encryptStr);

        String decryptStr = aesDecrypt(encryptStr, key);
        System.out.println(decryptStr);
    }
}
