package org.yangxin.test.security.mooc.messagedigest;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author yangxin
 * 2020/09/29 16:40
 */
public class HmacTest {

    private static final String SRC = "imooc security hmac";

    private static void jdkHmacMd5() {
        try {
            // 初始化KeyGenerator
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            // 产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获得密钥
            byte[] key = secretKey.getEncoded();

            // 还原密钥
            SecretKey restoreSecretKey = new SecretKeySpec(key, "HmacMD5");
            // 实例化MAC
            Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
            // 初始化Mac
            mac.init(restoreSecretKey);
            // 执行摘要
            byte[] hmacMD5Bytes = mac.doFinal(SRC.getBytes());
            System.out.println("jdk hmacMD5: " + Hex.encodeHexString(hmacMD5Bytes));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

//    private static void bcHmacMD5() {
//        HMac hMac = new HMac(new MD5Digest());
//        hMac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode(src)));
//        hMac.update(src.getBytes(), 0, src.getBytes().length);
//
//        // 执行摘要
//        byte[] hmacMD5Bytes = new byte[hMac.getMacSize()];
//        hMac.doFinal(hmacMD5Bytes, 0);
//
//        System.out.println("bc hmacMD5: " + org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
//    }

    public static void main(String[] args) {
        jdkHmacMd5();
//        bcHmacMD5();
    }
}
