package org.yangxin.test.zookeeper.zookeeperapi;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 获取子节点数据的demo演示
 *
 * @author yangxin
 * 2020/07/16 09:53
 */
@NoArgsConstructor
@Data
public class ZKGetChildrenList implements Watcher {

    private ZooKeeper zooKeeper = null;
    public static final String ZK_SERVER_PATH = "localhost:2181";
    public static final Integer TIMEOUT = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZKGetChildrenList(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZKGetChildrenList());
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
        try {
            if (event.getType() == Event.EventType.NodeChildrenChanged) {
                System.out.println("NodeChildrenChanged");
                ZKGetChildrenList zkGetChildrenList = new ZKGetChildrenList(ZK_SERVER_PATH);
                List<String> childrenList = zkGetChildrenList.getZooKeeper().getChildren(event.getPath(), false);
                childrenList.forEach(System.out::println);
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeCreated) {
                System.out.println("NodeCreated.");
            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                System.out.println("NodeDataChanged.");
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                System.out.println("NodeDeleted.");
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZKGetChildrenList zkGetChildrenList = new ZKGetChildrenList(ZK_SERVER_PATH);

        /*
            参数：
            path：父节点路径
            watch：true或者false，注册一个watch事件
         */
        // 异步调用
        String ctx = "{'callback':'ChildrenCallback'}";
        zkGetChildrenList.getZooKeeper().getChildren("/imooc", true, new Children2CallBack(), ctx);
        countDownLatch.await();
    }
}
