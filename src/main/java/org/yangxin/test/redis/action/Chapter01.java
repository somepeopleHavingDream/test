package org.yangxin.test.redis.action;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

/**
 * string article: -> 文章Id
 * set voted:articleId -> 给该文章投票的所有用户名称
 * hash article:articleId -> 给文章的具体信息
 * zset score: -> 所有文章的评分
 * zset time: -> 所有文章的发布时间
 * set group:group -> 该群组的文章
 * zset score:group -> 该群组文章的评分
 *
 * @author yangxin
 * 2022/5/24 17:36
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "SameParameterValue", "HttpUrlsUsage"})
public class Chapter01 {

    private static final Integer ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static final Integer VOTE_SCORE = 432;
    private static final Integer ARTICLES_PER_PAGE = 25;

    public static void main(String[] args) {
        new Chapter01().run();
    }

    private void run() {
        Jedis conn = new Jedis(ConfigConstant.HOST, ConfigConstant.PORT);
        conn.auth(ConfigConstant.PASSWORD);
        conn.select(15);

        String articleId = postArticle(conn, "username", "A title", "http://www.google.com");
        System.out.println("We posted a new article with id: " + articleId);
        System.out.println("Its HASH looks like: ");

        Map<String, String> articleData = conn.hgetAll("article:" + articleId);
        for (Map.Entry<String, String> entry : articleData.entrySet()) {
            System.out.println(" " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();

        articleVote(conn, "other_user", "article:" + articleId);
        String votes = conn.hget("article:" + articleId, "votes");
        System.out.println("We voted for the article, it now has votes: " + votes);
        assert Integer.parseInt(votes) > 1;

        System.out.println("The currently highest-scoring articles are:");
        List<Map<String, String>> articles = getArticles(conn, 1);
        printArticles(articles);
        assert articles.size() >= 1;

        addGroups(conn, articleId, new String[]{"new-group"});
        System.out.println("We added the article to a new group, other articles include:");
        articles = getGroupArticles(conn, "new-group", 1);
        printArticles(articles);
        assert articles.size() >= 1;
    }

    private List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page) {
        return getGroupArticles(conn, group, page, "score:");
    }

    /**
     * 从群组里面获取一整页文章的方法
     *
     * @param conn redis连接
     * @param group 群组
     * @param page 第几页
     * @param order 排序键
     * @return 取出来的文章信息
     */
    private List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page, String order) {
        // 为每个群组的每种排列顺序都创建一个键
        String key = order + group;
        // 检查是否有已缓存的排序结果，如果没有的话就现在进行排序
        if (!conn.exists(key)) {
            // 根据评分或者发布时间，对群组文章进行排序
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            conn.zinterstore(key, params, "group:" + group, order);

            /*
                让redis在60秒之后自动删除这个有序集合。
                开发者在灵活性或限制条件之间的取舍将改变程序存储和更新数据的方式，这一点对于任何数据库都是适用的，Redis也不例外。
             */
            conn.expire(key, 60);
        }

        // 调用之前定义的getArticles()方法来进行分页并获取文章的数据
        return getArticles(conn, page, key);
    }

    /**
     * 添加群组
     *
     * @param conn redis连接
     * @param articleId 文章Id
     * @param toAdd 要添加的群组名称
     */
    private void addGroups(Jedis conn, String articleId, String[] toAdd) {
        // 构建存储文章信息的键名
        String article = "article:" + articleId;
        for (String group : toAdd) {
            // 将文章添加到它所属的群组里面
            conn.sadd("group:" + group, article);
        }
    }

    private void printArticles(List<Map<String, String>> articles) {
        for (Map<String, String> article : articles) {
            System.out.println(" id: " + article.get("id"));
            for (Map.Entry<String, String> entry : article.entrySet()) {
                if ("id".equals(entry.getKey())) {
                    continue;
                }

                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    /**
     * 取出评分最高的文章
     *
     * @param conn redis连接
     * @param page 第几页
     * @return 评分最高的文章信息
     */
    private List<Map<String, String>> getArticles(Jedis conn, int page) {
        return getArticles(conn, page, "score:");
    }

    /**
     * 此方法既可以用于取出评分最高的文章，又可以用于取出最新发布的文章
     *
     * @param conn redis连接
     * @param page 第几页
     * @param order 键
     * @return 取出来的文章信息
     */
    private List<Map<String, String>> getArticles(Jedis conn, int page, String order) {
        // 设置获取文章的起始索引和结束索引
        int start = (page - 1) * ARTICLES_PER_PAGE;
        int end = start + ARTICLES_PER_PAGE - 1;

        // 获取多个文章Id
        Set<String> ids = conn.zrevrange(order, start, end);
        List<Map<String, String>> articles = new ArrayList<>();
        for (String id : ids) {
            // 根据文章Id获取文章的详细信息
            Map<String, String> articleData = conn.hgetAll(id);
            articleData.put("id", id);
            articles.add(articleData);
        }

        return articles;
    }

    /**
     * 文章投票
     *
     * @param conn redis连接
     * @param user 投票的用户
     * @param article 投票的文章
     */
    private void articleVote(Jedis conn, String user, String article) {
        long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
        if (conn.zscore("time:", article) < cutoff) {
            return;
        }

        String articleId = article.substring(article.indexOf(':') + 1);
        if (conn.sadd("voted:" + articleId, user) == 1) {
            conn.zincrby("score:", VOTE_SCORE, article);
            conn.hincrBy(article, "votes", 1);
        }
    }

    /**
     * 文章发布
     *
     * @param conn redis连接
     * @param user 作者
     * @param title 文章的标题
     * @param link 文章的连接
     * @return 文章Id
     */
    private String postArticle(Jedis conn, String user, String title, String link) {
        // article: -> 文章Id
        String articleId = String.valueOf(conn.incr("article:"));

        // voted:articleId -> 给该文章投票的所有用户名称
        String voted = "voted:" + articleId;
        conn.sadd(voted, user);
        conn.expire(voted, ONE_WEEK_IN_SECONDS);

        // article:articleId -> 给文章的具体信息
        String article = "article:" + articleId;
        long now = System.currentTimeMillis() / 1_000;
        Map<String, String> articleData = new HashMap<>(5);
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(now));
        articleData.put("votes", "1");
        conn.hmset(article, articleData);

        // score: -> 所有文章的评分
        conn.zadd("score:", now + VOTE_SCORE, article);
        // time: -> 所有文章的发布时间
        conn.zadd("time:", now, article);

        return articleId;
    }
}
