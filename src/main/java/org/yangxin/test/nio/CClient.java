package org.yangxin.test.nio;

import java.io.IOException;

/**
 * @author yangxin
 * 2020/05/25 15:18
 */
public class CClient {

    public static void main(String[] args) throws IOException {
        new NioClient().start("CClient");
    }
}
