package org.yangxin.test.classinjdk.linenumberreader;

import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yangxin
 * 2021/12/4 10:20
 */
public class LineNumberReaderTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        try (LineNumberReader reader = new LineNumberReader(
                Files.newBufferedReader(Paths.get("D:\\IdeaProjects\\test\\src\\main\\java\\org\\yangxin\\test\\classinjdk\\linenumberreader\\LineNumberReaderTest.java"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 此时，lineNumber已经从0加1了，因为每次遇到换行符就会加1
                System.out.println("第" + reader.getLineNumber() + "行：line = " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
