package org.yangxin.test.redis.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.javatuples.Pair;
import redis.clients.jedis.*;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * list recent:test:info -> 命名空间为test且日志级别为info的最近的日志
 * zset common:test:info -> 命名空间为test且日志级别为info的最频繁的日志
 * string common:test:info:start -> 命名空间为test且日志级别为info的最开始的日志的时间
 * zset known: -> 展示了一些目前正在使用的计数器
 * hash count:5:hits -> 此计数器以每5秒为一个时间片记录着网站的点击量
 * zset stats:ProfilePage:AccessTime -> 个人简介页面的访问时间统计
 * zset slowest:AccessTime -> 页面的平均访问时长
 * string is-under-maintenance -> 服务器是否正在维护
 * string config:redis:statistics -> 连接存储统计数据的redis服务器所需要的信息
 *
 * @author yangxin
 * 2022/6/5 22:32
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaAvoidCallStaticSimpleDateFormat", "unused", "AlibabaAvoidMissUseOfMathRandom", "SpellCheckingInspection", "AlibabaCollectionInitShouldAssignCapacity", "DuplicatedCode"})
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

    static {
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
        testCounters(conn);
        testStats(conn);
        testAccessTime(conn);
        testIsUnderMaintenance(conn);
        testConfig(conn);
    }

    public void testConfig(Jedis conn) {
        System.out.println("\n----- testConfig -----");
        System.out.println("Let's set a config and then get a connection from that config...");
        Map<String, Object> config = new HashMap<>();
        config.put("db", 15);
        setConfig(conn, "redis", "test", config);

        Jedis conn2 = redisConnection("test");
        System.out.println(
                "We can run commands from the configured connection: " + (conn2.info() != null));
    }

    public static final Map<String, Jedis> REDIS_CONNECTIONS =
            new HashMap<>();

    private static final Map<String, Map<String, Object>> CONFIGS =
            new HashMap<>();
    private static final Map<String, Long> CHECKED = new HashMap<>();

    /**
     * 将应用组件的名字传递该装饰器
     *
     * @param component 组件
     * @return redis连接
     */
    public Jedis redisConnection(String component) {
        Jedis configConn = REDIS_CONNECTIONS.get("config");
        if (configConn == null) {
            configConn = new Jedis("localhost");
            configConn.select(15);
            REDIS_CONNECTIONS.put("config", configConn);
        }

        String key = "config:redis:" + component;
        Map<String, Object> oldConfig = CONFIGS.get(key);
        Map<String, Object> config = getConfig(configConn, "redis", component);

        if (!config.equals(oldConfig)) {
            Jedis conn = new Jedis("localhost");
            if (config.containsKey("db")) {
                conn.select(((Double) config.get("db")).intValue());
            }
            REDIS_CONNECTIONS.put(key, conn);
        }

        return REDIS_CONNECTIONS.get(key);
    }

    public Map<String, Object> getConfig(Jedis conn, String type, String component) {
        int wait = 1000;
        String key = "config:" + type + ':' + component;

        // 检查是否需要对这个组件的配置信息进行更新
        Long lastChecked = CHECKED.get(key);
        if (lastChecked == null || lastChecked < System.currentTimeMillis() - wait) {
            // 有需要对配置进行更新，记录最后一次检查这个连接的时间
            CHECKED.put(key, System.currentTimeMillis());

            String value = conn.get(key);
            Map<String, Object> config;
            if (value != null) {
                Gson gson = new Gson();
                config = gson.fromJson(
                        value, new TypeToken<Map<String, Object>>() {
                        }.getType());
            } else {
                config = new HashMap<>();
            }

            CONFIGS.put(key, config);
        }

        return CONFIGS.get(key);
    }

    public void setConfig(
            Jedis conn, String type, String component, Map<String, Object> config) {
        Gson gson = new Gson();
        conn.set("config:" + type + ':' + component, gson.toJson(config));
    }

    public void testIsUnderMaintenance(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testIsUnderMaintenance -----");
        System.out.println("Are we under maintenance (we shouldn't be)? " + isUnderMaintenance(conn));
        conn.set("is-under-maintenance", "yes");
        System.out.println("We cached this, so it should be the same: " + isUnderMaintenance(conn));
        Thread.sleep(1000);
        System.out.println("But after a sleep, it should change: " + isUnderMaintenance(conn));
        System.out.println("Cleaning up...");
        conn.del("is-under-maintenance");
        Thread.sleep(1000);
        System.out.println("Should be False again: " + isUnderMaintenance(conn));
    }

    /*
        将两个变量设置为全局变量以便在之后对它们进行写入
     */

    private long lastChecked;
    private boolean underMaintenance;

    public boolean isUnderMaintenance(Jedis conn) {
        // 距离上次检查是否已经超过1秒？
        if (lastChecked < System.currentTimeMillis() - 1000) {
            // 更新最后检查时间
            lastChecked = System.currentTimeMillis();
            // 检查系统是否正在进行维护
            String flag = conn.get("is-under-maintenance");
            underMaintenance = "yes".equals(flag);
        }

        // 返回一个布尔值，用于表示系统是否正在进行维护
        return underMaintenance;
    }

    public void testAccessTime(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testAccessTime -----");
        System.out.println("Let's calculate some access times...");
        AccessTimer timer = new AccessTimer(conn);
        for (int i = 0; i < 10; i++) {
            // 记录代码块执行前的时间
            timer.start();
            Thread.sleep((int) ((.5 + Math.random()) * 1000));
            timer.stop("req-" + i);
        }
        System.out.println("The slowest access times are:");
        Set<Tuple> atimes = conn.zrevrangeWithScores("slowest:AccessTime", 0, -1);
        for (Tuple tuple : atimes) {
            System.out.println("  " + tuple.getElement() + ", " + tuple.getScore());
        }
        assert atimes.size() >= 10;
        System.out.println();
    }

    public void testStats(Jedis conn) {
        System.out.println("\n----- testStats -----");
        System.out.println("Let's add some data for our statistics!");
        List<Object> r = null;
        for (int i = 0; i < 5; i++) {
            double value = (Math.random() * 11) + 5;
            r = updateStats(conn, "temp", "example", value);
        }
        System.out.println("We have some aggregate statistics: " + r);
        Map<String, Double> stats = getStats(conn, "temp", "example");
        System.out.println("Which we can also fetch manually:");
        System.out.println(stats);
        assert stats.get("count") >= 5;
    }

    public List<Object> updateStats(Jedis conn, String context, String type, double value) {
        int timeout = 5000;
        // 负责存储统计数据的键
        String destination = "stats:" + context + ':' + type;
        // 像common_log()函数一样，处理当前这一个小时的数据和上一个小时的数据
        String startKey = destination + ":start";
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            conn.watch(startKey);
            String hourStart = ISO_FORMAT.format(new Date());

            String existing = conn.get(startKey);
            Transaction trans = conn.multi();
            if (existing != null && COLLATOR.compare(existing, hourStart) < 0) {
                trans.rename(destination, destination + ":last");
                trans.rename(startKey, destination + ":pstart");
                trans.set(startKey, hourStart);
            }

            String tkey1 = UUID.randomUUID().toString();
            String tkey2 = UUID.randomUUID().toString();
            // 将值添加到临时键里面
            trans.zadd(tkey1, value, "min");
            trans.zadd(tkey2, value, "max");

            // 使用聚合函数MIN和MAX，对存储统计数据的键以及两个临时键进行并集计算
            trans.zunionstore(
                    destination,
                    new ZParams().aggregate(ZParams.Aggregate.MIN),
                    destination, tkey1);
            trans.zunionstore(
                    destination,
                    new ZParams().aggregate(ZParams.Aggregate.MAX),
                    destination, tkey2);

            // 删除临时键
            trans.del(tkey1, tkey2);
            // 对有序集合中的样本数量、值的和、值的平方之和3个成员进行更新
            trans.zincrby(destination, 1, "count");
            trans.zincrby(destination, value, "sum");
            trans.zincrby(destination, value * value, "sumsq");

            List<Object> results = trans.exec();
            if (results == null) {
                continue;
            }
            // 返回基本的计数信息，以便函数调用者在有需要时做进一步的处理。
            return results.subList(results.size() - 3, results.size());
        }
        return null;
    }

    public Map<String, Double> getStats(Jedis conn, String context, String type) {
        // 程序将从这个键里面取出统计数据
        String key = "stats:" + context + ':' + type;
        // 获取基本的统计数据，并将它们都放到一个字典里面。
        Map<String, Double> stats = new HashMap<>();
        Set<Tuple> data = conn.zrangeWithScores(key, 0, -1);
        for (Tuple tuple : data) {
            stats.put(tuple.getElement(), tuple.getScore());
        }
        // 计算平均值
        stats.put("average", stats.get("sum") / stats.get("count"));
        // 计算标准差的第一个步骤
        double numerator = stats.get("sumsq") - Math.pow(stats.get("sum"), 2) / stats.get("count");
        double count = stats.get("count");
        // 完成标准差的计算工作
        stats.put("stddev", Math.pow(numerator / (count > 1 ? count - 1 : 1), .5));
        return stats;
    }

    public void testCounters(Jedis conn)
            throws InterruptedException {
        System.out.println("\n----- testCounters -----");
        System.out.println("Let's update some counters for now and a little in the future");
        long now = System.currentTimeMillis() / 1000;
        for (int i = 0; i < 10; i++) {
            int count = (int) (Math.random() * 5) + 1;
            updateCounter(conn, "test", count, now + i);
        }

        List<Pair<Integer, Integer>> counter = getCounter(conn, "test", 1);
        System.out.println("We have some per-second counters: " + counter.size());
        System.out.println("These counters include:");
        for (Pair<Integer, Integer> count : counter) {
            System.out.println("  " + count);
        }
        assert counter.size() >= 10;

        counter = getCounter(conn, "test", 5);
        System.out.println("We have some per-5-second counters: " + counter.size());
        System.out.println("These counters include:");
        for (Pair<Integer, Integer> count : counter) {
            System.out.println("  " + count);
        }
        assert counter.size() >= 2;
        System.out.println();

        System.out.println("Let's clean out some counters by setting our sample count to 0");
        CleanCountersThread thread = new CleanCountersThread(0, 2 * 86400000);
        thread.start();
        Thread.sleep(1000);
        thread.quit();
        thread.interrupt();
        counter = getCounter(conn, "test", 86400);
        System.out.println("Did we clean out all of the counters? " + (counter.size() == 0));
        assert counter.size() == 0;
    }

    public List<Pair<Integer, Integer>> getCounter(
            Jedis conn, String name, int precision) {
        // 取得存储计数器数据的键的名字
        String hash = String.valueOf(precision) + ':' + name;
        // 从Redis里面取出计数器数据
        Map<String, String> data = conn.hgetAll("count:" + hash);
        // 将计数器数据转换成指定的格式
        ArrayList<Pair<Integer, Integer>> results =
                new ArrayList<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            results.add(new Pair<>(
                    Integer.parseInt(entry.getKey()),
                    Integer.parseInt(entry.getValue())));
        }
        // 对数据进行排序，把旧的数据样本排在前面
        Collections.sort(results);
        return results;
    }

    public void updateCounter(Jedis conn, String name, int count) {
        updateCounter(conn, name, count, System.currentTimeMillis() / 1000);
    }

    /**
     * 以秒为单位的计算器精度，分别为1秒、5秒、1分钟、5分钟、1小时、5小时、1天——用户可以按需调整这些精度
     */
    public static final int[] PRECISION = new int[]{1, 5, 60, 300, 3600, 18000, 86400};

    public void updateCounter(Jedis conn, String name, int count, long now) {
        // 通过取得当前时间来判断应该对哪个时间片执行自增操作
        // 为了保证之后的清理工作可以正确地执行，这里需要创建一个事务型流水线
        Transaction trans = conn.multi();

        // 为我们记录的每种精度都创建一个计数器
        for (int prec : PRECISION) {
            // 取得当前时间片的开始时间
            long pnow = (now / prec) * prec;
            // 负责创建存储计数信息的散列
            String hash = String.valueOf(prec) + ':' + name;
            // 将计数器的引用信息添加到有序集合里面，并将其分值设置为0，以便在之后执行清理操作
            trans.zadd("known:", 0, hash);
            // 对给定名字和精度的计算器进行更新
            trans.hincrBy("count:" + hash, String.valueOf(pnow), count);
        }
        trans.exec();
    }

    public void testLogCommon(Jedis conn) {
        System.out.println("\n----- testLogCommon -----");
        System.out.println("Let's write some items to the common log");
        for (int count = 1; count < 6; count++) {
            for (int i = 0; i < count; i++) {
                logCommon(conn, "test", "message-" + count);
            }
        }
        Set<Tuple> common = conn.zrevrangeWithScores("common:test:info", 0, -1);
        System.out.println("The current number of common messages is: " + common.size());
        System.out.println("Those common messages are:");
        for (Tuple tuple : common) {
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
        while (System.currentTimeMillis() < end) {
            // 对记录当前小时数的键进行监视，确保轮换操作可以正确地执行
            conn.watch(startKey);
            // 取得当前时间，取得当前所处的小时数
            String hourStart = ISO_FORMAT.format(new Date());
            String existing = conn.get(startKey);

            // 创建一个事务
            Transaction trans = conn.multi();
            // 如果这个常见日志消息列表记录的是上一个小时的日志
            if (existing != null && COLLATOR.compare(existing, hourStart) < 0) {
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
            if (results == null) {
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
        for (String message : recent) {
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

    @SuppressWarnings({"UnusedAssignment", "IntegerDivisionInFloatingPointContext", "BusyWait"})
    public static class CleanCountersThread
            extends Thread {
        private final Jedis conn;
        private int sampleCount = 100;
        private boolean quit;
        private final long timeOffset; // used to mimic a time in the future.

        public CleanCountersThread(int sampleCount, long timeOffset) {
            this.conn = new Jedis("localhost");
            this.conn.select(15);
            this.sampleCount = sampleCount;
            this.timeOffset = timeOffset;
        }

        public void quit() {
            quit = true;
        }

        @Override
        public void run() {
            // 为了平等地处理更新频率各不相同的多个计数器，程序需要记录清理操作执行的次数
            int passes = 0;
            // 持续地对计数器进行清理，直到退出为止
            while (!quit) {
                // 记录清理操作开始执行的时间，这个值将被用于计算清理操作的执行时长
                long start = System.currentTimeMillis() + timeOffset;
                // 渐进地遍历所有已知的计数器
                int index = 0;
                while (index < conn.zcard("known:")) {
                    // 取得被检查计数器的数据
                    Set<String> hashSet = conn.zrange("known:", index, index);
                    index++;
                    if (hashSet.size() == 0) {
                        break;
                    }
                    String hash = hashSet.iterator().next();
                    // 取得计数器的精度
                    int prec = Integer.parseInt(hash.substring(0, hash.indexOf(':')));
                    /*
                        因为清理程序每60秒就会循环一次，
                        所以这里需要根据计数器的更新频率来判断是否真的有必要对计数器进行清理。
                        如果这个计数器在这次循环里不需要进行清理，
                        那么检查下一个计数器。
                        （举个例子，
                        如果清理程序只循环了3次，
                        而计数器的更新频率为每5分钟一次，
                        那么程序暂时还不需要对这个计数器进行清理。）
                     */
                    int bprec = (int) Math.floor(prec / 60);
                    if (bprec == 0) {
                        bprec = 1;
                    }
                    if ((passes % bprec) != 0) {
                        continue;
                    }

                    String hkey = "count:" + hash;
                    // 根据给定的精度以及需要保留的样本算了，计算出我们需要保留什么时间之前的样本。
                    String cutoff = String.valueOf(
                            ((System.currentTimeMillis() + timeOffset) / 1000) - (long) sampleCount * prec);
                    // 获取样本的开始时间，并将其从字符串转换为整数。
                    ArrayList<String> samples = new ArrayList<>(conn.hkeys(hkey));
                    Collections.sort(samples);
                    // 计算出需要移除的样本数量。
                    int remove = bisectRight(samples, cutoff);

                    // 按需移除计数样本
                    if (remove != 0) {
                        conn.hdel(hkey, samples.subList(0, remove).toArray(new String[0]));
                        // 这个散列可能已经被清空
                        if (remove == samples.size()) {
                            // 在尝试修改计数器散列之前，对其进行监视
                            conn.watch(hkey);
                            /*
                                验证计数器散列是否为空，
                                如果是的话，
                                那么从记录已知计数器的有序集合里面移除它。
                             */
                            if (conn.hlen(hkey) == 0) {
                                Transaction trans = conn.multi();
                                trans.zrem("known:", hash);
                                trans.exec();
                                // 在删除了一个计数器的情况下，下次循环可以使用与本次循环相同的索引
                                index--;
                            } else {
                                // 计数器散列并不为空，继续让它留在已知计数器的有序集合里面。
                                conn.unwatch();
                            }
                        }
                    }
                }

                // 为了让清理操作的执行频率与计数器更新的频率保持一致，对记录循环次数的变量以及记录执行时长的变量进行更新
                passes++;
                long duration = Math.min(
                        (System.currentTimeMillis() + timeOffset) - start + 1000, 60000);
                try {
                    // 如果这次循环未耗尽60秒，那么在余下的时间内进行休眠；如果60秒已经耗尽，那么休眠1秒以便稍作休息。
                    sleep(Math.max(60000 - duration, 1000));
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // mimic python's bisect.bisect_right
        public int bisectRight(List<String> values, String key) {
            int index = Collections.binarySearch(values, key);
            return index < 0 ? Math.abs(index) - 1 : index + 1;
        }
    }

    public class AccessTimer {
        private final Jedis conn;
        private long start;

        public AccessTimer(Jedis conn) {
            this.conn = conn;
        }

        public void start() {
            // 记录代码块执行器的时间
            start = System.currentTimeMillis();
        }

        public void stop(String context) {
            // 计算代码块的执行时长
            long delta = System.currentTimeMillis() - start;
            // 更新这一上下文的统计数据
            List<Object> stats = updateStats(conn, context, "AccessTime", delta / 1000.0);
            // 计算页面的平均访问时长
            double average = (Double) stats.get(1) / (Double) stats.get(0);

            Transaction trans = conn.multi();
            // 将页面的平均访问时长添加到记录最长访问时间的有序集合里
            trans.zadd("slowest:AccessTime", average, context);
            // AccessTime有序集合只会保留最慢的100条记录。
            trans.zremrangeByRank("slowest:AccessTime", 0, -101);
            trans.exec();
        }
    }
}
