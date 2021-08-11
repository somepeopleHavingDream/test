package org.yangxin.test.netty.rpc.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2021/8/10 16:29
 */
public class MessageRegistry {

    private final Map<String, Class<?>> clazzMap = new HashMap<>();

    public void register(String type, Class<?> clazz) {
        clazzMap.put(type, clazz);
    }

    public Class<?> get(String type) {
        return clazzMap.get(type);
    }
}
