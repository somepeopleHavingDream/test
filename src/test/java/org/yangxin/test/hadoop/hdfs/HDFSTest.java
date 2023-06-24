package org.yangxin.test.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Unit test for simple App.
 */
public class HDFSTest {

    public static final String HDFS_PATH = "hdfs://192.168.1.103:8020";
    public static final String USER = "root";
    public static FileSystem fileSystem = null;
    public static Configuration configuration = null;

    @Before
    public void setUp() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();

        /*
            构造一个访问指定HDFS系统的客户端对象
            第一个参数：HDFS的URI
            第二个参数：客户端指定的配置参数
            第三个参数：客户端的身份，就是用户名
         */
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, USER);
    }

    @After
    public void teardown() {
        configuration = null;
        fileSystem = null;
    }

    @Test
    public void mkdir() throws IOException {
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }
}
