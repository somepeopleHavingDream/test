package org.yangxin.test.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import org.yangxin.test.jdk8.serializable.Student;

import java.io.IOException;

/**
 * @author yangxin
 * 2020/03/03 17:25
 */
public class CborTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        ObjectMapper objectMapper = new ObjectMapper(new CBORFactory());

        // 组装一个数组对象
        ObjectNode node1 = objectMapper.createObjectNode();
        node1.put("temperature", 10);
        ObjectNode node2 = objectMapper.createObjectNode();
        node2.put("pressure", 0.01);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add(node1);
        arrayNode.add(node2);

        // 获取二进制流
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(arrayNode);
            JsonNode jsonNode = objectMapper.readTree(bytes);
            System.out.println(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test2() {
        CBORFactory cborFactory = new CBORFactory();
        ObjectMapper objectMapper = new ObjectMapper(cborFactory);

        Student student = Student.builder()
                .age(10)
                .username("test2")
                .build();
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(student);
            Student result = objectMapper.readValue(bytes, Student.class);
            System.out.println(result);
            System.out.println(objectMapper.readTree(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
