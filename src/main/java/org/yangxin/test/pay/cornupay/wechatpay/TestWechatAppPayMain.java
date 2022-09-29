package org.yangxin.test.pay.cornupay.wechatpay;

import cn.hutool.json.JSONUtil;
import org.yangxin.test.pay.cornupay.Sm2Util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yangxin
 * 2022/9/29 16:24
 */
@SuppressWarnings({"unused", "StringBufferReplaceableByString"})
public class TestWechatAppPayMain {

    public static void main(String[] args) {
        String url = "http://192.168.101.99:11000/transapi/open/trans/onlinepay";
        //商户19091014504700000247的公钥和私钥
        String demoPrivateKey = "B35541B4A9C04DA03123A33B76BF38B3826CF94CE36091A5D7908F5BFF0071C7";
        String publicKey = "04E1CAB2EAA51CFAFA5AB6AF01213B78872C2CA3AA2DFA23B8AA85EE3C4FFC92AE863A702457B46CCA2EA6DCF7985C46D833947952B9C92B66282CBF68FFBD489E";

        WechatAppPayRequest wechatAppPayRequest = new WechatAppPayRequest();
        //就微信APP支付，以下参数可以写死
        wechatAppPayRequest.setVersion(1);
        wechatAppPayRequest.setSignType("RSA2");
        wechatAppPayRequest.setServiceCode("wechat.qrcode");
        wechatAppPayRequest.setProductCode("wechat.qrcode");
        wechatAppPayRequest.setTradeType("APP");

        //以下参数自行设置
        wechatAppPayRequest.setMerchantOrderNo(CommonUtil.getRandomString(20));
        wechatAppPayRequest.setAmount(1);
        wechatAppPayRequest.setClientIp("192.168.36.148");
        wechatAppPayRequest.setMerchantMemberNo("19091014504700000247");//测试目前仅此商户可以使用

        //发送请求
        signAndPost(wechatAppPayRequest,demoPrivateKey,url);
    }

    /**
     * 组装请求并发送
     *
     * @param wechatAppPayRequest 微信app支付请求
     * @param demoPrivateKey 私钥
     * @param url 统一资源定位符
     */
    private static void signAndPost(WechatAppPayRequest wechatAppPayRequest, String demoPrivateKey, String url) {
        // 获取待签名字符串
        String data = getPreSignData(wechatAppPayRequest);
        System.out.println(data);

        // SM2签名
        String sign = CommonUtil.bytesToBase64(Sm2Util.sign(demoPrivateKey, data.getBytes()));

        // 封装 request 对象
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        sb.append("\"version\":").append(wechatAppPayRequest.getVersion()).append(","); // 数字不需要双引号
        sb.append("\"serviceCode\":" + "\"").append(wechatAppPayRequest.getServiceCode()).append("\"").append(",");
        sb.append("\"merchantMemberNo\":" + "\"").append(wechatAppPayRequest.getMerchantMemberNo()).append("\"").append(",");
        sb.append("\"sign\":" + "\"").append(sign).append("\"").append(",");
        sb.append("\"signType\":" + "\"").append(wechatAppPayRequest.getSignType()).append("\"").append(",");

        // body
        sb.append("\"body\":");
        sb.append("{");
        sb.append("\"productCode\":" + "\"").append(wechatAppPayRequest.getProductCode()).append("\"").append(",");
        sb.append("\"merchantOrderNo\":" + "\"").append(wechatAppPayRequest.getMerchantOrderNo()).append("\"").append(",");
        sb.append("\"amount\":").append(wechatAppPayRequest.getAmount()).append(","); // 数字
        sb.append("\"clientIp\":" + "\"").append(wechatAppPayRequest.getClientIp()).append("\"").append(",");
        sb.append("\"tradeType\":" + "\"").append(wechatAppPayRequest.getTradeType()).append("\"");
        sb.append("}");
        // body 结束

        sb.append("}");
        // 封装 request 对象结束

        String request = sb.toString();
        System.out.println(JSONUtil.toJsonStr(wechatAppPayRequest));
        String response = HttpUtil.postJson(url, request);
        if (response == null) {
            System.out.println("请求上游异常");
            return;
        }
        System.out.println(response);
    }

    /**
     * 获取待签名字符串
     *
     * @param wechatAppPayRequest 微信应用支付请求
     * @return 待签名字符串
     */
    public static String getPreSignData(WechatAppPayRequest wechatAppPayRequest){
        Map<String,Object> map = new HashMap<>();
        map.put("version", wechatAppPayRequest.getVersion());
        map.put("merchantMemberNo", wechatAppPayRequest.getMerchantMemberNo());
        map.put("serviceCode", wechatAppPayRequest.getServiceCode());
        map.put("signType", wechatAppPayRequest.getSignType());

        wechatAppPayRequest.getBody().forEach((key, value) -> map.put("body." + key, value));

        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        return CommonUtil.mapToString(treeMap);
    }
}
