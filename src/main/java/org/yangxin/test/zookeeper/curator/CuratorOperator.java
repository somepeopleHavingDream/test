package org.yangxin.test.zookeeper.curator;

import lombok.Data;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Optional;

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

        // 创建结点
        String nodePath = "/super/mooc";
//        byte[] data = "super".getBytes();
//        client.create()
//                .creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                .forPath(nodePath, data);

        // 更新结点数据
//        byte[] newData = "mooc".getBytes();
//        client.setData()
//                .withVersion(0)
//                .forPath(nodePath, newData);

        // 删除结点
//        client.delete()
//                // 如果删除失败，那么在后端还是会继续删除，直到删除成功
//                .guaranteed()
//                // 如果有子结点，仍然删除
//                .deletingChildrenIfNeeded()
//                .withVersion(1)
//                .forPath(nodePath);

        // 读取结点数据
//        Stat stat = new Stat();
//        byte[] data = client.getData()
//                .storingStatIn(stat)
//                .forPath(nodePath);
//        System.out.println("结点" + nodePath + "的数据为：" + new String(data));
//        System.out.println("该结点的版本号为：" + stat.getVersion());

        // 查询子节点
//        List<String> childNodeList = client.getChildren()
//                .forPath(nodePath);
//        System.out.println("开始打印子结点……");
//        childNodeList.forEach(System.out::println);

        // 判断结点是否存在，如果不存在则为空
//        Stat stat = client.checkExists()
//                .forPath(nodePath);
//        System.out.println(stat);

        // watcher事件，当使用usingWatcher的时候，监听只会触发一次，监听完毕后就被销毁
//        client.getData()
//                .usingWatcher(new MyCuratorWatcher())
//                .forPath(nodePath);
//        client.getData()
//                .usingWatcher(new MyWatcher())
//                .forPath(nodePath);

        // 为结点添加watcher
        // CuratorCache：监听数据结点的变更，会触发事件。（以当前结点为根节点，建立了树型结构监听树）
        CuratorCache curatorCache = CuratorCache.build(client, nodePath);
        curatorCache.start();
//        Optional<ChildData> childDataOptional = curatorCache.get(nodePath);
//        if (childDataOptional.isPresent()) {
//            System.out.println("结点初始化数据为：" + new String(childDataOptional.get().getData()));
//        } else {
//            System.out.println("结点初始化数据为空……");
//        }

        curatorCache.listenable().addListener((type, oldData, data) -> {
            if (oldData != null && data != null) {
                System.out.println("type: " + type
                        + " oldData: " + new String(oldData.getData())
                        + " data: " + new String(data.getData()));
            } else {
                System.out.println("type: " + type + " oldData: " + oldData + " data: " + data);
            }
        });

        Thread.sleep(30000);
//        Thread.sleep(3000);

        // 关闭客户端
        curatorOperator.closeZKClient();
        isZKCuratorStarted  = client.getState() == CuratorFrameworkState.STARTED;
        System.out.println("当前客户端状态：" + (isZKCuratorStarted ? "连接中" : "已关闭"));
    }
}
