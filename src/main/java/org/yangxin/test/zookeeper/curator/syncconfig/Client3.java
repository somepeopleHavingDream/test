package org.yangxin.test.zookeeper.curator.syncconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.RetryNTimes;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangxin
 * 2020/12/19 00:34
 */
@SuppressWarnings("DuplicatedCode")
public class Client3 {

    private final CuratorFramework CLIENT;

    private static final String ZK_SERVER_PATH = "localhost:2181";
    private static final String CONFIG_NODE_PATH = "/super/mooc";
    private static final String SUB_PATH = "/redis-config";

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public Client3() {
        CLIENT = CuratorFrameworkFactory.builder()
                .connectString(ZK_SERVER_PATH)
                .sessionTimeoutMs(10000)
                .retryPolicy(new RetryNTimes(3, 5000))
                .namespace("redis")
                .build();
        CLIENT.start();
    }

    public void closeZKClient() {
        if (CLIENT != null) {
            CLIENT.close();
        }
    }

    public static void main(String[] args) {
        Client3 client1 = new Client3();
        System.out.println("client3启动成功。");

        // 开启缓存
        CuratorCache curatorCache = CuratorCache.build(client1.CLIENT, CONFIG_NODE_PATH);
        curatorCache.start();

        // 添加监听事件
        curatorCache.listenable()
                // 监听结点变化
                .addListener((type, oldData, data) -> {
                    // 如果结点发生变化
                    // 如果路径为CONFIG_NODE_PATH+SUB_PATH，即/super/mooc/redis-config，则进行处理
                    if (type == CuratorCacheListener.Type.NODE_CHANGED
                            && Objects.equals(oldData.getPath(), CONFIG_NODE_PATH + SUB_PATH)) {
                        // 打印发生变化的结点的路径
                        System.out.println("监听到配置发生变化，结点路径为：" + oldData.getPath());

                        // 读取结点数据
                        String currentData = new String(data.getData());
                        System.out.println("结点" + CONFIG_NODE_PATH + SUB_PATH + "的数据为：" + currentData);

                        // 转换结点数据
                        try {
                            RedisConfig redisConfig = OBJECT_MAPPER.readValue(currentData, RedisConfig.class);

                            // 配置信息不为空，则进行相应操作
                            String currentType = redisConfig.getType();
//                            String url = redisConfig.getUrl();
//                            String remark = redisConfig.getRemark();
                            if ("add".equalsIgnoreCase(currentType)) {
                                System.out.println("add操作……");
                            } else if ("update".equalsIgnoreCase(currentType)) {
                                System.out.println("update操作……");
                            } else if ("delete".equalsIgnoreCase(currentType)) {
                                System.out.println("delete操作");
                            }
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                });

        try {
            // 此处CountDownLatch对象的只是单纯地让主线程挂起
            COUNT_DOWN_LATCH.await();
            client1.closeZKClient();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
