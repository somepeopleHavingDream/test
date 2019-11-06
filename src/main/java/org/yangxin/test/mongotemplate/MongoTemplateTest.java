package org.yangxin.test.mongotemplate;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author yangxin
 * 2019/11/06 17:37
 */
public class MongoTemplateTest {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "NewEvaluationSystem");
    }
}
