package org.yangxin.test.pay.cornupay;

import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 润物支付
 *
 * @author yangxin
 * 2022/9/29 15:48
 */
@SuppressWarnings("unused")
public class CodePay {

    public static void main(String[] args) {
        // url
        String url = "http://202.100.182.26:8180/transapi/open/trans/onlinepay";
        // 私钥
        String privateKey = "6DBBC9A95B64592BCFF1F26931D6E40148DF4C11A121371FC69E7BD4CF498F4B";
        // 公钥
        String publicKey = "048292F0AE2301E3A8F8C41EAB9A12C9CEC094244FADE88FF5122F3D09927FD04341CAB4C183AB0F1454B2C6020C6BAC9FAEB93608510F50C6E5E2D07740A1349D";

        // 参数
        String version = "1";
        String serviceCode = "unionpay.qrcode";
        String merchantMemberNo = "19070816310000000125";
        String signType = "SM2";
        String sign;
        String productCode = "pay";
        String merchantOrderNo = "20220830191305000";
        long amount = 100;
        String clientIp = "127.0.0.1";
        String productName = "百事";
        String userOpenid = "1111111";
        String notifyUrl = "http://127.0.0.1/api/notifytest/notify";

        // 拼接参数
        Map<String, Object> requestMap = new LinkedHashMap<>();
        requestMap.put("version", version);
        requestMap.put("serviceCode", serviceCode);
        requestMap.put("merchantMemberNo", merchantMemberNo);
        requestMap.put("signType", signType);
        requestMap.put("sign", "");

        //计算 sign
        TreeMap<String, Object> signMap = new TreeMap<>();
        signMap.put("version", version);
        signMap.put("serviceCode", serviceCode);
        signMap.put("merchantMemberNo", merchantMemberNo);
        signMap.put("signType", signType);
        signMap.put("body.productCode", productCode);
        signMap.put("body.merchantOrderNo", merchantOrderNo);
        signMap.put("body.amount", amount);
        signMap.put("body.clientIp", clientIp);
        signMap.put("body.productName", productName);
        signMap.put("body.userOpenid", userOpenid);
        signMap.put("body.notifyUrl", notifyUrl);
        String needSign = MapUtil.mapToString(signMap);
        System.out.println(needSign);
        sign = CommonUtil.bytesToBase64(Sm2Util.sign(privateKey, needSign.getBytes(StandardCharsets.UTF_8)));
        //计算 sign 结束

        requestMap.put("sign", sign);

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("productCode", productCode);
        requestBodyMap.put("merchantOrderNo", merchantOrderNo);
        requestBodyMap.put("amount", amount);
        requestBodyMap.put("clientIp", clientIp);
        requestBodyMap.put("productName", productName);
        requestBodyMap.put("userOpenid", userOpenid);
        requestBodyMap.put("notifyUrl", notifyUrl);

        requestMap.put("body", requestBodyMap);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String requestContent = gson.toJson(requestMap);

        // 发送请求
        System.out.println(url);
        System.out.println(requestContent);
        String responseContent = HttpUtil.post(url, requestContent);
        System.out.println(responseContent);

        // code=
        // ORDERED 下单成功
        // FAIL
    }
}
