package org.yangxin.test.rpc.diy.server;

import java.io.IOException;

/**
 * @author yangxin
 * 2021/2/17 下午9:53
 */
public interface Server {

    /**
     * 服务启动
     *
     * @throws IOException IOException
     */
    void start() throws IOException;

    /**
     * 注册
     *
     * @param serviceInterface serviceInterface
     * @param impl impl
     */
    void register(Class<?> serviceInterface, Class<?> impl);
}
