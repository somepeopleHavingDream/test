package org.yangxin.test.id;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author yangxin
 * 2021/11/13 上午11:11
 */
public class OrderCodeTest {

    /**
     * 创建订单流水号（流水号主要用于用户一眼从该流水号上看出相关信息）
     *
     * @param type 线上/线下
     * @param organizationId 组织编号
     * @param spgId 品类Id
     * @param date 时期
     * @return 订单流水号
     */
    public static String createOrderCode(String type, String organizationId, String spgId, String date) {
        StringBuilder builder = new StringBuilder();
        builder.append(type)
                .append(organizationId)
                .append(spgId)
                .append(date);
        ThreadLocalRandom.current().ints(0, 9).limit(10).forEach(builder::append);
        return builder.toString();
    }

    public static void main(String[] args) {
        String code = createOrderCode("S", "000012", "11002", "20180514");
        System.out.println(code);
    }
}
