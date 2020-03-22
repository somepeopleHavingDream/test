package org.yangxin.test.logback;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangxin
 * 2020/03/22 15:47
 */
@Slf4j
public class MyAPP1 {

    public static void main(String[] args) {
        log.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        log.info("Exiting application.");
    }
}
