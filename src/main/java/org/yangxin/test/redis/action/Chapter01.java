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
@SuppressWarnings({"AlibabaUndefineMagicConstant", "SameParameterValue"})
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

    private List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page, String order) {
        String key = order + group;
        if (!conn.exists(key)) {
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            conn.zinterstore(key, params, "group:" + group, order);
            conn.expire(key, 60);
        }
        return getArticles(conn, page, key);
    }

    private void addGroups(Jedis conn, String articleId, String[] toAdd) {
        String article = "article:" + articleId;
        for (String group : toAdd) {
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

    private List<Map<String, String>> getArticles(Jedis conn, int page) {
        return getArticles(conn, page, "score:");
    }

    private List<Map<String, String>> getArticles(Jedis conn, int page, String order) {
        int start = (page - 1) * ARTICLES_PER_PAGE;
        int end = start + ARTICLES_PER_PAGE - 1;

        Set<String> ids = conn.zrevrange(order, start, end);
        List<Map<String, String>> articles = new ArrayList<>();
        for (String id : ids) {
            Map<String, String> articleData = conn.hgetAll(id);
            articleData.put("id", id);
            articles.add(articleData);
        }

        return articles;
    }

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
