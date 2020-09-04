package org.yangxin.test.json;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author yangxin
 * 2020/09/04 11:42
 */
public class JacksonTest {

    /**
     * @author yangxin
     * 2020/09/04 11:42
     */
    @Data
    private static class Student {

        @JsonProperty(value = "real_name")
        private String realName;
    }

    /**
     * @author yangxin
     * 2020/09/04 14:01
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Friend {

        private String nickname;

        private int age;
    }

    /**
     * @author yangxin
     * 2020/09/04 14:35
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonRootName("FriendDetail")
    @JsonIgnoreProperties({"uselessProp1", "uselessProp3"})
    private static class FriendDetail {

        @JsonProperty("NickName")
        private String name;

        @JsonProperty("Age")
        private int age;

        private String uselessProp1;

        @JsonIgnore
        private int uselessProp2;

        private String uselessProp3;
    }

    /**
     * @author yangxin
     * 2020/09/04 14:42
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonRootName("Person")
    private static class Person {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("NickName")
//        @JacksonXmlText
        private String nickname;

        @JsonProperty("Age")
        private int age;

        @JsonProperty("IdentityCode")
        @JacksonXmlCData
        private String identityCode;

        @JsonProperty
        @JsonFormat(pattern = "yyyy-MM-DD")
//        @JacksonXmlProperty(isAttribute = true)
        private LocalDate birthday;
    }

    public static void main(String[] args) throws JsonProcessingException {
//        Student student = new Student();
//        student.setRealName("zhangsan");
//        // 这里需要注意的是，将对象转换成json字符串使用的方法是fasterxml.jacson提供的
//        System.out.println(new ObjectMapper().writeValueAsString(student));
//        // fastjson不认识@JsonProperty注解，所以要使用jackson自己的序列化工具方法
//        System.out.println(JSON.toJSONString(student));


//        // 简单映射
//        ObjectMapper mapper = new ObjectMapper();
//        Friend friend = new Friend("Alice", 25);
//        
//        // 写为字符串
//        String text = mapper.writeValueAsString(friend);
//        System.out.println(text);
//        // 写为文件
//        // 写为字节流
//        // 从字符串中读取
//        Friend newFriend = mapper.readValue(text, Friend.class);
//        System.out.println(newFriend);


//        // 集合的映射
//        ObjectMapper mapper = new ObjectMapper();
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("age", 25);
//        map.put("name", "Alice");
//        map.put("interests", new String[] {"pc games", "music"});
//
//        String text = mapper.writeValueAsString(map);
//        System.out.println(text);
//
//        Map<String, Object> objectMap = mapper.readValue(text, new TypeReference<Map<String, Object>>() {
//        });
//        System.out.println(objectMap);
//
//        JsonNode root = mapper.readTree(text);
//        String name = root.get("name").asText();
//        int age = root.get("age").asInt();
//        System.out.println("name: " + name + " age: " + age);


//        // Jackson配置，优先推荐enable、disable方法
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 美化输出
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        // 允许序列化空的POJO类（否则会抛出异常）
//        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        // 把java.util.Date，Calendar输出为数字（时间戳）
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        // 在遇到未知属性的时候不抛出异常
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        // 强制JSON 空字符串（""）转换为null对象值
//        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//        // 在JSON空字符串（""）转换为null对象值
//        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//        // 允许没有引号的字段名（非标准）
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        // 允许单引号（非标准）
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        // 强制转义非ASCII字符
//        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
//        // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);


//        // 用注解管理映射
//        ObjectMapper mapper = new ObjectMapper();
////        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
////        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        FriendDetail friendDetail = new FriendDetail("Alice", 25, "", 0, "");
//        String text = mapper.writeValueAsString(friendDetail);
//        System.out.println(text);
//
//        FriendDetail fd = mapper.readValue(text, FriendDetail.class);
//        System.out.println(fd);


//        // 对Java8日期时间类支持
////        ObjectMapper mapper = new ObjectMapper()
////                .registerModule(new JavaTimeModule())
////                .registerModule(new ParameterNamesModule())
////                .registerModule(new Jdk8Module());
//
//        Person person = new Person("Alice", "一天", 25, "10000", LocalDate.of(1994, 1, 1));
//        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
////        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        String text = mapper.writeValueAsString(person);
//        System.out.println(text);
//
//        Person person1 = mapper.readValue(text, Person.class);
//        System.out.println(person1);


//        // XML映射
//        // 实际上XmlMapper就是ObjectMapper的子类
//        Person person = new Person("Alice", "一天", 25, "10000", LocalDate.of(1994, 1, 1));
//        XmlMapper mapper = new XmlMapper();
//        mapper.findAndRegisterModules();
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String text = mapper.writeValueAsString(person);
//        System.out.println(text);
//
//        Person person1 = mapper.readValue(text, Person.class);
//        System.out.println(person1);

        test();
    }

    private static void test() {
        ObjectMapper objectMapper = new ObjectMapper();
        String body = "{\"code\":1,\"msg\":\"success\",\"dataset\":{\"total\":507470,\"page\":1,\"limit\":20,\"rows\":[\"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\"],\"pages\":25374}}";
        try {
//            String code = objectMapper.readTree(body).get("code").asText();
//            String msg = objectMapper.readTree(body).get("msg").asText();
//            System.out.println("code: " + code);
//            System.out.println("msg: " + msg);

            // 这个地方，jackson是真的拿不到，但是fastjson可以拿到，但仍然可以通过toString()方法拿到嵌套子json。
//            String dataset = JSON.parseObject(body).getString("dataset");
            String dataset = objectMapper.readTree(body).get("dataset").toString();
            System.out.println("dataset: " + dataset);
//            String rows = JSON.parseObject(dataset).getString("rows");
            String rows = objectMapper.readTree(dataset).get("rows").toString();
            System.out.println("rows: " + rows);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
