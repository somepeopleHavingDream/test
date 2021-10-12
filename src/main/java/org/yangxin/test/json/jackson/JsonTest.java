package org.yangxin.test.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

/**
 * @author yangxin
 * 2021/9/7 11:54
 */
public class JsonTest {

    public static void main(String[] args) throws JsonProcessingException {
//        test1();
        test2();
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
    private static class Message {

        private String msgId;

        private String msgType;

        private String content;
    }
}
