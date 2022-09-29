package org.yangxin.test.pay.cornupay;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author yangxin
 */
@SuppressWarnings("unused")
public class MapUtil
{

    public static String mapToString(Map<String, Object> map)
    {

        StringBuilder param = new StringBuilder();
        for (Entry<String, Object> e : map.entrySet()) {
            if (e.getValue() != null && !isBlank(e.getValue().toString())) {
                param.append(e.getKey()).append("=").append(e.getValue()).append("&");
            }
        }

        return param.substring(0, param.length() - 1);

    }
    

    public static String mapToString2(Map<String, String> map)
    {

        StringBuilder param = new StringBuilder();
        for (Entry<String, String> e : map.entrySet()) {
            if (e.getValue() != null && !isBlank(e.getValue())) {
                param.append(e.getKey()).append("=").append(e.getValue()).append("&");
            }
        }

        return param.substring(0, param.length() - 1);

    }

    private static boolean isBlank(CharSequence cs)
    {
        return (cs == null) || (cs.length() == 0);
    }

    public static void main(String[] args)
    {
        String a = null;
        String b = "";
        String c = "1 2";
        String d = "123";

        System.out.println(isBlank(a));
        System.out.println(isBlank(b));
        System.out.println(isBlank(c));
        System.out.println(isBlank(d));
    }

}
