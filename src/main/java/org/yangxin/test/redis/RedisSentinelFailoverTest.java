package org.yangxin.test.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2/6/21 11:30 PM
 */
@SuppressWarnings({"SpellCheckingInspection", "InfiniteLoopStatement", "DuplicatedCode"})
@Slf4j
public class RedisSentinelFailoverTest {

    public static void main(String[] args) {
        final String masterName = "mymaster";

        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("127.0.0.1:26379");
        sentinelSet.add("127.0.0.1:26380");
        sentinelSet.add("127.0.0.1:26381");

        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinelSet, "123456");
        int count = 0;
        while (true) {
            count++;

            try (Jedis jedis = jedisSentinelPool.getResource()) {
                int index = new Random().nextInt(100000);
                String key = "k-" + index;
                String value = "v-" + index;

                jedis.set(key, value);

                if (count % 100 == 0) {
                    log.info("key: [{}], value: [{}]", key, jedis.get(key));
                }

                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
