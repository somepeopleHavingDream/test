package org.yangxin.test.jdk8.reference.weakreference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2020/12/12 20:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

    private String name;

    /**
     * 覆盖finalize，在回收的时候会执行
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Apple: " + name + " finalize.");
    }
}
