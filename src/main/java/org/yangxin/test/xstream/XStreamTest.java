package org.yangxin.test.xstream;

import com.thoughtworks.xstream.XStream;

/**
 * 测试
 *
 * @author yangxin
 * 2019/10/09 14:13
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class XStreamTest {

    public static void main(String[] args) {
        // xml转对象
        String xml = "<User>\n"
                + " <name>peter</name>\n"
                + " <age>13</age>\n"
                + " <SEX>男</SEX>\n"
                + " </User>";
        User user = XStreamUtil.xmlToObject(xml, User.class);
        System.out.println(user);

        // 对象转xml
        User user1 = new User("java", "10", "女");
        XStream xStream = new XStream();
        // 自动检测模式，默认
        xStream.autodetectAnnotations(true);
        String xml1 = xStream.toXML(user1);
        System.out.println(xml1);
    }
}
