package org.yangxin.test.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author yangxin
 * 2020/03/23 09:14
 */
@Slf4j
public class MyAPP3 {

    public static void main(String[] args) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator joranConfigurator = new JoranConfigurator();
        joranConfigurator.setContext(loggerContext);
        loggerContext.reset();
        try {
            // 这里是通过参数来修改配置的
            joranConfigurator.doConfigure(args[0]);
        } catch (JoranException e) {
            e.printStackTrace();
        }

        StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);

        log.info("Entering application");

        Foo foo = new Foo();
        foo.doIt();
        log.info("Exiting application");
    }
}
