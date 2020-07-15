package org.yangxin.test.zookeeper.demo;

import org.apache.zookeeper.AsyncCallback;

/**
 * @author yangxin
 * 2020/07/15 11:17
 */
public class CreateCallBack implements AsyncCallback.StringCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("创建节点：" + path);
        System.out.println((String) ctx);
    }
}
