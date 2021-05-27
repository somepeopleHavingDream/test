package org.yangxin.test.security.sign;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yangxin
 * 2021/5/26 10:43
 */
@SuppressWarnings("ALL")
public class SignTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        test1();
        String s = "POSThttps://xxxx/testurlplatformKey=48dd3eC69id=100041203386timestamp=1464850334544kMCV4EHQrtLAb9tFGzOU8Qqr7OlcTglH";
        System.out.println(DigestUtils.md5Hex(URLEncoder.encode(s, "utf-8")));
    }

    private static void test1() throws UnsupportedEncodingException {
        // 合作方平台编码，配合sign中的密钥做合法性身份认证
        final String platformKey = "48dd3eC69";
        // unix时间戳，本次请求签名的有效时间为该时间戳+10分钟
        String timestamp = "1464850334544";
//        long timestamp = System.currentTimeMillis();
        // 调用参数签名值，与platformKey成对出现
        String sign = "";

        // 密钥
        final String secretKey = "kMCV4EHQrtLAb9tFGzOU8Qqr7OlcTglH";

        final String method = "POST";
        final String url = "https://xxxx/testurl";

        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("platformKey", platformKey);
        paramMap.put("timestamp", timestamp);
//        paramMap.put("id", 10041203386L);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method)
                .append(url);

        paramMap.forEach((k, v) -> {
            stringBuilder.append(k)
                    .append("=")
                    .append(v);
        });

        String s = stringBuilder.append(secretKey).toString();
        System.out.println(s);

        System.out.println(DigestUtils.md5Hex(URLEncoder.encode(s, "utf-8")));
    }
}
