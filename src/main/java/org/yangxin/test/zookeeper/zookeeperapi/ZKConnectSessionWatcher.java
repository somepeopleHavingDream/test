package org.yangxin.test.zookeeper.zookeeperapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 恢复之前的会话连接demo演示
 *
 * @author yangxin
 * 2020/07/14 15:39
 */
public class ZKConnectSessionWatcher implements Watcher {

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, new ZKConnectSessionWatcher());

        long sessionId = zooKeeper.getSessionId();
        String ssid = "0x" + Long.toHexString(sessionId);
        System.out.println("ssid: " + ssid);
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();

        System.out.println("客户端开始连接zookeeper服务器……");
        System.out.println("连接状态：" + zooKeeper.getState());
        Thread.sleep(1000);
        System.out.println("连接状态：" + zooKeeper.getState());

        // 开始会话重连
        System.out.println("开始会话重连……");

        ZooKeeper zkSession = new ZooKeeper(zkServerPath,
                timeout,
                new ZKConnectSessionWatcher(),
                sessionId,
                sessionPasswd);
        System.out.println("重新连接状态zkSession: " + zkSession.getState());
        Thread.sleep(1000);
        System.out.println("重新连接状态zkSession: " + zkSession.getState());
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("接收到watch通知：" + event);
    }
}
