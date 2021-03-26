package org.yangxin.test.zip;

import lombok.AllArgsConstructor;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author yangxin
 * 2021/03/25 16:55
 */
@AllArgsConstructor
public class ZipTest {

    /**
     * 目的地zip文件
     */
    private final String zipFileName;

    /**
     * 源文件（带压缩的文件或文件夹）
     */
    private final String sourceFileName;

    public void zip() {
        System.out.println("压缩中...");

        // 创建zip输出流
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));

            // 创建缓冲输出流
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);

            // 需要压缩的文件
            File sourceFile = new File(sourceFileName);

            // 压缩
            compress(zipOutputStream, bufferedOutputStream, sourceFile, sourceFile.getName());

            // 关闭输出流
            bufferedOutputStream.close();
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩文件
     */
    private void compress(ZipOutputStream zipOutputStream,
                          BufferedOutputStream bufferedOutputStream,
                          File sourceFile,
                          String name) {
        // 如果路径为目录（文件夹）
        if (sourceFile.isDirectory()) {
            // 取出文件夹中的文件（或子文件夹）
            File[] files = sourceFile.listFiles();

            // 如果文件为null
            if (files == null) {
                return;
            }

            // 如果文件夹为空，则只需在目的地zip文件中写入一个目录进去
            if (files.length == 0) {
                System.out.println(name + "/");

                try {
                    zipOutputStream.putNextEntry(new ZipEntry(name + "/"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // 如果文件夹不为空，则递归调用compress，文件夹中的每个文件（或文件夹）进行压缩
                for (File file : files) {
                    compress(zipOutputStream, bufferedOutputStream, file, name + "/" + file.getName());
                }
            }
        } else {
            // 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            try {
                zipOutputStream.putNextEntry(new ZipEntry(name));
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                int tag;
                System.out.println(name);

                // 将源文件写到zip文件中
                while ((tag = bufferedInputStream.read()) != -1) {
                    zipOutputStream.write(tag);
                }

                // 关闭输入输出流
                bufferedInputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ZipTest zipTest = new ZipTest("/home/yangxin/Downloads/compress", "/home/yangxin/Downloads/测试数据/1");
        zipTest.zip();
    }
}
