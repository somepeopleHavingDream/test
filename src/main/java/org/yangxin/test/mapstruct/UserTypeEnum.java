package org.yangxin.test.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangxin
 * 2022/3/7 15:08
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    Java("000", "Java开发工程师"),

    Db("001", "数据库管理员"),

    Linux("002", "Linux运维员");

    private String value;
    private String title;
}
