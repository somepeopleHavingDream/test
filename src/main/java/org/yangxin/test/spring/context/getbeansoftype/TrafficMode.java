package org.yangxin.test.spring.context.getbeansoftype;

/**
 * 交通方式
 *
 * @author yangxin
 * 2021/10/18 11:56
 */
public interface TrafficMode {

    /**
     * 查询交通方式编码
     *
     * @return 交通方式编码
     */
    TrafficCode getCode();

    /**
     * 查询交通方式的费用，单位：分
     *
     * @return 费用
     */
    Integer getFee();
}
