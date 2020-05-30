package org.yangxin.test.io.nio;

import java.io.IOException;

/**
 * @author yangxin
 * 2020/05/25 14:54
 */
public class AClient {

    public static void main(String[] args) throws IOException {
        new NIOClient().start("AClient");
    }
}
