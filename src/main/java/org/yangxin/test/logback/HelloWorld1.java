package org.yangxin.test.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author yangxin
 * 2020/03/21 22:44
 */
@Slf4j
public class HelloWorld1 {

    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger("org.yangxin.test.logback.HelloWorld1");
        log.debug("hello world");

        // 打印内部的状态
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);
    }
}
