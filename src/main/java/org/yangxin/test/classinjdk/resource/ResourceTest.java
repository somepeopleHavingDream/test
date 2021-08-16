package org.yangxin.test.classinjdk.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author yangxin
 * 2021/4/25 14:32
 */
public class ResourceTest {

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("my", new Locale("zh", "CN"));
        String cancelKey = bundle.getString("cancelKey");
        System.out.println(cancelKey);
    }
}
