package org.yangxin.test.zookeeper.nativeapi.util;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * @author yangxin
 * 2020/07/16 11:21
 */
public class AclUtils {

    public static String getDigestUserPassword(String id) throws NoSuchAlgorithmException {
        return DigestAuthenticationProvider.generateDigest(id);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String id = "test:test";
        String digestUserPassword = getDigestUserPassword(id);
        System.out.println(digestUserPassword);
    }
}
