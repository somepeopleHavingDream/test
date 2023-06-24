package org.yangxin.test.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        configuration.set("dfs.replication", "1");

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

    /**
     * 创建HDFS文件夹
     *
     * @throws IOException io异常
     */
    @Test
    public void mkdir() throws IOException {
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }

    /**
     * 查看HDFS内容
     */
    @Test
    public void text() throws IOException {
        FSDataInputStream in = fileSystem.open(new Path("/cdh_version.properties"));
        IOUtils.copyBytes(in, System.out, 1024);
    }

    /**
     * 写文件
     *
     * @throws IOException IO异常
     */
    @Test
    public void create() throws IOException {
//        FSDataOutputStream out = fileSystem.create(new Path("/hdfsapi/test/a.txt"));
        FSDataOutputStream out = fileSystem.create(new Path("/hdfsapi/test/b.txt"));
        out.writeUTF("hello pk b");
        out.flush();
        out.close();
    }

    /**
     * 重命名
     *
     * @throws IOException IO异常
     */
    @Test
    public void rename() throws IOException {
        Path oldPath = new Path("/hdfsapi/test/b.txt");
        Path newPath = new Path("/hdfsapi/test/c.txt");
        boolean result = fileSystem.rename(oldPath, newPath);
        System.out.println(result);
    }

    /**
     * 拷贝本地文件到HDFS文件系统
     */
    @Test
    public void copyFromLocalFile() throws IOException {
        Path src = new Path("./README.md");
        Path dst = new Path("/hdfsapi/test/");
        fileSystem.copyFromLocalFile(src, dst);
    }

    /**
     * 拷贝本地大文件到HDFS文件系统，带进度
     */
    @Test
    public void copyFromLocalBigFile() throws IOException {
        InputStream in = Files.newInputStream(Paths.get(
                "C:\\Users\\yangxin\\Downloads\\301\\hadoop-2.6.0-cdh5.15.1.tar.gz"
        ));
        FSDataOutputStream out = fileSystem.create(new Path("/hdfsapi/test/hadoop-2.6.0-cdh5.15.1.tar.gz"),
                () -> System.out.print("."));
        IOUtils.copyBytes(in, out, 4096);
    }
}
