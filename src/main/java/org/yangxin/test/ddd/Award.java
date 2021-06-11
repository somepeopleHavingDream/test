package org.yangxin.test.ddd;

import lombok.Data;

/**
 * @author yangxin
 * 2021/6/11 17:34
 */
@Data
public class Award {

    private Integer awardId;

    /**
     * 概率
     */
    private Integer probability;
}
