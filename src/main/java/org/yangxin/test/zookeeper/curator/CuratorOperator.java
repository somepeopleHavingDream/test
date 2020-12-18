package org.yangxin.test.zookeeper.curator;

import lombok.Data;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * @author yangxin
 * 2020/12/18 20:05
 */
@Data
public class CuratorOperator {

    private final CuratorFramework client;
    private static final String ZK_SERVER_PATH = "localhost:2181";

    /**
     * 实例化zk客户端
     */
    public CuratorOperator() {
        /*
            curator连接zookeeper的策略：RetryNTimes
            n：重试的次数
            sleepMsBetweenRetries：每次重试间隔的时间
         */
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);

        client = CuratorFrameworkFactory.builder()
                .connectString(ZK_SERVER_PATH)
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace("workspace")
                .build();
        client.start();
    }

    /**
     * 关闭zk客户端连接
     */
    public void closeZKClient() {
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) throws Exception {
        // 实例化，开启客户端
        CuratorOperator curatorOperator = new CuratorOperator();
        CuratorFramework client = curatorOperator.getClient();
        boolean isZKCuratorStarted  = client.getState() == CuratorFrameworkState.STARTED;
        System.out.println("当前客户端状态：" + (isZKCuratorStarted ? "连接中" : "已关闭"));

        // 创建节点
        String nodePath = "/super/mooc";
//        byte[] data = "super".getBytes();
//        client.create()
//                .creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                .forPath(nodePath, data);

        // 更新节点数据
//        byte[] newData = "mooc".getBytes();
//        client.setData()
//                .withVersion(0)
//                .forPath(nodePath, newData);

        // 删除节点
        client.delete()
                // 如果删除失败，那么在后端还是会继续删除，直到删除成功
                .guaranteed()
                // 如果有子节点，仍然删除
                .deletingChildrenIfNeeded()
                .withVersion(1)
                .forPath(nodePath);

        Thread.sleep(3000);

        // 关闭客户端
        curatorOperator.closeZKClient();
        isZKCuratorStarted  = client.getState() == CuratorFrameworkState.STARTED;
        System.out.println("当前客户端状态：" + (isZKCuratorStarted ? "连接中" : "已关闭"));
    }
}
