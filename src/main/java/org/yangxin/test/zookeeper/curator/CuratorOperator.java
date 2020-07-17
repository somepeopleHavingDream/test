package org.yangxin.test.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.Objects;

/**
 * @author yangxin
 * 2020/07/17 10:27
 */
public class CuratorOperator {

    public CuratorFramework curatorFramework;
    public static final String ZK_SERVER_PATH = "localhost:2181";
    public final static String ADD_PATH = "/super/imooc/d";

    /**
     * 实例化zk客户端
     */
    public CuratorOperator() {
        // curator连接zookeeper的策略：RetryNTimes
        // n：重试的次数
        // sleepMsBetweenRetries：每次重试间隔的时间
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);

        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZK_SERVER_PATH)
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace("workspace")
                .build();
        curatorFramework.start();
    }

    /**
     * 关闭zk客户端连接
     */
    public void closeZKClient() {
        if (curatorFramework != null) {
            curatorFramework.close();
            curatorFramework = null;
        }
    }

    public static void main(String[] args) throws Exception {
        // 实例化
        CuratorOperator curatorOperator = new CuratorOperator();
        CuratorFrameworkState curatorFrameworkState = curatorOperator.curatorFramework.getState();
        System.out.println("当前客户的状态：" + (Objects.equals(curatorFrameworkState, CuratorFrameworkState.STARTED)
                ? "连接中"
                : "已关闭"));

        // 创建节点
        String nodePath = "/super/mooc";

        // 为子节点添加watcher
        // PathChildrenCache：监听数据节点的增删改，会触发事件
        String childNodePathCache = nodePath;
        // cacheData：设置缓存节点的数据状态
        final PathChildrenCache childrenCache = new PathChildrenCache(curatorOperator.curatorFramework,
                childNodePathCache,
                true);

        /*
            StartMode：初始化方式
            POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件
            NORMAL：异步初始化
            BUILD_INITIAL_CACHE：同步初始化
         */
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        List<ChildData> childDataList = childrenCache.getCurrentData();
        System.out.println("当前数据节点的子节点数据列表：");
        for (ChildData childData : childDataList) {
            String s = new String(childData.getData());
            System.out.println(s);
        }

        childrenCache.getListenable().addListener((client, event) -> {
            if (event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                System.out.println("子节点初始化ok...");
            } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                String path = event.getData().getPath();
                if (path.equals(ADD_PATH)) {
                    System.out.println("添加子节点:" + event.getData().getPath());
                    System.out.println("子节点数据:" + new String(event.getData().getData()));
                } else if (path.equals("/super/imooc/e")) {
                    System.out.println("添加不正确...");
                }

            } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                System.out.println("删除子节点:" + event.getData().getPath());
            } else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                System.out.println("修改子节点路径:" + event.getData().getPath());
                System.out.println("修改子节点数据:" + new String(event.getData().getData()));
            }
        });

        Thread.sleep(100000);

        curatorOperator.closeZKClient();
        boolean isZkCuratorStarted2 = curatorOperator.curatorFramework.isStarted();
        System.out.println("当前客户的状态：" + (isZkCuratorStarted2 ? "连接中" : "已关闭"));
    }
}
