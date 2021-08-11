package org.yangxin.test.netty.rpc.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2021/8/10 11:13
 */
public class MessageHandlers {

    private final Map<String, IMessageHandler<?>> handlerMap = new HashMap<>();
    private IMessageHandler<MessageInput> defaultHandler;

    public void register(String type, IMessageHandler<?> handler) {
        handlerMap.put(type, handler);
    }

    public MessageHandlers defaultHandler(IMessageHandler<MessageInput> defaultHandler) {
        this.defaultHandler = defaultHandler;
        return this;
    }

    public IMessageHandler<MessageInput> defaultHandler() {
        return defaultHandler;
    }

    public IMessageHandler<?> get(String type) {
        return handlerMap.get(type);
    }
}
