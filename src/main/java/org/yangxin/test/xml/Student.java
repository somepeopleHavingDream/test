package org.yangxin.test.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2021/4/22 17:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;

    private String age;

    private String sex;
}
