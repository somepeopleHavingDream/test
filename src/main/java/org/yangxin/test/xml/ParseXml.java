package org.yangxin.test.xml;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yangxin
 * 2021/4/22 17:51
 */
public class ParseXml {

    public static String xml = "<Students><student><name><![CDATA[陈喻]]></name><age><![CDATA[26]]></age><sex><![CDATA[男]]></sex></student><student><name><![CDATA[陈彩凤]]></name><age><![CDATA[25]]></age><sex><![CDATA[女]]></sex></student><student><name><![CDATA[陈紫宣]]></name><age><![CDATA[2]]></age><sex><![CDATA[女]]></sex></student><student><name><![CDATA[陈紫曦]]></name><age><![CDATA[7个月]]></age><sex><![CDATA[女]]></sex></student></Students>";

    public static final String STUDENT = "student";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String SEX = "sex";

    public static void main(String[] args) {
        List<Student> studentList = parseXmlByDoc(xml);
        studentList.forEach(System.out::println);
    }

    private static List<Student> parseXmlByDoc(String xml) {
        if (StringUtils.isBlank(xml)) {
            return Collections.emptyList();
        }

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        List<Student> studentList = new ArrayList<>();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

            Element rootElement = document.getDocumentElement();
            NodeList studentNodeList = rootElement.getElementsByTagName(STUDENT);

            for (int i = 0; i < studentNodeList.getLength(); i++) {
                Element appElement = (Element) studentNodeList.item(i);
                NodeList studentInfoNodeList = appElement.getChildNodes();

                Student student = new Student();
                for (int j = 0; j < studentInfoNodeList.getLength(); j++) {
                    Element element = (Element) studentInfoNodeList.item(j);
                    String appAttr = element.getTagName();

                    switch (appAttr) {
                        case NAME:
                            student.setName(element.getTextContent());
                            break;
                        case AGE:
                            student.setAge(element.getTextContent());
                            break;
                        case SEX:
                            student.setSex(element.getTextContent());
                            break;
                        default:
                            break;
                    }
                }

                studentList.add(student);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return studentList;
    }
}
