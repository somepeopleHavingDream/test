package org.yangxin.test.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.yangxin.test.zookeeper.nativeapi.util.AclUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin
 * 2020/12/19 16:06
 */
public class CuratorAcl {

    private final CuratorFramework CLIENT;
    private static final String ZK_SERVER_PATH = "localhost:2181";

    public CuratorAcl() {
        CLIENT = CuratorFrameworkFactory.builder()
                // 登录认证
                .authorization("digest", "mooc1:123456".getBytes())
                .connectString(ZK_SERVER_PATH)
                .sessionTimeoutMs(10000)
                .retryPolicy(new RetryNTimes(3, 5000))
                .namespace("workspace")
                .build();
        CLIENT.start();
    }

    public void closeZKClient() {
        if (CLIENT != null) {
            CLIENT.close();
        }
    }

    public static void main(String[] args) throws Exception {
        // 实例化
        CuratorAcl curatorAcl = new CuratorAcl();
        System.out.println("当前客户端的状态：" + (curatorAcl.CLIENT.getState() == CuratorFrameworkState.STARTED ? "连接中" : "已关闭"));

        String nodePath = "/acl/father/child/sub";

        List<ACL> aclList = new ArrayList<>();
        Id mooc1 = new Id("digest", AclUtils.getDigestUserPassword("mooc1:123456"));
        Id mooc2 = new Id("digest", AclUtils.getDigestUserPassword("mooc2:123456"));
        aclList.add(new ACL(ZooDefs.Perms.ALL, mooc1));
        aclList.add(new ACL(ZooDefs.Perms.READ, mooc2));
        aclList.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE, mooc2));

        // 创建结点
//        byte[] data = "spiderman".getBytes();
//        curatorAcl.CLIENT.create()
//                .creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(aclList)
//                .forPath(nodePath, data);

        curatorAcl.CLIENT.setACL()
                .withACL(aclList)
                .forPath("/curatorNode");
    }
}
