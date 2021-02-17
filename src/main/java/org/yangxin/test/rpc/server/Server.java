package org.yangxin.test.rpc.server;

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

    /**
     * 服务是否正在运行
     *
     * @return 服务是否正在运行
     */
    boolean isRunning();

    /**
     * 获得端口
     *
     * @return 端口
     */
    int getPort();

    /**
     * 停止服务
     */
    void stop();
}
