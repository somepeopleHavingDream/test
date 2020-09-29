package org.yangxin.test.encrypt.messagedigest;

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
public class MDTest {

    private static final String src = "imooc security md";

    private static void jdkMD5() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = messageDigest.digest(src.getBytes());
            System.out.println("jdkMD5: " + Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void jdkMD2() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");
            byte[] md2Bytes = messageDigest.digest(src.getBytes());
            System.out.println("jdkMD2: " + Hex.encodeHexString(md2Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void bcMD4() {
        Digest digest = new MD4Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes, 0);
        System.out.println("BC MD4: " + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }

    private static void bcMD5() {
        Digest digest = new MD5Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md5Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md5Bytes, 0);
        System.out.println("BC MD5: " + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
    }

    public static void ccMD5() {
        System.out.println("CC MD5: " + DigestUtils.md5Hex(src.getBytes()));
    }

    public static void ccMD2() {
        System.out.println("CC MD2: " + DigestUtils.md2Hex(src.getBytes()));
    }

    public static void main(String[] args) {
        jdkMD5();
        jdkMD2();
        bcMD4();
        bcMD5();
        ccMD5();
        ccMD2();
    }
}
