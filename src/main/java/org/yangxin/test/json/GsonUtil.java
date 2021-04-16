package org.yangxin.test.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json工具类
 *
 * @author yangxin
 * 2019/10/23 15:57
 */
public class GsonUtil {

    /**
     * 将对象转换成json（不推荐，强推阿里的fastjson）
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(object);
    }
}
