package org.yangxin.test.serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yangxin
 * 2019/12/31 17:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = -363987537630696589L;

    private String username;
    private Integer age;
}
