package org.yangxin.test.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangxin
 * 2020/03/23 09:23
 */
public class AddStatusListenerAPP {

    public static void main(String[] args) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusManager statusManager = loggerContext.getStatusManager();
        OnConsoleStatusListener onConsoleStatusListener = new OnConsoleStatusListener();
        statusManager.add(onConsoleStatusListener);

        Logger logger = LoggerFactory.getLogger("myAPP");
        logger.info("Entering application");

        Foo foo = new Foo();
        foo.doIt();
        logger.info("Exiting application");

        StatusPrinter.print(statusManager);
    }
}
