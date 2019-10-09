package org.yangxin.test.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author yangxin
 * 2019/10/09 14:04
 */
@XStreamAlias("User")
@AllArgsConstructor
@ToString
public class User {
    @XStreamAlias("name")
    private String name;

    @XStreamAlias("age")
    private String age;

    @XStreamAlias("SEX")
    private String sex;
}
