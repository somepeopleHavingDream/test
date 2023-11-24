package org.yangxin.test.objconvert.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 被映射类VO2：比实体类少一个字段
 * 
 * @author yangxin
 * 2022/3/7 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO2 {
    
    private Integer id;
    private String name;
    private String createTime;
}
