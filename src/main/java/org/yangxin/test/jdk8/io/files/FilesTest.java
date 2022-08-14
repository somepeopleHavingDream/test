package org.yangxin.test.jdk8.io.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author yangxin
 * 2021/11/28 下午12:58
 */
@SuppressWarnings({"CommentedOutCode", "AlibabaRemoveCommentedCode", "resource"})
public class FilesTest {

    public static void main(String[] args) throws IOException {
        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
    }

    /**
     * java.nio.file.Files#copy(java.nio.file.Path, java.nio.file.Path, java.nio.file.CopyOption...)
     * @throws IOException 输入输出异常
     */
    private static void test6() throws IOException {
        Files.copy(Paths.get("/home/yangxin/IdeaProjects/test/file/big-table.txt"),
                Paths.get("/home/yangxin/IdeaProjects/test/file/big-table1.txt"));
    }

    /**
     * java.nio.file.Files#createFile(java.nio.file.Path, java.nio.file.attribute.FileAttribute[])
     * @throws IOException 输入输出异常
     */
    private static void test5() throws IOException {
        String directory = "/home/yangxin/IdeaProjects/test/file/tmp";
        Files.createDirectories(Paths.get(directory));
        if (Files.exists(Paths.get(directory))) {
            Files.createFile(Paths.get(directory, "test.txt"));
        }
    }

    /**
     * 遍历一个文件夹
     * java.nio.file.Files#newDirectoryStream(java.nio.file.Path)
     * java.nio.file.Files#list(java.nio.file.Path)
     */
    private static void test4() {
        Path directoryPath = Paths.get("/home/yangxin/IdeaProjects/test");
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
//            for (Path path : stream) {
//                System.out.println(path.getFileName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (Stream<Path> stream = Files.list(directoryPath)) {
            stream.forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * java.nio.file.Files#newBufferedWriter(java.nio.file.Path, java.nio.file.OpenOption...)
     * @throws IOException 输入输出异常
     */
    private static void test3() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths
                .get("/home/yangxin/IdeaProjects/test/file/mystuff.txt"));
        writer.write("测试文件写操作");
        writer.flush();
        writer.close();
    }

    /**
     * java.nio.file.Files#newBufferedReader(java.nio.file.Path)
     * @throws IOException 输入输出异常
     */
    private static void test2() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths
                .get("/home/yangxin/IdeaProjects/test/file/big-table.txt"));
        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println(s);
        }
    }

    /**
     * java.nio.file.Files#createFile(java.nio.file.Path, java.nio.file.attribute.FileAttribute[])
     * @throws IOException 输入输出异常
     */
    private static void test1() throws IOException {
        Path path = Paths.get("/home/yangxin/IdeaProjects/test/file/mystuff.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}
