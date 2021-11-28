package org.yangxin.test.classinjdk.io.path;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Paths类使用用例
 *
 * @author yangxin
 * 2020/05/25 14:39
 */
public class PathTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        Path path = Paths.get("/home/yangxin/IdeaProjects/test/file", "big-table.txt");
        System.out.println(path);
    }

    private static void test1() {
        // 以当前路径作为Path对象
        Path path = Paths.get(".");

        // 对应的路径
        System.out.println("path对象的对应路径：" + path);

        // 路径数量是以路径名的数量作为标准
        System.out.println("path路径数量：" + path.getNameCount());

        // 获取绝对路径
        System.out.println("path绝对路径：" + path.toAbsolutePath());

        // 获取父路径
        System.out.println("path父路径：" + path.getParent());

        // 获取path对象的文件名或者文件目录名
        System.out.println(path.getFileName());
    }
}
