package org.yangxin.test.security.baidu;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author yangxin
 * 2021/7/7 16:16
 */
@SuppressWarnings("CommentedOutCode")
public class RsaTest {

    /**
     * RSA最大加密明文大小
     */
    private static final Integer MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final Integer MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return 私钥实例
     */
    public static PrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return 公钥
     */
    public static PublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodeKey);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param key  公钥或密钥
     * @return 密文
     */
    public static String encrypt(String data, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        int inputLength = data.getBytes().length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;

        // 对数据分段加密
        while (inputLength - offset > 0) {
            if (inputLength - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLength - offset);
            }

            byteArrayOutputStream.write(cache, 0, cache.length);
            offset = (++i) * MAX_ENCRYPT_BLOCK;
        }

        byte[] encryptedData = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        // 获取加密内容使用base64进行编码，并以UTF-8为标准转换成字符串
        // 加密后的字符串
        return Base64.encodeBase64String(encryptedData);
    }

//    /**
//     * RSA加密-私钥
//     *
//     * @param data      待加密数据
//     * @param privateKey 私钥
//     * @return 密文
//     */
//    public static String encrypt(String data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//
//        int inputLength = data.getBytes().length;
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        int offset = 0;
//        byte[] cache;
//        int i = 0;
//
//        // 对数据分段加密
//        while (inputLength - offset > 0) {
//            if (inputLength - offset > MAX_DECRYPT_BLOCK) {
//                cache = cipher.doFinal(data.getBytes(), offset, MAX_DECRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(data.getBytes(), offset, inputLength - offset);
//            }
//
//            byteArrayOutputStream.write(cache, 0, cache.length);
//            offset = (++i) * MAX_DECRYPT_BLOCK;
//        }
//
//        byte[] encryptedData = byteArrayOutputStream.toByteArray();
//        byteArrayOutputStream.close();
//
//        // 获取加密内容使用base64进行编码，并以UTF-8为标准转换成字符串
//        // 加密后的字符串
//        return Base64.encodeBase64String(encryptedData);
//    }

    /**
     * RSA解密
     *
     * @param data       密文
     * @param privateKey 密钥
     * @return 明文
     */
    public static String decrypt(String data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLength = dataBytes.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLength - offset > 0) {
            if (inputLength - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLength - offset);
            }

            byteArrayOutputStream.write(cache, 0, cache.length);
            offset = (++i) * MAX_DECRYPT_BLOCK;
        }

        byte[] decryptedData = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        // 解密后的内容
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    /**
     * 签名
     *
     * @param data       原文
     * @param privateKey 密钥
     */
    public static String sign(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());

        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原文
     * @param publicKey 公钥
     * @param sign      需要比对的签名
     * @return 是否验签成功
     */
    public static Boolean verify(String srcData, PublicKey publicKey, String sign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, SignatureException {
        test1();
    }

    private static void test1() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException, SignatureException {
        // 生成密钥对
        KeyPair keyPair = getKeyPair();
        String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
        String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));

        System.out.println("私钥：" + privateKey);
        System.out.println("公钥：" + privateKey);

        // RSA加密
        String data = "待加密的文字内容";
        String encryptData = encrypt(data, getPublicKey(publicKey));
        System.out.println("加密后的内容：" + encryptData);

        // RSA解密
        String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
        System.out.println("解密后的内容：" + decryptData);

        // RSA签名
        String sign = sign(data, getPrivateKey(privateKey));
        // RSA验签
        Boolean verify = verify(data, getPublicKey(publicKey), sign);
        System.out.println("验签结果：" + verify);
    }
}
