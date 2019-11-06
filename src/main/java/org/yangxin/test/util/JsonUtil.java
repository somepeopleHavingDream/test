package org.yangxin.test.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json工具类
 *
 * @author yangxin
 * 2019/10/23 15:57
 */
public class JsonUtil {
    /**
     * 将对象转换成json
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(object);
    }
}
