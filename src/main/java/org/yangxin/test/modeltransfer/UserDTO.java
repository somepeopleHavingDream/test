package org.yangxin.test.modeltransfer;

import lombok.Builder;

/**
 * 用户DTO
 *
 * @author yangxin
 * 2019/10/15 10:07
 */
@Builder
class UserDTO {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户级别
     */
    private Long userLevel;

    /**
     * 用户名称
     */
    private String userName;
}
