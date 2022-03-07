package org.yangxin.test.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2022/3/7 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO4 {

    private String userId;
    private String userName;
    private String createTime;
    private String updateTime;
}
