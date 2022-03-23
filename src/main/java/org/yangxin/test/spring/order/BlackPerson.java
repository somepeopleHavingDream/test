package org.yangxin.test.spring.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * 2022/3/22 16:02
 */
@Component
@Order(1)
public class BlackPerson implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("BlackPerson");
    }
}
