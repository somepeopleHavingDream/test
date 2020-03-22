package org.yangxin.test.logback;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangxin
 * 2020/03/22 14:16
 */
//@Slf4j
public class SelectionRule {

    public static void main(String[] args) {
        // ch.qos.logback.classic.Logger可以设置日志的级别
        // 获取一个名为“com.foo”的logger实例
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        // 设置logger的级别为INFO
        logger.setLevel(Level.INFO);

        // 这条日志可以打印，因为WARN >= INFO
        logger.warn("警告信息");
        // 这条日志不会打印，因为DEBUG < INFO
        logger.debug("调试信息");

        // "com.foo.bar"会继承"com.foo"的有效级别
        Logger barLogger = LoggerFactory.getLogger("com.foo.bar");
        // 这条日志会打印，因为INFO >= INFO
        barLogger.info("子级信息");
        // 这条日志不会打印，因为DEBUG < INFO
        barLogger.debug("子级调试信息");
    }
}
