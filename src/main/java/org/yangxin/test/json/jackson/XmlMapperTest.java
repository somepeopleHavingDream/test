package org.yangxin.test.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin
 * 2021/4/16 14:50
 */
@SuppressWarnings({"AlibabaRemoveCommentedCode"})
public class XmlMapperTest {

    private static final XmlMapper MAPPER = new XmlMapper();

    public static void main(String[] args) throws JsonProcessingException {
//        test1();
        test2();
    }

    private static void test2() throws JsonProcessingException {
        JsonNode node = MAPPER.readTree("<EStudentCertificateDTO><code>1</code><msg>操作成功</msg><cardId>TRTW54345343456784</cardId><terminalId>869523050457556</terminalId><userId>20006972939</userId><schoolId>57063300</schoolId></EStudentCertificateDTO>");
        JsonNode code = node.get("code");

        // asText()会进行强转，如果字段值不是String类型数据，会将其转成String基本类型的数据（如果是复杂类型，返回空字符串）
        System.out.println(code.asText());
        System.out.println(code);
        // textValue()只针对String类型数据，所以如果字段值是非String类型数据，则返回null
        System.out.println(code.textValue());
        System.out.println(code.toPrettyString());
    }

    /**
     * 将xml字符串读取为一个对象实例
     *
     * @throws JsonProcessingException json处理异常
     */
    private static void test1() throws JsonProcessingException {
        Person person = MAPPER.readValue("<Person><firstName>Alice</firstName><lastName>Bob</lastName><phoneNumbers><phoneNumbers>1</phoneNumbers><phoneNumbers>2</phoneNumbers><phoneNumbers>3</phoneNumbers></phoneNumbers><address><streetName>a</streetName><city>b</city></address><address><streetName>c</streetName><city>d</city></address></Person>", Person.class);
        System.out.println(person);
    }
}

@Data
class SimpleBean {

    private Integer x = 1;
    private Integer y = 2;
}

@Data
class Person {

    private String firstName;

    private String lastName;

    private List<String> phoneNumbers = new ArrayList<>();

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Address> address = new ArrayList<>();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Address {

    private String streetName;

    private String city;
}
