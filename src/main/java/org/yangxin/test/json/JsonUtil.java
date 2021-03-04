package org.yangxin.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.yangxin.test.encrypt.symmetric.AesTest;

import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author yangxin
 * 2020/09/03 17:04
 */
public class JsonUtil {

    public static void main(String[] args) throws Exception {
//        test1();
        test2();
    }

    private static void test2() throws Exception {
        PlanCourseDTO planCourse =new PlanCourseDTO();
        planCourse.setThirdId("1234568965245dsdssds");
        planCourse.setOrganCode("15656");
        planCourse.setXq(202103L);
        planCourse.setClsId("1136aa6b88ecdea9fb977bc96e33cb48");
        planCourse.setUserId("6a584321-9659-472c-b936-636860f1b2d1");
        planCourse.setCourseId("81cc551797a443f2bac121fa71515f03");
        planCourse.setRoomId("c3ca2034a0a1241b49bcdbc7e8f1bfd4");
        planCourse.setWeek(3L);
        planCourse.setStanza(5L);
        planCourse.setCyc(0L);
        planCourse.setStartWeek(5L);
        planCourse.setEndWeek(10L);
        planCourse.setPlanDate("2021-03-03");

        String s = JSON.toJSONString(planCourse);
        String base64Encrypt = AesTest.encrypt("COHeJfoWQgaYBuna", Base64.encodeBase64String(s.getBytes(StandardCharsets.UTF_8)));
        String urlEncrypt = AesTest.encrypt("COHeJfoWQgaYBuna", URLEncoder.encode(s,"utf-8"));
        String encryptThirdId = AesTest.encrypt("COHeJfoWQgaYBuna", URLEncoder.encode("123457,123458", "utf-8"));
        System.out.println(encryptThirdId);
        String decrypt = AesTest.decrypt("COHeJfoWQgaYBuna", encryptThirdId);
        System.out.println(URLDecoder.decode(decrypt, "utf-8"));
    }

    private static void test1() {
        String str = "{\"code\":1,\"msg\":\"success\",\"dataset\":{\"total\":507470,\"page\":1,\"limit\":20,\"rows\":[\"aOo2evQYIH5Uiz0bJPOdpbhWPKdKd7OaJEtXswT+AsR8X2gqzRZeyFbmq0YqpC7uFS7oqUIakdqhdwpMrj47Twq1lAolx8pOe8YEsSZHsnDdpIS12qDCX99fgZTgatW/yajLxWo/Hry/yCDx+H15G3VH1Ema6jqW1eR8xD7pUKk=\"],\"pages\":25374}}";
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject.getString("msg"));
        System.out.println(jsonObject.getString("dataset"));
        System.out.println(jsonObject.getString("rows"));
        // 这说明JSON类只能获取当前层级的某个字段的值
        System.out.println(JSON.parseObject(jsonObject.getString("dataset")).getString("rows"));
    }
}

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
class PlanCourse implements Serializable {

    private static final long serialVersionUID = 7780091784337168648L;
    private String thirdId;
    private String organCode;
    private Long xq;
    private String clsId;
    private String userId;
    private String courseId;
    private String roomId;
    private Long week;
    private Long stanza;
    private Long cyc;
    private Long startWeek;
    private Long endWeek;
    private String planDate;
    private String flag;
    private String status;
    private String partnerType;
    private Date createTime;
    private Date modifyTime;

}

@Data
class PlanCourseDTO implements Serializable {

    private static final long serialVersionUID = 7780091784337168648L;
    private Long id;
    private String thirdId;
    private String organCode;
    private Long xq;
    private String clsId;
    private String userId;
    private String courseId;
    private String roomId;
    private Long week;
    private Long stanza;
    private Long cyc;
    private Long startWeek;
    private Long endWeek;
    private String planDate;
    private String flag;
    private String status;
    private String partnerType;
    private Date createTime;
    private Date modifyTime;

}
