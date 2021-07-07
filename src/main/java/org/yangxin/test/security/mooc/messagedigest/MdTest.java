package org.yangxin.test.security.mooc.messagedigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yangxin
 * 2020/09/29 15:47
 */
public class MdTest {

    private static final String SRC = "imooc security md";

    private static void jdkMd5() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = messageDigest.digest(SRC.getBytes());
            System.out.println("jdkMD5: " + Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void jdkMd2() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            byte[] md2Bytes = messageDigest.digest(SRC.getBytes());
            System.out.println("jdkMD2: " + Hex.encodeHexString(md2Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void bcMd4() {
        Digest digest = new MD4Digest();
        digest.update(SRC.getBytes(), 0, SRC.getBytes().length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes, 0);
        System.out.println("BC MD4: " + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }

    private static void bcMd5() {
        Digest digest = new MD5Digest();
        digest.update(SRC.getBytes(), 0, SRC.getBytes().length);
        byte[] md5Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md5Bytes, 0);
        System.out.println("BC MD5: " + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
    }

    public static void ccMd5() {
        System.out.println("CC MD5: " + DigestUtils.md5Hex(SRC.getBytes()));
    }

    public static void ccMd2() {
        System.out.println("CC MD2: " + DigestUtils.md2Hex(SRC.getBytes()));
    }

    public static void main(String[] args) {
//        jdkMd5();
//        jdkMd2();
//        bcMd4();
//        bcMd5();
//        ccMd5();
//        ccMd2();

        final String clientId = "openapi_test";
        final String timestamp = "20150610103030120";
        final String key = "openapi_test";
        System.out.println(DigestUtils.md5Hex(timestamp + clientId + key));
    }
}
