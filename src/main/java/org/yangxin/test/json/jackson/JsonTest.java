package org.yangxin.test.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

/**
 * @author yangxin
 * 2021/9/7 11:54
 */
public class JsonTest {

    public static void main(String[] args) throws JsonProcessingException {
        test1();
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
    private static class Message {

        private String msgId;

        private String msgType;

        private String content;
    }
}
