package org.yangxin.test.zookeeper.curator.distributedlock;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;

/**
 * @author yangxin
 * 2020/07/24 15:52
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ZKCurator {

    /**
     * zk客户端
     */
    private CuratorFramework client = null;

    /**
     * 初始化操作
     */
    public void init() {
        // 使用命名空间
        client = client.usingNamespace("zk-curator-connector");
    }

    /**
     * 判断zk是否连接
     */
    public boolean isZKAlive() {
        return client.getState() == CuratorFrameworkState.STARTED;
    }
}
