package org.yangxin.test.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangxin
 * 2/15/21 12:32 AM
 */
public class RedisClusterTest {

    private static final JedisCluster JEDIS_CLUSTER;

    static {
        // 添加集群的服务结点Set集合
        Set<HostAndPort> hostAndPortSet = new HashSet<>();

        // 添加结点
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 6382));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 6383));
        hostAndPortSet.add(new HostAndPort("127.0.0.1", 6384));

        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数，默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数，默认8个
        jedisPoolConfig.setMaxTotal(500);
        // 最小空闲连接数，默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数（如果设置为阻塞时BlockWhenExhausted），如果超时就抛出异常
        jedisPoolConfig.setMaxWaitMillis(2000);
        // 对拿到connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);

        JEDIS_CLUSTER = new JedisCluster(hostAndPortSet,
                2000,
                5000,
                500,
                "123456",
                jedisPoolConfig);
    }

    public static void main(String[] args) {
        System.out.println(JEDIS_CLUSTER.set("username", "alice"));
        System.out.println(JEDIS_CLUSTER.get("username"));
    }
}
