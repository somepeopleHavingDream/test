package org.yangxin.test.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author yangxin
 * 2022/3/7 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO3 {

    private Integer id;
    private String name;

    /**
     *  实体类该属性是String
     */
    private LocalDateTime createTime;

    /**
     * 实体类该属性是LocalDateTime
     */
    private String updateTime;
}
