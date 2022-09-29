package org.yangxin.test.pay.cornupay.wechatpay;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 */
@SuppressWarnings("unused")
public class WechatAppPayRequest {
    private Integer version;
    private String serviceCode;
    private String merchantMemberNo;
    private String sign;
    private String signType;

    private String merchantOrderNo;
    private Integer amount;
    private String clientIp;
    private String productCode;
    private String tradeType;

    private Map<String,Object> body=new HashMap<>();

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getMerchantMemberNo() {
        return merchantMemberNo;
    }

    public void setMerchantMemberNo(String merchantMemberNo) {
        this.merchantMemberNo = merchantMemberNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        beforeSet("merchantOrderNo",merchantOrderNo);
        this.merchantOrderNo = merchantOrderNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        beforeSet("amount",amount);
        this.amount = amount;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        beforeSet("clientIp",clientIp);
        this.clientIp = clientIp;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        beforeSet("productCode",productCode);
        this.productCode = productCode;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        beforeSet("tradeType",tradeType);
        this.tradeType = tradeType;
    }

    private void beforeSet(String key,Object value){
        body.put(key,value);
    }
}
