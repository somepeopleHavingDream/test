package org.yangxin.test;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangxin
 * 2020/04/29 10:24
 */
@SuppressWarnings({"FieldCanBeLocal", "unused", "CommentedOutCode"})
@SpringBootApplication
@Slf4j
@MapperScan("org.yangxin.test.mybatisplus")
public class TestApplication {
//public class TestApplication implements CommandLineRunner {

    private final ApplicationContext applicationContext;
    private final StringEncryptor stringEncryptor;

    @Autowired
    public TestApplication(ApplicationContext applicationContext, StringEncryptor stringEncryptor) {
        this.applicationContext = applicationContext;
        this.stringEncryptor = stringEncryptor;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);

//        // 测试事件
//        publishEvent(context);
//        // 测试log4j2日志
//        log4j2();
    }

    private static void log4j2() {
        log.error("error");
        log.info("info");
        log.debug("debug");
        log.warn("warn");
        log.trace("trace");
    }

//    @Override
//    public void run(String... args) {
//        Environment environment = applicationContext.getBean(Environment.class);
//
//        // 首先获取配置文件里的原始明文信息
//        String mysqlOriginPassword = environment.getProperty("spring.datasource.password");
//
//        // 加密
//        String mysqlEncryptedPassword1 = encrypt(mysqlOriginPassword);
//        String mysqlEncryptedPassword2 = encrypt("");
//
//        // 打印加密前后的结果对比
//        log.info("MySQL原始明文密码： [{}]", mysqlOriginPassword);
//        log.info("MySQL加密后密码： [{}]", mysqlEncryptedPassword1);
//        log.info("MySQL加密后密码： [{}]", mysqlEncryptedPassword2);
//        log.info("MySQL解密后密码： [{}]", decrypt("BQGsohp2tGtdi/wU5ck8wDyLGWQinHIZVV4DwG6sfV4aQMnsuYQUMvXvy0LieYzV"));
//    }

    /**
     * 加密
     */
    private String encrypt(String originPassword) {
        return stringEncryptor.encrypt(originPassword);
    }

    /**
     * 解密
     */
    @SuppressWarnings("SameParameterValue")
    private String decrypt(String encryptedPassword) {
        return stringEncryptor.decrypt(encryptedPassword);
    }
}
