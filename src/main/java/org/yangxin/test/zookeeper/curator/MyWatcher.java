package org.yangxin.test.zookeeper.curator;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author yangxin
 * 2020/12/18 21:24
 */
public class MyWatcher implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        System.out.println("触发watcher，结点路径为：" + event.getPath());
    }
}
