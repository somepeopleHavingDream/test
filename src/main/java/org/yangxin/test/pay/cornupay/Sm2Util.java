package org.yangxin.test.pay.cornupay;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

/**
 * @author yangxin
 */
@SuppressWarnings("DuplicatedCode")
public class Sm2Util {

    public static Sm2KeyPair generatorKeyPair() {
        SM2 sm2 = SmUtil.sm2();
        byte[] privateKeyByte = BCUtil.encodeECPrivateKey(sm2.getPrivateKey());
        byte[] publicKeyByte = ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(false);

        String publicKey = HexUtil.encodeHexStr(publicKeyByte);
        String privateKey = HexUtil.encodeHexStr(privateKeyByte);

        Sm2KeyPair entity = new Sm2KeyPair();
        entity.setPublicKey(publicKey);
        entity.setPrivateKey(privateKey);
        return entity;
    }

    public static byte[] sign(String privateKeyHex, byte[] dataInput) {
        ECPrivateKeyParameters privateKeyParameters = BCUtil.toSm2Params(privateKeyHex);

        SM2 sm2 = new SM2(privateKeyParameters, null);
        sm2.usePlainEncoding();
        sm2.setMode(SM2Engine.Mode.C1C3C2);

        return sm2.sign(dataInput, null);
    }

    public static boolean verify(String publicKeyHex, byte[] signature, byte[] dataInput) {
        if (publicKeyHex.length() == 130) {
            publicKeyHex = publicKeyHex.substring(2);
        }
        String publicKeyX = publicKeyHex.substring(0, 64);
        String publicKeyY = publicKeyHex.substring(64, 128);
        ECPublicKeyParameters ecPublicKeyParameters = BCUtil.toSm2Params(publicKeyX, publicKeyY);

        SM2 sm2 = new SM2(null, ecPublicKeyParameters);
        sm2.usePlainEncoding();
        sm2.setMode(SM2Engine.Mode.C1C3C2);

        return sm2.verify(dataInput, signature);
    }

    public static byte[] encrypt(String publicKeyHex, byte[] dataInput) {
        if (publicKeyHex.length() == 130) {
            publicKeyHex = publicKeyHex.substring(2);
        }
        String publicKeyX = publicKeyHex.substring(0, 64);
        String publicKeyY = publicKeyHex.substring(64, 128);
        ECPublicKeyParameters ecPublicKeyParameters = BCUtil.toSm2Params(publicKeyX, publicKeyY);

        SM2 sm2 = new SM2(null, ecPublicKeyParameters);
        sm2.usePlainEncoding();
        sm2.setMode(SM2Engine.Mode.C1C3C2);

        return sm2.encrypt(dataInput, KeyType.PublicKey);
    }

    public static byte[] decrypt(String privateKeyHex, byte[] dataInput) {
        ECPrivateKeyParameters privateKeyParameters = BCUtil.toSm2Params(privateKeyHex);

        SM2 sm2 = new SM2(privateKeyParameters, null);
        sm2.usePlainEncoding();
        sm2.setMode(SM2Engine.Mode.C1C3C2);

        return sm2.decrypt(dataInput, KeyType.PrivateKey);
    }

    private static void testEncryptAndDecrypt() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("生成公钥私钥");

        Sm2KeyPair keyPair = generatorKeyPair();
        String publicKey = keyPair.getPublicKey();
        String privateKey = keyPair.getPrivateKey();

        System.out.println("publicKey=" + publicKey);
        System.out.println("privateKey=" + privateKey);
        System.out.println();

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("待加密的内容");

        String text = "123456789";
        System.out.println("text=" + text);
        System.out.println();

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("加密解密");

        byte[] dataOutputEncrypted = encrypt(publicKey, text.getBytes());
        System.out.println("加密后的内容=" + CommonUtil.bytesToBase64(dataOutputEncrypted));
        System.out.println("加密后的内容=" + CommonUtil.bytesToHex(dataOutputEncrypted));
        System.out.println();

        byte[] dataOutputDecrypted = decrypt(privateKey, dataOutputEncrypted);
        System.out.println("解密后的内容=" + CommonUtil.bytesToBase64(dataOutputDecrypted));
        System.out.println("解密后的内容=" + CommonUtil.bytesToHex(dataOutputDecrypted));
        System.out.println("解密后的内容=" + new String(dataOutputDecrypted));

    }

    private static void testSignAndVerify() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("生成公钥私钥");

        Sm2KeyPair keyPair = generatorKeyPair();
        String publicKey = keyPair.getPublicKey();
        String privateKey = keyPair.getPrivateKey();

        System.out.println("publicKey=" + publicKey);
        System.out.println("privateKey=" + privateKey);
        System.out.println();

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("待加密的内容");

        String text = "123456789";
        System.out.println("text=" + text);
        System.out.println();

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("签名验签");

        byte[] dataOutputSign = sign(privateKey, text.getBytes());
        boolean dataOutputVerify = verify(publicKey, dataOutputSign, text.getBytes());
        System.out.println("dataOutputVerify=" + dataOutputVerify);

    }

    public static void main(String[] args) {
        testEncryptAndDecrypt();

        testSignAndVerify();

    }

}
