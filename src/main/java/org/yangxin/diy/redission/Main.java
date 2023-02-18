package org.yangxin.diy.redission;

/**
 * @author yangxin
 * 2023/2/16 11:54
 */
public class Main {

    private static final String LOCK_KEY = "COMMERCE-BUSINESS";

    /**
     * @return Redisson的配置类
     */
    private static RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig  singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://192.168.1.104:6379");

        return Redisson.create(config);
    }

    public static void main(String[] args) {
        RedissonClient redissonClient = redissonClient();
        System.out.println(redissonClient);

        RLock lock = redissonClient.getLock(LOCK_KEY);
    }
}
