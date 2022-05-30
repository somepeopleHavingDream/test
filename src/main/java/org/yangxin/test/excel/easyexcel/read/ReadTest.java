package org.yangxin.test.excel.easyexcel.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.File;

/**
 * @author yangxin
 * 2022/5/24 15:26
 */
@SuppressWarnings({"CommentedOutCode", "SpellCheckingInspection", "AlibabaRemoveCommentedCode"})
public class ReadTest {

    /**
     * 文件路径
     */
    private static final String FILE_PATH = "." + File.separator + "excel" + File.separator + "easyexcel.xlsx";

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /**
     * 读Api的拆分
     */
    private static void test1() {
        // 创建ExcelReaderBuilder对象
        ExcelReaderBuilder builder = EasyExcel.read();
        // 获取文件对象
        builder.file(FILE_PATH)
                // 指定sheet
                .sheet(0)
                // 注册监听器进行数据的解析
                .registerReadListener(new AnalysisEventListener<Object>() {

                    @Override
                    public void invoke(Object demoData, AnalysisContext analysisContext) {
                        /*
                            每解析一行数据，该方法会被调用一次
                         */

                        /*
                            如果没有指定数据模板，解析的数据会封装成LinkedHashMap返回，
                            demoData instanceof LinkedHashMap，返回true。
                         */
                        System.out.println("解析数据为：" + demoData);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        /*
                            全部解析完成后被调用
                         */
                        System.out.println("解析完成");
                    }
                })
                .doRead();
    }

    /**
     * 最简单的读（方式一）
     */
    private static void test2() {
        // 读取excel
        EasyExcel.read(FILE_PATH, DemoData.class, new AnalysisEventListener<DemoData>() {

                    @Override
                    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
                        System.out.println("解析数据为：" + demoData);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("解析完成");
                    }
                })
                .sheet()
                .doRead();
    }

    /**
     * 最简单的读（方式二）
     */
    private static void test3() {
        // 创建一个数据格式来装读取到的数据
        Class<DemoData> head = DemoData.class;
        // 创建ExcelReader对象
        ExcelReader reader = EasyExcel.read(FILE_PATH, head, new AnalysisEventListener<DemoData>() {

                    @Override
                    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
                        System.out.println("解析数据为：" + demoData);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("解析完成");
                    }
                })
                .build();

        // 创建sheet对象，并读取Excel的第一个sheet（下标从0开始），也可以根据sheet名称获取
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        // 读取sheet表格数据，参数是可变参数，可以读取多个sheet
        reader.read(sheet);
        // 读所有sheet
        //        reader.readAll();
        // 需要自己关闭流操作，在读取文件时会创建临时文件，如果不关闭，会损耗磁盘，严重的磁盘爆掉
        reader.finish();
    }

    /**
     * 读指定的多个sheet。
     * 不同sheet表格的数据模板可能不一样，这时候就需要分别构建不同的sheet对象，分别为其指定对于的数据模板
     */
    private static void test4() {
        // 构建ExcelReader对象
        ExcelReader reader = EasyExcel.read(FILE_PATH).build();
        // 构建sheet对象
        ReadSheet sheet0 = EasyExcel.readSheet(0)
                // 指定sheet0的数据模板
                .head(DemoData.class)
                .registerReadListener(new AnalysisEventListener<DemoData>() {

                    @Override
                    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
                        System.out.println("解析数据为：" + demoData);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("解析完成");
                    }
                })
                .build();

        // 读取sheet，有几个就构建几个sheet进行读取
        reader.read(sheet0);
        // 需要自己关闭流操作，在读取文件时会创建临时文件，如果不关闭，会损耗磁盘，严重的磁盘爆掉
    }
}
