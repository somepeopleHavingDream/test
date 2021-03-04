package org.yangxin.test.encrypt.base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author yangxin
 * 2020/09/29 15:06
 */
public class Base64Test {

    private static final String SRC = "imooc security base64";

    private static void jdkBase64() {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(SRC.getBytes());
        System.out.println("encode: " + encode);

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            System.out.println("decode: " + new String(decoder.decodeBuffer(encode)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void commonsCodesBase64() {
        byte[] encodeBytes = Base64.encodeBase64(SRC.getBytes());
        System.out.println("encode: " + new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("decode: " + new String(decodeBytes));
    }

    private static void bouncyCastleBase64() {
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(SRC.getBytes());
        System.out.println("encode: " + new String(encodeBytes));

        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
        System.out.println("decode: " + new String(decodeBytes));
    }

    public static void main(String[] args) {
        // aW1vb2Mgc2VjdXJpdHkgYmFzZTY0
        jdkBase64();
        commonsCodesBase64();
        bouncyCastleBase64();
    }
}
