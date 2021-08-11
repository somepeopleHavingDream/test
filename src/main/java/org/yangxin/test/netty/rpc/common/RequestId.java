package org.yangxin.test.netty.rpc.common;

import java.util.UUID;

/**
 * @author yangxin
 * 2021/8/11 11:44
 */
public class RequestId {

    public static String next() {
        return UUID.randomUUID().toString();
    }
}
