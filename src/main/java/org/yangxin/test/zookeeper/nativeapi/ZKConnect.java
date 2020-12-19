package org.yangxin.test.zookeeper.nativeapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * zookeeper连接demo演示
 *
 * @author yangxin
 * 2020/07/10 11:17
 */
//@Slf4j
public class ZKConnect implements Watcher {

//    public static final String ZK_SERVER_PATH = "192.168.3.3:2181";
    public static final String ZK_SERVER_PATH = "localhost:2181,localhost:2182,localhost:2183";

    public static final Integer timeout = 5000;

    public static void main(String[] args) throws IOException, InterruptedException {
        // 客户端和zookeeper服务端连接是一个异步的过程
        // 当连接成功后，客户端会收到一个watch通知
        //
        // 参数：
        // connectString：连接服务器的ip字符串，比如：”192.168.1.1:2181,192.168.1.2:2181,192.168.1.3:2181“，可以是一个ip，也可以是多个ip，一个ip代表单机，多个ip代表集群，也可以在ip后加路径
        // sessionTimeout：超时时间，心跳收不到了，那就超时
        // watcher：通知事件，如果有对应的事件触发，则会收到一个通知；如果不需要，那就设置为null
        // canBeReadOnly：可读，当这个物理机节点断开后，还是可以读到数据的，只是不能写，此时数据被读取到的可能是旧数据.此处建议设置为false，不推荐使用
        // sessionId：会话的id
        // sessionPasswd：会话密码，当会话丢失后，可以依据sessionId和sessionPasswd重新获取会话
        ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER_PATH, timeout, new ZKConnect());

        System.out.println("客户端开始连接zookeeper服务器……");
//        System.out.println("客户端开始连接zookeeper服务器……");
        System.out.println("连接状态：" + zooKeeper.getState());

        Thread.sleep(2000);
//        Thread.sleep(10000);

        System.out.println("连接状态：" + zooKeeper.getState());
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("接收到watcher通知：" + event);
//        System.out.println("接收到watcher通知：[{}]" + event);
    }
}