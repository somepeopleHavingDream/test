package org.yangxin.test.logback;

import ch.qos.logback.classic.util.ContextInitializer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangxin
 * 2020/03/23 09:05
 */
@Slf4j
public class ServerMain {

    static {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "configurationFile.xml");
    }

    public static void main(String[] args) {
        log.info("ServerMain");
    }
}
