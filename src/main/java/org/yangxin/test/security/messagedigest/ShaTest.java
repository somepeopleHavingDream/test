package org.yangxin.test.security.messagedigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yangxin
 * 2020/09/29 16:23
 */
public class ShaTest {

    private static final String SRC = "imooc security sha";

    private static void jdkSha1() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(SRC.getBytes());
            System.out.println("jdk sha-1: " + Hex.encodeHexString(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void bcSha1() {
        Digest digest = new SHA1Digest();
        digest.update(SRC.getBytes(), 0, SRC.getBytes().length);
        byte[] sha1Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Bytes, 0);
        System.out.println("bc sha-1: " + org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));
    }

    private static void bcSha224() {
        Digest digest = new SHA224Digest();
        digest.update(SRC.getBytes(), 0, SRC.getBytes().length);
        byte[] sha224Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha224Bytes, 0);
        System.out.println("bc sha-224: " + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
    }

    private static void ccSha1() {
        System.out.println("cc sha1-1: " + DigestUtils.sha1Hex(SRC.getBytes()));
        System.out.println("cc sha1-2: " + DigestUtils.sha1Hex(SRC));
    }

    public static void main(String[] args) {
        jdkSha1();
        bcSha1();
        bcSha224();
        ccSha1();
    }
}
