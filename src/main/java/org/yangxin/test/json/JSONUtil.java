package org.yangxin.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author yangxin
 * 2020/09/03 17:04
 */
public class JSONUtil {

    public static void main(String[] args) {
        String str = "{\"code\":1,\"msg\":\"success\",\"dataset\":{\"total\":507470,\"page\":1,\"limit\":20,\"rows\":[\"aOo2evQYIH5Uiz0bJPOdpbhWPKdKd7OaJEtXswT+AsR8X2gqzRZeyFbmq0YqpC7uFS7oqUIakdqhdwpMrj47Twq1lAolx8pOe8YEsSZHsnDdpIS12qDCX99fgZTgatW/yajLxWo/Hry/yCDx+H15G3VH1Ema6jqW1eR8xD7pUKk=\"],\"pages\":25374}}";
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject.getString("msg"));
        System.out.println(jsonObject.getString("dataset"));
        System.out.println(jsonObject.getString("rows"));
        // 这说明JSON类只能获取当前层级的某个字段的值
        System.out.println(JSON.parseObject(jsonObject.getString("dataset")).getString("rows"));
    }
}
