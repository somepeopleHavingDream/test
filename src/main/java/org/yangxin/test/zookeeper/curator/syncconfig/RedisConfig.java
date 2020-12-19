package org.yangxin.test.zookeeper.curator.syncconfig;

import lombok.Data;

/**
 * Redis配置
 *
 * @author yangxin
 * 2020/12/19 01:19
 */
@Data
public class RedisConfig {

    /**
     * add新增配置，update更新配置，delete删除配置
     */
    private String type;

    /**
     * 如果是add或update，则提供下载地址
     */
    private String url;

    /**
     * 备注
     */
    private String remark;
}
