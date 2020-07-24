package org.yangxin.test.zookeeper.distributedlock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁的实现工具类
 *
 * @author yangxin
 * 2020/07/24 15:56
 */
@Slf4j
public class DistributedLock {

    /**
     * zk客户端
     */
    private CuratorFramework client = null;

    /**
     * 用于挂起当前请求，并且等待上一个分布式锁释放
     */
    private static CountDownLatch ZK_LOCK_LATCH = new CountDownLatch(1);

    /**
     * 分布式锁的总结点名
     */
    private static final String ZK_LOCK_PROJECT = "locks";

    /**
     * 分布式锁结点
     */
    private static final String DISTRIBUTED_LOCK = "distributed_lock";

    public DistributedLock(CuratorFramework client) {
        this.client = client;
    }

    public void init() {
        // 使用命名空间
        client = client.usingNamespace("ZKLocks-Namespace");

        /*
            创建zk锁的总结点，相当于eclipse的工作空间下的项目
            - ZKLocks-Namespace
                - locks
                    - distributed_lock
         */
        try {
            if (client.checkExists().forPath("/" + ZK_LOCK_PROJECT) == null) {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath("/" + ZK_LOCK_PROJECT);
            }

            // 针对zk的分布式锁节点，创建相应的watcher事件监听
            addWatcherToLock("/" + ZK_LOCK_PROJECT);
        } catch (Exception e) {
            log.error("客户端连接zookeeper服务器错误，请重试……", e);
        }
    }

    /**
     * 获得分布式锁
     */
    public void getLock() {
        // 使用死循环，当且仅当上一个锁释放并且当前请求获取锁成功后才会跳出
        while (true) {
            try {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK);
                log.info("获得分布式锁成功……");

                // 如果锁的节点能被创建成功，则锁没有被占用
                return;
            } catch (Exception e) {
                log.info("获得分布式锁失败。");

                // 如果没有获取到锁，需要重新设置同步资源值
                if (ZK_LOCK_LATCH.getCount() <= 0) {
                    ZK_LOCK_LATCH = new CountDownLatch(1);
                }
                // 阻塞线程
                try {
                    ZK_LOCK_LATCH.await();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    /**
     * 释放分布式锁
     */
    public boolean releaseLock() {
        try {
            if (client.checkExists().forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK) != null) {
                client.delete().forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        log.info("分布式锁释放完毕！");
        return true;
    }

    /**
     * 创建watcher监听
     */
    @SuppressWarnings({"deprecation", "SameParameterValue"})
    private void addWatcherToLock(String path) throws Exception {
        final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(((client1, event) -> {
            if (Objects.equals(event.getType(), PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String path1 = event.getData().getPath();
                log.info("上一个会话已释放锁或该会话已断开，节点路径为：[{}]", path1);
                if (path1.contains(DISTRIBUTED_LOCK)) {
                    log.info("释放计数器，让当前请求来获得分布式锁……");
                    ZK_LOCK_LATCH.countDown();
                }
            }
        }));
    }
}
