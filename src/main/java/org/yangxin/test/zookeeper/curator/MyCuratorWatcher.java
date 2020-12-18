package org.yangxin.test.zookeeper.curator;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * @author yangxin
 * 2020/12/18 21:23
 */
public class MyCuratorWatcher implements CuratorWatcher {

    @Override
    public void process(WatchedEvent event) {
        System.out.println("触发watcher，结点路径为：" + event.getPath());
    }
}
