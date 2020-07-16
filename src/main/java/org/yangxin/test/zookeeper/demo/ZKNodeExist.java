package org.yangxin.test.zookeeper.demo;

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
 * 判断阶段是否存在demo
 *
 * @author yangxin
 * 2020/07/16 10:41
 */
@NoArgsConstructor
@Data
public class ZKNodeExist implements Watcher {

    private ZooKeeper zooKeeper = null;
    public static final String ZK_SERVER_PATH = "localhost:2181";
    public static final Integer TIMEOUT = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZKNodeExist(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZKNodeExist());
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
        if (event.getType() == Event.EventType.NodeCreated) {
            System.out.println("节点创建。");
            countDownLatch.countDown();
        } else if (event.getType() == Event.EventType.NodeDataChanged) {
            System.out.println("节点数据改变");
            countDownLatch.countDown();
        } else if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("节点删除");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZKNodeExist zkNodeExist = new ZKNodeExist(ZK_SERVER_PATH);

        Stat stat = zkNodeExist.getZooKeeper().exists("/imooc-fake", true);
        if (stat != null) {
            System.out.println("查询的节点版本为dataVersion: " + stat.getVersion());
        } else {
            System.out.println("该节点不存在。");
        }

        countDownLatch.await();
    }
}
