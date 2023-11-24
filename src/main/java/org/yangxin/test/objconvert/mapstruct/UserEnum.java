package org.yangxin.test.objconvert.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2022/3/7 15:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEnum {

    private Integer id;
    private String name;
    private UserTypeEnum userTypeEnum;
}
