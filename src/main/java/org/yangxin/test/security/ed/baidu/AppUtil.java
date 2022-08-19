package org.yangxin.test.security.ed.baidu;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author yangxin
 * 2021/3/15 10:15
 */
public final class AppUtil {

    /**
     * 生成AppSecret密钥
     */
    private static final String SERVER_NAME = "alice@mail.com";

    private final static String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 短8位UUID思想其实借鉴微博短域名的生成方式，
     * 但是其重复概率过高，而且每次生成4个，需要随机选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，
     * 所以将UUID分成8组，每4个为一组，这样重复率就大大降低了。
     * 经测试，在生成的一千万个数据中也没有出现重复，完全满足大部分需求。
     *
     * @return appKey(appId)
     */
    public static String getAppId() {
        StringBuilder builder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        final int length = 8;
        for (int i = 0; i < length; i++) {
            String s = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(s, 16);
            builder.append(CHARS[x % 0x3E]);
        }

        return builder.toString();
    }

    /**
     * 通过AppKey和内置关键词生成AppSecret
     *
     * @param appId appId(appKey)
     * @return AppSecret
     */
    public static String getAppSecret(String appId) {
        String[] array = {appId, SERVER_NAME};
        StringBuilder builder = new StringBuilder();

        // 字典序排序
        Arrays.sort(array);
        for (String s : array) {
            builder.append(s);
        }

        String s = builder.toString();
        return DigestUtils.sha1Hex(s);
    }

    public static void main(String[] args) {
        String appId = getAppId();
        String appSecret = getAppSecret(appId);
        System.out.println(appId + ":" + appSecret);
    }
}
