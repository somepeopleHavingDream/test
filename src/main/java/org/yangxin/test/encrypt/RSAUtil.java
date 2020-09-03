package org.yangxin.test.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiao
 * 2020/7/6 19:08
 */
public class RSAUtil {

    private static final Map<Integer, String> keyMap = new HashMap<>();  //用于封装随机产生的公钥与私钥

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String publicEncrypt(String str, String publicKey) throws Exception {
        String result;
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] inputArray = str.getBytes();
        int inputLength = inputArray.length;
        // 最大加密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 117;

        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache;
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        result = Base64.encodeBase64String(resultBytes);
        return result;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String privateDecrypt(String str, String privateKey) throws Exception {
        byte[] data = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(data, offSet, 128);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 128;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    public static void main(String[] args) {
        String rows = "Ae98oaQ2PSpFmg3KywTEoXJhey+2mk+sM57X1/QrGrx4xN6CV0t1funjTMyQhR60yn3PkwwvUmU1GxoKblI6VdDItXL7lOBJPush1N3UQyr1HoUIEF5SPXIUcWzhReFgHyH/aTSQyF83Gz3EMtB+1ZRKNFP0fdiUWJkMCt2YQwE=";
        try {
            System.out.println(RSAUtil.privateDecrypt(rows, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}