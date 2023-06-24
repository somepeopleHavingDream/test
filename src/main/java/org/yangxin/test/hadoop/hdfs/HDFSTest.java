package org.yangxin.test.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 使用Java API操作HDFS文件系统
 *
 * 关键点
 * 1）创建Configuration
 * 2）获取FileSystem
 * 3）HDFS API的操作
 *
 * @author yangxin
 * 2023/6/23 22:46
 */
public class HDFSTest {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
//        System.setProperty("hadoop.home.dir", "C:\\Users\\yangxin\\Documents\\hadoop-2.6.0\\bin\\winutils.exe");
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.103:8020"), configuration, "root");
        Path path = new Path("/hdfsapi/test");
        boolean result = fileSystem.mkdirs(path);
        System.out.println(result);
    }
}
