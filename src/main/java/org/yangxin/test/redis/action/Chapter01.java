package org.yangxin.test.redis.action;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * 2022/5/24 17:36
 */
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
//        System.out.println("Its HASH looks like: ");
//
//        Map<String, String> articleData = conn.hgetAll("article:" + articleId);
//        for (Map.Entry<String, String> entry : articleData.entrySet()) {
//            System.out.println(" " + entry.getKey() + ": " + entry.getValue());
//        }
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
