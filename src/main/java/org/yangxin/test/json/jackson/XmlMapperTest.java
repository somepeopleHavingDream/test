package org.yangxin.test.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
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
@SuppressWarnings({"AlibabaRemoveCommentedCode", "CommentedOutCode"})
public class XmlMapperTest {

    public static void main(String[] args) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

//        String s = xmlMapper.writeValueAsString(new SimpleBean());
//        System.out.println(s);

//        SimpleBean value = xmlMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>", SimpleBean.class);
//        System.out.println(value);

//        Person person = new Person();
//        person.setFirstName("Alice");
//        person.setLastName("Bob");
//        person.setPhoneNumbers(Arrays.asList("1", "2", "3"));
//        person.setAddress(Arrays.asList(new Address("a", "b"), new Address("c", "d")));
//        System.out.println(xmlMapper.writeValueAsString(person));

        Person person = xmlMapper.readValue("<Person><firstName>Alice</firstName><lastName>Bob</lastName><phoneNumbers><phoneNumbers>1</phoneNumbers><phoneNumbers>2</phoneNumbers><phoneNumbers>3</phoneNumbers></phoneNumbers><address><streetName>a</streetName><city>b</city></address><address><streetName>c</streetName><city>d</city></address></Person>", Person.class);
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
