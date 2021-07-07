package org.yangxin.test.security.huawei;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author yangxin
 * 2021/6/16 17:52
 */
@SuppressWarnings("ALL")
public class EncryptTest {

    /**
     * 编码方式
     */
    private static final String CHARSET = "UTF-8";

    /**
     * 加密类型 256加密（AES256_CBC_PKCS5Padding）时传1,128加密（AES128_CBC_PKCS5Paddding）
     */
    private static final String ENCRYPT_TYPE_256 = "1";

    /**
     * 服务商信息界面的key值（需要替换为自己的key）
     */
    private static final String ACCESS_KEY = "xxxxxxx";

    public static void main(String[] args) throws Exception {

// ------------服务商验证请求---------------

// 将请求转换为map，模拟从request中获取参数操作（request.getParameterMap()）

        Map<String, String[]> paramsMap = getTestUrlMap();

// 加密类型 256加密（AES256_CBC_PKCS5Padding），128加密（AES128_CBC_PKCS5Padding）

        System.out.println("服务商验证请求:" + verificateRequestParams(paramsMap, ACCESS_KEY, 256));

// 需要加密的手机、密码等

        String needEncryptStr = "15905222222";

        String encryptStr = generateSaaSUsernameOrPwd(needEncryptStr, ACCESS_KEY, ENCRYPT_TYPE_256);

        System.out.println("加密的手机、密码等:" + encryptStr);
//
//// 解密
//
        String decryptStr = decryptMobilePhoneOrEMail(ACCESS_KEY, "Uh2977H2620i0K251ckE cPpVaeXADc5xJivbg==", ENCRYPT_TYPE_256);
//        String decryptStr = decryptMobilePhoneOrEMail(ACCESS_KEY, encryptStr, ENCRYPT_TYPE_256);

        System.out.println("解密的手机、密码等:" + decryptStr);
//
//// body签名
//
//        String needEncryptBody =
//
//                "{\"resultCode\":\"00000\",\"resultMsg\":\"购买成功\",\"encryptType\":\"1\",\"instanceId\":\"000bd4e1-5726-4ce9-8fe4-fd081a179304\",\"appInfo\"{\"userName\":\"3LQvu8363e5O4zqwYnXyJGWz8y+GAcu0rpM0wQ==\",\"password\":\"RY31aEnR5GMCFmt3iG1hW7UF1HK09MuAL2sgxA==\"}}";
//
//        String encryptBody = generateResponseBodySignature(ACCESS_KEY, needEncryptBody);
//
//        System.out.println("body签名:" + encryptBody);

    }

    private static Map<String, String[]> getTestUrlMap() {

// 原始请求：http://bzapic.natappfree.cc?activity=newInstance&businessId=61e834ba-7b97-4418-b8f7-e5345137278c&customerId=68cbc86abc2018ab880d92f36422fa0e&expireTime=20200727153156&orderId=CS1906666666ABCDE&productId=00301-666666-0--0&testFlag=1&timeStamp=20200727073711903&authToken=Gzbfjf9LHRBcI3bFVi++sLinCNOBF6qa7is1fvjEgYQ=

        Map<String, String[]> paramsMap = new HashMap<String, String[]>();

        paramsMap.put("activity", new String[]{"newInstance"});

        paramsMap.put("businessId", new String[]{"61e834ba-7b97-4418-b8f7-e5345137278c"});

        paramsMap.put("customerId", new String[]{"68cbc86abc2018ab880d92f36422fa0e"});

        paramsMap.put("expireTime", new String[]{"20200727153156"});

        paramsMap.put("orderId", new String[]{"CS1906666666ABCDE"});

        paramsMap.put("productId", new String[]{"00301-666666-0--0"});

        paramsMap.put("testFlag", new String[]{"1"});

        paramsMap.put("timeStamp", new String[]{"20200727073711903"});

        paramsMap.put("authToken", new String[]{"Gzbfjf9LHRBcI3bFVi++sLinCNOBF6qa7is1fvjEgYQ="});

        return paramsMap;

    }

    /**
     * 校验通知消息的合法性
     *
     * @param accessKey     接入码
     * @param encryptLength 加密长度
     * @return 验证结果
     */

    public static boolean verificateRequestParams(Map<String, String[]> paramsMap, String accessKey,

                                                  int encryptLength) {

        String timeStamp = null;

        String authToken = null;

        String[] timeStampArray = paramsMap.get("timeStamp");

        if (null != timeStampArray && timeStampArray.length > 0) {

            timeStamp = timeStampArray[0];

        }

        String[] authTokenArray = paramsMap.get("authToken");

        if (null != authTokenArray && authTokenArray.length > 0) {

            authToken = authTokenArray[0];

        }

// 对剩下的参数进行排序，拼接成加密内容

        Map<String, String[]> sortedMap = new TreeMap<>(paramsMap);

        sortedMap.remove("authToken");

        StringBuilder strBuffer = new StringBuilder();

        Set<String> keySet = sortedMap.keySet();

        for (String key : keySet) {

            String value = sortedMap.get(key)[0];

            strBuffer.append("&").append(key).append("=").append(value);

        }

// 修正消息体,去除第一个参数前面的&

        String reqParams = strBuffer.substring(1);

        String key = accessKey + timeStamp;

        String signature = null;

        try {

            signature = generateResponseBodySignature(key, reqParams);

        } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException

                | UnsupportedEncodingException e) {

        }

