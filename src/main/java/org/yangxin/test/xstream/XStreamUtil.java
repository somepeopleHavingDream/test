package org.yangxin.test.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author yangxin
 * 2019/10/09 14:09
 */
class XStreamUtil {
    /**
     * 将xml转换成bean
     */
    static <T> T xmlToObject(String xml, Class<T> cls) {
        XStream xStream = new XStream(new DomDriver());

        // xStream使用注解转换
        xStream.processAnnotations(cls);
        return (T) xStream.fromXML(xml);
    }
}
