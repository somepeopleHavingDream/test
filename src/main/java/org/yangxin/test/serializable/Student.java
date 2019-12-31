package org.yangxin.test.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangxin
 * 2019/12/31 17:54
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -363987537630696589L;

    private String username;
    private Integer age;
}
