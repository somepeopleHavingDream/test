package org.yangxin.test.pay.shengpay;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * 2022/12/2 11:21
 */
@SuppressWarnings("SameParameterValue")
public class SignTest {

    public static void main(String[] args) throws Exception {
        Map<String, Object> params  = new HashMap<>();
        params.put("body", "首充得200—ID1505495，扫码刷单是骗局");
        params.put("clientIp", "127.0.0.1");
        params.put("currency", "CNY");
        params.put("extra", "{\"openId\":\"123456\",\"appId\":\"\"}");
        params.put("mchId", "");
        params.put("outTradeNo", "4222120209343742315920");
        params.put("timeExpire", "20221202094437");
        params.put("totalFee", 400);
        params.put("tradeType", "wx_jsapi");
        params.put("notifyUrl", "www.baidu.com");
        params.put("nonceStr", "98f83679876247edb75ba5fbd9b67fe6");
        params.put("signType", "RSA");

        String sign = sign(params, "");
        System.out.println("sign:" + sign);

        boolean verify = verify(params, "", sign);
        System.out.println("verify:" + verify);
    }

    /**
     * 签名
     *
     * @param params 参与签名的参数
     * @param privateKey 私钥
     * @return 签名
     * @throws Exception 异常
     */
    private static String sign(Map<String, Object> params, String privateKey) throws Exception {
        String content = getSignStr(params);

        // 获取私钥对象
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
        PrivateKey priKey = kf.generatePrivate(keySpecPKCS8);

        // 对连接的字符串进行签名
        Signature sign = Signature.getInstance("SHA1WithRSA");
        sign.initSign(priKey);
        sign.update(content.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * 验签
     *
     * @param params 参与签名的参数
     * @param publicKey 公钥
     * @param signStr 签名
     * @return 验签结果
     * @throws Exception 异常
     */
    public static boolean verify(Map<String, Object> params, String publicKey, String signStr) throws Exception {
        // 获得签名字符串
        String content = getSignStr(params);

        // 获取公钥对象
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecPKCS8 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        PublicKey pubKey = kf.generatePublic(keySpecPKCS8);

        // 对连接的字符串进行签名
        Signature sign = Signature.getInstance("SHA1WithRSA");
        sign.initVerify(pubKey);
        sign.update(content.getBytes(StandardCharsets.UTF_8));
        return sign.verify(Base64.getDecoder().decode(signStr));
    }

    /**
     * 获得签名串
     *
     * @param params 参数
     * @return 签名串
     */
    private static String getSignStr(Map<String, Object> params) {
        // 过滤掉值为空的参数
        Map<String, Object> filteredParams = params.entrySet().stream()
                .filter(e -> e.getValue() != null && !"".equals(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 对参数进行排序并连接成一个字符串
        List<String> paramList = new TreeSet<>(filteredParams.keySet())
                .stream()
                .map(key -> String.format("%s=%s", key, filteredParams.get(key).toString()))
                .collect(Collectors.toList());
        return String.join("&", paramList);
    }
}
