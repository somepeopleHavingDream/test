package org.yangxin.test.redis.action;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

/**
 * list recent:test:info -> 命名空间为test且日志级别为info的最近的日志
 * zset common:test:info -> 命名空间为test且日志级别为info的最频繁的日志
 * string common:test:info:start -> 命名空间为test且日志级别为info的最开始的日志的时间
 *
 * @author yangxin
 * 2022/6/5 22:32
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidCallStaticSimpleDateFormat"})
public class Chapter05 {

    /*
        设置一个字典，将大部分日志的安全级别映射为字符串
     */

    public static final String DEBUG = "debug";
    public static final String INFO = "info";
    public static final String WARNING = "warning";
    public static final String ERROR = "error";
    public static final String CRITICAL = "critical";

    public static final Collator COLLATOR = Collator.getInstance();

    public static final SimpleDateFormat TIMESTAMP =
            new SimpleDateFormat("EEE MMM dd HH:00:00 yyyy");
    private static final SimpleDateFormat ISO_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:00:00");
    static{
        ISO_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) throws InterruptedException {
        new Chapter05().run();
    }

    public void run() throws InterruptedException {
        Jedis conn = new Jedis(ConfigConstant.HOST, ConfigConstant.PORT);
        conn.auth(ConfigConstant.PASSWORD);
        conn.select(15);

        testLogRecent(conn);
        testLogCommon(conn);
    }

    public void testLogCommon(Jedis conn) {
        System.out.println("\n----- testLogCommon -----");
        System.out.println("Let's write some items to the common log");
        for (int count = 1; count < 6; count++) {
            for (int i = 0; i < count; i ++) {
                logCommon(conn, "test", "message-" + count);
            }
        }
        Set<Tuple> common = conn.zrevrangeWithScores("common:test:info", 0, -1);
        System.out.println("The current number of common messages is: " + common.size());
        System.out.println("Those common messages are:");
        for (Tuple tuple : common){
            System.out.println("  " + tuple.getElement() + ", " + tuple.getScore());
        }
        assert common.size() >= 5;
    }

    public void logCommon(Jedis conn, String name, String message) {
        // 设置日志的安全级别
        logCommon(conn, name, message, INFO, 5000);
    }

    public void logCommon(
            Jedis conn, String name, String message, String severity, int timeout) {
        // 负责存储近期的常见日志消息的键
        String commonDest = "common:" + name + ':' + severity;
        // 因为程序每小时需要轮换一次日志，所以它使用一个键来记录当前所处的小时数
        String startKey = commonDest + ":start";
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end){
            // 对记录当前小时数的键进行监视，确保轮换操作可以正确地执行
            conn.watch(startKey);
            // 取得当前时间，取得当前所处的小时数
            String hourStart = ISO_FORMAT.format(new Date());
            String existing = conn.get(startKey);

            // 创建一个事务
            Transaction trans = conn.multi();
            // 如果这个常见日志消息列表记录的是上一个小时的日志
            if (existing != null && COLLATOR.compare(existing, hourStart) < 0){
                // 那么将这些旧的常见日志消息归档
                trans.rename(commonDest, commonDest + ":last");
                trans.rename(startKey, commonDest + ":pstart");
                // 更新当前所处的小时数
                trans.set(startKey, hourStart);
            }

            // 对记录日志出现次数的计数器执行自增操作
            trans.zincrby(commonDest, 1, message);

            String recentDest = "recent:" + name + ':' + severity;
            trans.lpush(recentDest, TIMESTAMP.format(new Date()) + ' ' + message);
            trans.ltrim(recentDest, 0, 99);
            List<Object> results = trans.exec();
            // null response indicates that the transaction was aborted due to
            // the watched key changing.
            if (results == null){
                // 如果程序因为其他客户端正在执行归档操作而出现监视错误，那么进行重试
                continue;
            }
            return;
        }
    }

    public void testLogRecent(Jedis conn) {
        System.out.println("\n----- testLogRecent -----");
        System.out.println("Let's write a few logs to the recent log");
        for (int i = 0; i < 5; i++) {
            logRecent(conn, "test", "this is message " + i);
        }
        List<String> recent = conn.lrange("recent:test:info", 0, -1);
        System.out.println(
                "The current recent message log has this many messages: " +
                        recent.size());
        System.out.println("Those messages include:");
        for (String message : recent){
            System.out.println(message);
        }
        assert recent.size() >= 5;
    }

    public void logRecent(Jedis conn, String name, String message) {
        // 尝试将日志的安全级别转换为简单的字符串
        logRecent(conn, name, message, INFO);
    }

    public void logRecent(Jedis conn, String name, String message, String severity) {
        // 负责创建存储消息的键
        String destination = "recent:" + name + ':' + severity;
        // 使用流水线来将通信往返次数降低为一次
        Pipeline pipe = conn.pipelined();
        // 将当前时间添加到消息里面，用于记录消息的发送时间
        // 将消息添加到日志列表的最前面
        pipe.lpush(destination, TIMESTAMP.format(new Date()) + ' ' + message);
        // 对日志列表进行修剪，让它只包含最新的100条消息
        pipe.ltrim(destination, 0, 99);
        // 执行两个命令
        pipe.sync();
    }
}
