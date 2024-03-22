package org.yangxin.test.json.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author yangxin
 * 2021/9/7 11:54
 */
@SuppressWarnings({"unused", "CommentedOutCode", "CallToPrintStackTrace"})
public class JsonTest {

    public static void main(String[] args) throws JsonProcessingException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        // 创建一个 JsonFactory 对象
        JsonFactory jsonFactory = new JsonFactory();

        try {
            // 创建一个 ByteArrayOutputStream 对象，用于存储 JSON 数据
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // 使用 JsonFactory 创建一个 JsonGenerator，并指定输出流和编码方式
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);

            // 创建一个 ObjectMapper 对象，用于序列化 Java 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 创建一个要序列化的 Java 对象
            Message message = new Message("1", "1", "1");

            // 创建一个 ObjectWriter 对象，用于将 Java 对象写入到 JsonGenerator 中
            ObjectWriter objectWriter = objectMapper.writer();

            // 使用 ObjectWriter 将 Java 对象写入到 JsonGenerator 中
            objectWriter.writeValue(jsonGenerator, message);

            // 关闭 JsonGenerator
            jsonGenerator.close();

            // 将输出流中的数据转换为字符串并打印
            String json = outputStream.toString("UTF-8");
            System.out.println("Serialized JSON: " + json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test3() {
        // 创建 JsonFactory 实例
        JsonFactory jsonFactory = new JsonFactory();

        // 创建输出流
        try (OutputStream outputStream = System.out) {
            // 使用 JsonFactory 的 createGenerator 方法创建 JsonGenerator 实例
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);

            // 写入 JSON 内容
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", "John");
            jsonGenerator.writeNumberField("age", 30);
            jsonGenerator.writeEndObject();

            // 关闭 JsonGenerator
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test2() throws JsonProcessingException {
        String json = "{\n" +
                "    \"code\": \"1\",\n" +
                "    \"msg\": \"操作成功\",\n" +
                "    \"data\": {\n" +
                "        \"zoneInfoDTOList\": [\n" +
                "            {\n" +
                "                \"zoneId\": 1447531146323070978,\n" +
                "                \"zoneType\": 2,\n" +
                "                \"longitude\": 113.314491,\n" +
                "                \"latitude\": 23.14119,\n" +
                "                \"distance\": 3000,\n" +
                "                \"dayOfWeek\": \"1234567\",\n" +
                "                \"startHour\": 8,\n" +
                "                \"startMinute\": 0,\n" +
                "                \"endHour\": 23,\n" +
                "                \"endMinute\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"zoneId\": 1447520851673825282,\n" +
                "                \"zoneType\": 1,\n" +
                "                \"longitude\": 113.372621,\n" +
                "                \"latitude\": 23.134342,\n" +
                "                \"distance\": 500,\n" +
                "                \"dayOfWeek\": \"1234567\",\n" +
                "                \"startHour\": 8,\n" +
                "                \"startMinute\": 0,\n" +
                "                \"endHour\": 12,\n" +
                "                \"endMinute\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"zoneId\": 1447532370875289601,\n" +
                "                \"zoneType\": 2,\n" +
                "                \"longitude\": 113.326533,\n" +
                "                \"latitude\": 23.144164,\n" +
                "                \"distance\": 1000,\n" +
                "                \"dayOfWeek\": \"1234567\",\n" +
                "                \"startHour\": 8,\n" +
                "                \"startMinute\": 0,\n" +
                "                \"endHour\": 23,\n" +
                "                \"endMinute\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"timeSeconds\": 600\n" +
                "    }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);
        String data = jsonNode.get("data").toString();
        System.out.println(data);
    }

    private static void test1() throws JsonProcessingException {
        String data = "[\n" +
                "\n" +
                "          \n" +
                "        {\n" +
                "\n" +
                "          \n" +
                "            \"msgId\": \"334acaac80154fe89195d5c20d3c7808\",\n" +
                "\n" +
                "          \n" +
                "            \"msgType\": \"详见具体消息类型定义\",\n" +
                "\n" +
                "          \n" +
                "            \"content\": \"详见具体消息类型格式定义\"\n" +
                "\n" +
                "          \n" +
                "        }\n" +
                "\n" +
                "          \n" +
                "    ]";

        ObjectMapper mapper = new ObjectMapper();
        List<Message> messageList = mapper.readValue(data, new TypeReference<List<Message>>() {
        });
        System.out.println(messageList);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Message {

        private String msgId;

        private String msgType;

        private String content;
    }
}
