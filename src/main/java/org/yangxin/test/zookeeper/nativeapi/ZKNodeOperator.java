package org.yangxin.test.zookeeper.nativeapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.List;

/**
 * zookeeper操作demo演示
 *
 * @author yangxin
 * 2020/07/14 15:54
 */
public class ZKNodeOperator implements Watcher {

    private ZooKeeper zooKeeper = null;

    public static final String zkServerPath = "localhost:2181";
    public static final Integer timeout = 5000;

    public ZKNodeOperator() {
    }

    public ZKNodeOperator(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, timeout, new ZKNodeOperator());
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

    /**
     * 创建zk节点
     */
    public void createZKNode(String path, byte[] data, List<ACL> aclList) {
        String result = "";

        try {
            /*
              同步或者异步创建节点，都不支持子节点的递归创建，异步有一个callback函数。
              参数：
               path：创建的路径
               data：存储的数据的byte[]
               acl：控制权限策略
                   Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
                   CREATOR_ALL_ACL --> auth:user:password:cdrwa
               createMode：节点类型，是一个枚举
                   PERSISTENT：持久节点
                   PERSISTENT_SEQUENTIAL：持久顺序节点
                   EPHEMERAL：临时节点
                   EPHEMERAL_SEQUENTIAL：临时顺序节点
             */
            String ctx = "{'create':'success'}";
            zooKeeper.create(path, data, aclList, CreateMode.PERSISTENT, new CreateCallBack(), ctx);
//            result = zooKeeper.create(path, data, aclList, CreateMode.PERSISTENT);
            System.out.println("创建节点：\t" + result + "\t成功……");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("接收到watcher通知：" + event);
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public static void main(String[] args) throws InterruptedException {
        ZKNodeOperator zkNodeOperator = new ZKNodeOperator(zkServerPath);

        // 创建节点
        //        zkNodeOperator.createZKNode("/test-delete-node", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        // 修改节点
        /*
            参数：
            path：节点路径
            data：数据
            version：数据状态
         */
//        Stat stat = zkNodeOperator.getZooKeeper().setData("/test-delete-node", "modify".getBytes(), 0);
//        System.out.println(stat.getVersion());

        // 删除节点
        String ctx = "{'delete':'success'}";
        zkNodeOperator.getZooKeeper().delete("/test-delete-node", 1, new DeleteCallBack(), ctx);
        Thread.sleep(2000);
    }
}
