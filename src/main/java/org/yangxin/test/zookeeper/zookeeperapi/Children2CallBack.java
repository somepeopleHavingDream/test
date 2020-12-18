package org.yangxin.test.zookeeper.zookeeperapi;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author yangxin
 * 2020/07/16 09:59
 */
public class Children2CallBack implements AsyncCallback.Children2Callback {

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        children.forEach(System.out::println);
        System.out.println("ChildrenCallback: " + path);
        System.out.println("ctx: " + ctx);
        System.out.println("stat: " + stat.toString());
    }
}
