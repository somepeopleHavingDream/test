package org.yangxin.test.ddd;

import lombok.Data;

import java.util.List;

/**
 * @author yangxin
 * 2021/6/11 17:33
 */
@Data
public class AwardPool {

    private Integer awardPoolId;

    private List<Award> awards;
}
