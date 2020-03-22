package org.yangxin.test.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author yangxin
 * 2020/03/22 15:47
 */
@Slf4j
public class MyAPP2 {

    public static void main(String[] args) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);

        log.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        log.info("Exiting application.");
    }
}
