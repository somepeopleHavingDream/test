package org.yangxin.test.rpc.server.producer;

/**
 * @author yangxin
 * 2021/2/17 下午10:34
 */
public interface ServiceProducer {

    /**
     * 发送数据
     *
     * @param data data
     * @return 发送结果
     */
    String sendData(String data);
}
