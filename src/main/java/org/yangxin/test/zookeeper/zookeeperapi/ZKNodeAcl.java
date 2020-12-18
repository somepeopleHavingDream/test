package org.yangxin.test.zookeeper.zookeeperapi;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.yangxin.test.zookeeper.util.AclUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作节点acl演示
 *
 * @author yangxin
 * 2020/07/16 10:57
 */
@NoArgsConstructor
@Data
public class ZKNodeAcl implements Watcher {

    private ZooKeeper zooKeeper = null;
    public static final String ZK_SERVER_PATH = "localhost:2181";
    public static final Integer TIMEOUT = 5000;

    public ZKNodeAcl(String connectString) {
        try {
            zooKeeper = new ZooKeeper(connectString, TIMEOUT, new ZKNodeAcl());
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

    public void createZKNode(String path, byte[] data, List<ACL> aclList) {
        String result;

        try {
            /*
                同步或者异步创建节点，都不支持子节点的递归创建，异步有一个callback函数
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
            result = zooKeeper.create(path, data, aclList, CreateMode.PERSISTENT);
            System.out.println("创建节点：\t" + result + "\t成功。");
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ZKNodeAcl zkNodeAcl = new ZKNodeAcl(ZK_SERVER_PATH);

        // acl 任何人都可以访问
//        zkNodeAcl.createZKNode("/aclmooc", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        // 自定义用户认证访问
        List<ACL> aclList = new ArrayList<>();
        Id test1 = new Id("digest", AclUtils.getDigestUserPassword("test1:123456"));
        Id test2 = new Id("digest", AclUtils.getDigestUserPassword("test2:123456"));
        aclList.add(new ACL(ZooDefs.Perms.ALL, test1));
        aclList.add(new ACL(ZooDefs.Perms.READ, test2));
        aclList.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE, test2));
        zkNodeAcl.createZKNode("/aclmooc/testdigest", "testdigest".getBytes(), aclList);

        // 验证ip是否有权限
//        zkNodeAcl.getZooKeeper().setData("/iptest-2018", "now".getBytes(), 1);
//        Stat stat = new Stat();
//        byte[] data = zkNodeAcl.getZooKeeper().getData("/iptest-2018", false, stat);
//        System.out.println(new String(data));
//        System.out.println(stat.getVersion());
    }
}
