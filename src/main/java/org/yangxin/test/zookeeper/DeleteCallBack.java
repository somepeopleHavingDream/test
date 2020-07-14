package org.yangxin.test.zookeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * @author yangxin
 * 2020/07/14 16:06
 */
public class DeleteCallBack implements AsyncCallback.VoidCallback {

    @Override
    public void processResult(int rc, String path, Object ctx) {
        System.out.println("删除节点：" + path);
        System.out.println((String) ctx);
    }
}
