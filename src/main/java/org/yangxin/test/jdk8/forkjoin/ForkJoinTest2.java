package org.yangxin.test.jdk8.forkjoin;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * fork-join实战异步读取磁盘文件
 *
 * @author yangxin
 * 2022/10/19 20:55
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class ForkJoinTest2 {

    /**
     * 读取文件任务对象
     */
    static class FileTask extends RecursiveAction {

        private final File file;

        public FileTask(File file) {
            this.file = file;
        }

        @Override
        protected void compute() {
            List<FileTask> fileTasks = new LinkedList<>();

            File[] files = file.listFiles();
            if (!Objects.isNull(files)) {
                for (File tmpFile : files) {
                    // 如果内部文件还是目录，则再次进行递归读取
                    if (tmpFile.isDirectory()) {
                        fileTasks.add(new FileTask(tmpFile));
                    } else {
                        System.out.println(file.getAbsolutePath());
                    }
                }

                // 递归读取目录中的文件，把任务丢进去执行，然后这里使用join进行等待完成
                for (FileTask fileTask : invokeAll(fileTasks)) {
                    fileTask.join();
                }
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileTask fileTask = new FileTask(new File("F:/"));
        // 采用异步提交，让主线程可以做其他的事情
        forkJoinPool.execute(fileTask);

        System.out.println("主线程还可以做其他的事情");
        for (int i = 0; i < 3; i++) {
            System.out.println("主线程吃了" + (i + 1) + "碗饭");
        }

        // 进行阻塞
        fileTask.join();
        System.out.println("所有线程执行完毕！");
    }
}
