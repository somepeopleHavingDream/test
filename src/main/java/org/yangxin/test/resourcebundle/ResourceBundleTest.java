package org.yangxin.test.resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ResourceBundle
 *
 * @author yangxin
 * 2019/09/30
 */
public class ResourceBundleTest {
    public static void main(String[] args) {
        // 中文简体
        ResourceBundle bundle = ResourceBundle.getBundle("my", new Locale("zh", "CN"));
        String cancel = bundle.getString("cancelKey");
        System.out.println(cancel);
    }
}
