package org.yangxin.test.netty.rpc.common;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yangxin
 * 2021/8/10 11:07
 */
@NoArgsConstructor
@AllArgsConstructor
public class MessageInput implements Serializable {

    private static final long serialVersionUID = 5802025799965961277L;

    private String type;
    private String requestId;
    private String payload;

    public String getType() {
        return type;
    }

    public String getRequestId() {
        return requestId;
    }

    public <T> T getPayload(Class<T> clazz) {
        return payload == null ? null : JSON.parseObject(payload, clazz);
    }
}
