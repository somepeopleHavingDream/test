package org.yangxin.test.rpc.server.producer;

/**
 * @author yangxin
 * 2021/2/17 下午10:33
 */
public class ServiceProducerImpl implements ServiceProducer {

    @Override
    public String sendData(String data) {
        return "I am service producer, the data is " + data;
    }
}