        return Objects.equals(authToken, signature);

    }

    public static String generateResponseBodySignature(String key, String body)

            throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {

        return base_64(hmacSHA256(key, body));

    }

    public static byte[] hmacSHA256(String macKey, String macData) {

        try {

            try {

                SecretKeySpec secret = new SecretKeySpec(macKey.getBytes(CHARSET), "HmacSHA256");

                Mac mac = Mac.getInstance("HmacSHA256");

                mac.init(secret);

                return mac.doFinal(macData.getBytes(CHARSET));

            } catch (UnsupportedEncodingException | InvalidKeyException ignored) {

            }

        } catch (NoSuchAlgorithmException ignored) {

        }

        return new byte[0];

    }

// 服务商body签名

    public static String generateSaaSUsernameOrPwd(String isvBody, String decryptAccessKey, String sEncryptType) {

        String iv = getRandomChars(16);

        int iEncryptType = 0;

        try {

            iEncryptType = Integer.parseInt(sEncryptType);

        } catch (NumberFormatException exception) {

            iEncryptType = 1;

        }

        int encryptType;

        if (1 == iEncryptType) {

            encryptType = 256;

        } else {

            encryptType = 128;

        }

        String isvEncryptBody = encryptAESCBCEncode(isvBody, decryptAccessKey, iv, encryptType);

        return iv + isvEncryptBody;

    }

    /**
     * AES CBC 256位加密
     *
     * @param content 加密内容
     * @param key     加密密钥
     * @param iv      加密盐值
     * @return 加密结果
     */

    public static String encryptAESCBCEncode(String content, String key, String iv, int encryptType) {

        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(key) || StringUtils.isEmpty(iv)) {

            return null;

        }

        try {

            byte[] encrypContent =

                    encryptAESCBC(content.getBytes(CHARSET), key.getBytes(CHARSET), iv.getBytes(CHARSET), encryptType);

            if (null != encrypContent) {

                return base_64(encrypContent);

            } else {

                return null;

            }

        } catch (UnsupportedEncodingException e) {

            return null;

        }

    }

    public static byte[] encryptAESCBC(byte[] content, byte[] keyBytes, byte[] iv, int encryptType) {

        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            secureRandom.setSeed(keyBytes);

            keyGenerator.init(encryptType, secureRandom);

            SecretKey key = keyGenerator.generateKey();

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

            return cipher.doFinal(content);

        } catch (Exception e) {

        }

        return null;

    }

    public static String base_64(byte[] bytes) {

        try {

            return new String(Base64.encodeBase64(bytes), CHARSET);

        } catch (UnsupportedEncodingException e) {

            return null;

        }

    }

    static String decryptMobilePhoneOrEMail(String accessKey, String encryptStr, String sEncryptType) {

        String iv = encryptStr.substring(0, 16);

        int iEncryptType = 1;

        try {

            iEncryptType = Integer.parseInt(sEncryptType);

        } catch (NumberFormatException exception) {

            exception.printStackTrace();

        }

        int encryptType;

        if (1 == iEncryptType) {

            encryptType = 256;

        } else {

            encryptType = 128;

        }

        String decryptBody = null;

        try {

            decryptBody = decryptAESCBCEncode(encryptStr.substring(16), accessKey, iv, encryptType);

        } catch (Exception e) {

            e.printStackTrace();

            return decryptBody;

        }

        return decryptBody;

    }

    public static String decryptAESCBCEncode(String content, String key, String iv, int encryptType)

            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,

            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(key) || StringUtils.isEmpty(iv)) {

            return null;

        }

        return new String(decryptAESCBC(org.apache.commons.codec.binary.Base64.decodeBase64(content.getBytes()),

                key.getBytes(), iv.getBytes(), encryptType));

    }

    public static byte[] decryptAESCBC(byte[] content, byte[] keyBytes, byte[] iv, int encryptType)

            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,

            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

        secureRandom.setSeed(keyBytes);

        keyGenerator.init(encryptType, secureRandom);

        SecretKey key = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

        byte[] result = cipher.doFinal(content);

        return result;

    }

    /**
     * 获取随机字符串
     *
     * @param length 字符串长度
     * @return
     * @author d00420944
     */

    public static String getRandomChars(int length) {

        StringBuilder randomCharsBuf = new StringBuilder(1024);

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {

// 字母和数字中随机

            if (random.nextInt(2) % 2 == 0) {

// 输出是大写字母还是小写字母

                int letterIndex = random.nextInt(2) % 2 == 0 ? 65 : 97;

                randomCharsBuf.append((char) (random.nextInt(26) + letterIndex));

            } else {

                randomCharsBuf.append(String.valueOf(random.nextInt(10)));

            }
        }

        return randomCharsBuf.toString();

    }
}
