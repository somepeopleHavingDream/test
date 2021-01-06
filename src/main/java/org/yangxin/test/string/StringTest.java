package org.yangxin.test.string;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 字符串测试类
 *
 * @author yangxin
 * 2019/10/31 11:36
 */
public class StringTest {

    public static void main(String[] args) throws JsonProcessingException {
        String chinese = "进击的巨人";
        System.out.println(chinese.codePointAt(3));
    }
}
