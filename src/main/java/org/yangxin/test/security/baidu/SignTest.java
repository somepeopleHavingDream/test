package org.yangxin.test.security.baidu;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author yangxin
 * 2021/5/26 10:43
 */
public class SignTest {

    private static final String PRIVATE_SECRET = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJgHMGYsspghvP+yCbjLG43CkZuQ3YJyDcmEKxvmgblITfmiTPx2b9Y2iwDT9gnLGExTDm1BL2A8VzMobjaHfiCmTbDctu680MLmpDDkVXmJOqdlXh0tcLjhN4+iDA2KkRqiHxsDpiaKT6MMBuecXQbJtPlVc1XjVhoUlzUgPCrvAgMBAAECgYAV9saYTGbfsdLOF5kYo0dve1JxaO7dFMCcgkV+z2ujKtNmeHtU54DlhZXJiytQY5Dhc10cjb6xfFDrftuFcfKCaLiy6h5ETR8jyv5He6KH/+X6qkcGTkJBYG1XvyyFO3PxoszQAs0mrLCqq0UItlCDn0G72MR9/NuvdYabGHSzEQJBAMXB1/DUvBTHHH4LiKDiaREruBb3QtP72JQS1ATVXA2v6xJzGPMWMBGQDvRfPvuCPVmbHENX+lRxMLp39OvIn6kCQQDEzYpPcuHW/7h3TYHYc+T0O6z1VKQT2Mxv92Lj35g1XqV4Oi9xrTj2DtMeV1lMx6n/3icobkCQtuvTI+AcqfTXAkB6bCz9NwUUK8sUsJktV9xJN/JnrTxetOr3h8xfDaJGCuCQdFY+rj6lsLPBTnFUC+Vk4mQVwJIE0mmjFf22NWW5AkAmsVaRGkAmui41Xoq52MdZ8WWm8lY0BLrlBJlvveU6EPqtcZskWW9KiU2euIO5IcRdpvrB6zNMgHpLD9GfMRcPAkBUWOV/dH13v8V2Y/Fzuag/y5k3/oXi/WQnIxdYbltad2xjmofJ7DbB7MJqiZZD8jlr8PCZPwRNzc5ntDStc959";

    public static void main(String[] args) throws Exception {
        test1();
    }

    private static void test1() throws Exception {
        Map<String, String> paramMap = new TreeMap<>();
        paramMap.put("version", "11");
        paramMap.put("trxid", "112094120001088317");
        paramMap.put("signtype", "RSA");
        paramMap.put("randomstr", "82712208");
        paramMap.put("cusid", "990581007426001");
        paramMap.put("appid", "00000051");

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            stringBuilder.append("&")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }

        String secretKey = " MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJgHMGYsspghvP+yCbjLG43CkZuQ3YJyDcmEKxvmgblITfmiTPx2b9Y2iwDT9gnLGExTDm1BL2A8VzMobjaHfiCmTbDctu680MLmpDDkVXmJOqdlXh0tcLjhN4+iDA2KkRqiHxsDpiaKT6MMBuecXQbJtPlVc1XjVhoUlzUgPCrvAgMBAAECgYAV9saYTGbfsdLOF5kYo0dve1JxaO7dFMCcgkV+z2ujKtNmeHtU54DlhZXJiytQY5Dhc10cjb6xfFDrftuFcfKCaLiy6h5ETR8jyv5He6KH/+X6qkcGTkJBYG1XvyyFO3PxoszQAs0mrLCqq0UItlCDn0G72MR9/NuvdYabGHSzEQJBAMXB1/DUvBTHHH4LiKDiaREruBb3QtP72JQS1ATVXA2v6xJzGPMWMBGQDvRfPvuCPVmbHENX+lRxMLp39OvIn6kCQQDEzYpPcuHW/7h3TYHYc+T0O6z1VKQT2Mxv92Lj35g1XqV4Oi9xrTj2DtMeV1lMx6n/3icobkCQtuvTI+AcqfTXAkB6bCz9NwUUK8sUsJktV9xJN/JnrTxetOr3h8xfDaJGCuCQdFY+rj6lsLPBTnFUC+Vk4mQVwJIE0mmjFf22NWW5AkAmsVaRGkAmui41Xoq52MdZ8WWm8lY0BLrlBJlvveU6EPqtcZskWW9KiU2euIO5IcRdpvrB6zNMgHpLD9GfMRcPAkBUWOV/dH13v8V2Y/Fzuag/y5k3/oXi/WQnIxdYbltad2xjmofJ7DbB7MJqiZZD8jlr8PCZPwRNzc5ntDStc959";

        String s = stringBuilder.substring(1);
        s = " " + s;
        String encrypt = RsaTest.encrypt(s, RsaTest.getPrivateKey(secretKey));

        System.out.println(encrypt);
        System.out.println(RsaTest.sign(encrypt, RsaTest.getPrivateKey(secretKey)));
    }
}
