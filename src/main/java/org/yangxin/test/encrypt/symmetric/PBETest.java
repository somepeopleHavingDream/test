package org.yangxin.test.encrypt.symmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * @author yangxin
 * 2020/09/29 17:38
 */
public class PBETest {

    private static final String src = "imooc security pbe";

    private static void jdkPBE() {
        // 初始化盐
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(8);

        // 口令与密钥
        String password = "imooc";
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk pbe encrypt: " + Base64.encodeBase64String(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk pbe decrypt: " + new String(result));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jdkPBE();
    }
}
