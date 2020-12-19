package org.yangxin.test.zookeeper.nativeapi;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 获取节点数据的demo演示
 *
 * @author yangxin
 * 2020/07/16 09:33
 */
@NoArgsConstructor
@Data
public class ZKGetNodeData implements Watcher {

    private ZooKeeper zooKeeper = null;

    public static final String ZK_SERVER_PATH = "localhost:2181";
    public static final Integer TIMEOUT = 5000;
    private static Stat stat = new Stat();

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZKGetNodeData(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZKGetNodeData());
        } catch (IOException e) {
            e.printStackTrace();

            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDataChanged) {
            ZKGetNodeData zkGetNodeData = new ZKGetNodeData(ZK_SERVER_PATH);
            try {
                byte[] responseByte = zkGetNodeData.getZooKeeper().getData("/imooc", false, stat);
                String result = new String(responseByte);
                System.out.println("更改后的值：" + result);
                System.out.println("版本号变化dversion: " + stat.getVersion());
                countDownLatch.countDown();
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZKGetNodeData zkGetNodeData = new ZKGetNodeData(ZK_SERVER_PATH);

        /*
            参数：
            path：节点路径
            watch：true或者false，注册一个watch事件
            stat：状态
         */
        byte[] responseByte = zkGetNodeData.getZooKeeper().getData("/imooc", true, stat);
        String result = new String(responseByte);
        System.out.println("当前值：" + result);
        countDownLatch.await();
    }
}
