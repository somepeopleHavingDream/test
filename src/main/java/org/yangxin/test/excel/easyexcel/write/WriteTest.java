package org.yangxin.test.excel.easyexcel.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangxin
 * 2022/5/30 14:56
 */
@SuppressWarnings({"AlibabaRemoveCommentedCode", "AlibabaUndefineMagicConstant", "CommentedOutCode"})
public class WriteTest {

    private static final String FILE_NAME = "." + File.separator + "excel" + File.separator + "user1.xlsx";

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        // 构建数据
        List<WidthAndHeightData> dataList = new LinkedList<>();
        WidthAndHeightData data = WidthAndHeightData.builder()
                .string("字符串")
                .date(new Date())
                .doubleData(888.88)
                .build();
        dataList.add(data);

        // 向Excel中写入数据
        EasyExcel.write(FILE_NAME, WidthAndHeightData.class)
                .sheet("行高和列宽测试")
                .doWrite(dataList);
    }

    private static void test3() {
        List<ComplexHeadUser> users = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            ComplexHeadUser user = ComplexHeadUser.builder()
                    .userId(i)
                    .userName("大哥" + i)
                    .hireDate(new Date())
                    .build();
            users.add(user);
        }

        // 向Excel中写入数据
        EasyExcel.write(FILE_NAME, ComplexHeadUser.class)
                .sheet("用户信息")
                .doWrite(users);
    }

    /**
     * 最简单的写（方式一），
     * 排除模型中的属性字段，
     * 向表格中导出指定属性，
     * 插入指定的列
     */
    private static void test1() {
        // 设置排除的属性，也可以在数据模型的字段上加@ExcelIgnore注解排除
//        Set<String> excludeField = new LinkedHashSet<>();
//        excludeField.add("hireDate");
//        excludeField.add("salary");

        // 设置要导出的字段
//        Set<String> includeFields = new LinkedHashSet<>();
//        includeFields.add("userName");
//        includeFields.add("hireDate");

        // 写Excel
        EasyExcel.write(FILE_NAME, User.class)
//                .excludeColumnFieldNames(excludeField)
//                .includeColumnFieldNames(includeFields)
                .sheet("用户信息")
                .doWrite(getUserData());
    }

    /**
     * 最简单的写（方式二），
     * 重复写到Excel的同一个Sheet中，
     * 写到Excel的不同Sheet中
     */
    private static void test2() {
        // 创建Sheet对象
        ExcelWriter excelWriter = EasyExcel.write(FILE_NAME, User.class).build();
        // 创建Sheet对象
//        WriteSheet writeSheet = EasyExcel.writerSheet("用户信息").build();
        // 向Excel中写入数据
//        excelWriter.write(getUserData(), writeSheet);

        // 向Excel的同一个Sheet重复写入数据
//        for (int i = 0; i < 2; i++) {
//            excelWriter.write(getUserData(), writeSheet);
//        }

        for (int i = 0; i < 2; i++) {
            // 创建Sheet对象
            WriteSheet writeSheet = EasyExcel.writerSheet("用户信息" + i).build();
            excelWriter.write(getUserData(), writeSheet);
        }

        // 关闭流
        excelWriter.finish();
    }

    private static List<User> getUserData() {
        List<User> users = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .userId(i)
                    .userName("admin" + i)
                    .gender(i % 2 == 0 ? "男" : "女")
                    .salary(i * 1000.00)
                    .hireDate(new Date())
                    .build();
            users.add(user);
        }

        return users;
    }
}
