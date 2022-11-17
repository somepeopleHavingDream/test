package org.yangxin.test.pay.jypay;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yangxin
 * 2022/11/16 20:25
 */
@SuppressWarnings("unused")
public class SignUtil {

    public static void main(String[] args) {
//        String sign = signMd5("{\"amount\":\"100.00\",\"busiType\":\"T0\",\"context\":\"1ca29b24-abca-4ace-8bae-277e5db79890\",\"ext\":{},\"partnerId\":\"test\",\"requestNo\":\"b1bb149d-d1e9-4db2-8e33-0f3d0f7f36cf\",\"service\":\"withdraw\",\"version\":\"1.0\"}", "06f7aab08aa2431e6dae6a156fc9e034");
    }

    /**
     * MD5摘要签名
     *
     * @param waitToSignStr 等待写入签名的字符串
     * @param key 键
     * @return Md5摘要
     */
    public static String signMd5(String waitToSignStr, String key) {
        // MD5摘要签名计算
        return DigestUtils.md5Hex(waitToSignStr + key);
    }

    /**
     * 验证MD5签名
     *
     * @param waitToSignStr 待签字符串
     * @param key 商户安全码
     * @param verifySign 待验证签名
     * @return 验签成功与否
     */
    public static boolean verifyMd5(String waitToSignStr, String key, String verifySign) {
        // MD5摘要签名计算
        String signature = DigestUtils.md5Hex(waitToSignStr + key);
        return StringUtils.equalsIgnoreCase(verifySign, signature);
    }
}
