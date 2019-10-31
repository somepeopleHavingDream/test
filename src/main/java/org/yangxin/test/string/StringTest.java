package org.yangxin.test.string;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 字符串测试类
 *
 * @author yangxin
 * 2019/10/31 11:36
 */
public class StringTest {
    public static void main(String[] args) {
        List<String> labelList = Lists.newArrayList("年度", "计划");
        System.out.println(labelList.toString().replaceAll("(\\[)",""));
        System.out.println(labelList.toString().replaceAll("^\\[$]",""));
        System.out.println(labelList.toString().replaceAll("(])",""));
    }
}
