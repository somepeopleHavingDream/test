package org.yangxin.test.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis
 *
 * @author yangxin
 * 2019/09/29 14:36
 */
class SecKill {

    @Test
    void testRedis() {
        // 1. 设置IP地址和端口
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2. 保存数据
        jedis.set("name", "imooc");
        // 3. 获取数据
        String value = jedis.get("name");
        System.out.println(value);
        // 4. 释放资源
        jedis.close();
    }

    @Test
    void testRedisByPool() {
        // 获得连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(30);
        // 设置最大空闲连接数
        config.setMaxIdle(10);

        // 获得连接池

        // 获得核心对象
        try (JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
             Jedis jedis = jedisPool.getResource()) {
            // 通过连接池获得连接
            // 设置数据
            jedis.set("name", "张三");
            // 获取数据
            String value = jedis.get("name");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
