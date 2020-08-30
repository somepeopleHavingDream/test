package org.yangxin.test.kafka;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author yangxin
 * 2020/08/
 */
@SuppressWarnings({"DuplicatedCode", "unused"})
public class MyConsumer {

    private static KafkaConsumer<String, String> consumer;
    private static final Properties properties;

    static {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "KafkaStudy");
    }

    private static void generalConsumeMessageAutoCommit() {
        properties.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<>(properties);
        // 消费者订阅主题
        consumer.subscribe(Collections.singleton("imooc-kafka-study-x"));

        try {
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("topic = %s, partition = %s, key = %s, value = %s%n",
                            record.topic(),
                            record.partition(),
                            record.key(),
                            record.value());

                    if ("done".equals(record.value())) {
                        flag = false;
                    }
                }

                if (!flag) {
                    break;
                }
            }
        } finally {
            consumer.close();
        }
    }

    private static void generalConsumeMessageSyncCommit() {
        properties.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));

        while (true) {
            boolean flag = true;

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s, partition = %s, key = %s, value = %s%n",
                        record.topic(),
                        record.partition(),
                        record.key(),
                        record.value());

                if ("done".equals(record.value())) {
                    flag = false;
                }
            }

            try {
                consumer.commitSync();
            } catch (CommitFailedException ex) {
                System.out.println("commit failed error: " + ex.getMessage());
            }

            if (!flag) {
                break;
            }
        }
    }

    private static void generalConsumeMessageASyncCommit() {
        properties.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));

        while (true) {
            boolean flag = true;

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s, partition = %s, key = %s, value = %s%n",
                        record.topic(),
                        record.partition(),
                        record.key(),
                        record.value());

                if ("done".equals(record.value())) {
                    flag = false;
                }
            }

            // commit A, offset 2000
            // commit B, offset 3000
            // 异步提交在发生异常时不会重试提交
            consumer.commitAsync();

            if (!flag) {
                break;
            }
        }
    }

    private static void generalConsumeMessageASyncCommitWithCallback() {
        properties.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));

        while (true) {
            boolean flag = true;

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s, partition = %s, key = %s, value = %s%n",
                        record.topic(),
                        record.partition(),
                        record.key(),
                        record.value());

                if ("done".equals(record.value())) {
                    flag = false;
                }
            }

            // commit A, offset 2000
            // commit B, offset 3000
            // 异步提交在发生异常时不会重试提交
            consumer.commitAsync((map, e) -> {
                if (e != null) {
                    System.out.println("commit failed for offsets: " + e.getMessage());
                }
            });

            if (!flag) {
                break;
            }
        }
    }

    private static void mixSyncAndAsyncCommit() {
        properties.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("topic = %s, partition = %s, key = %s, value = %s%n",
                            record.topic(),
                            record.partition(),
                            record.key(),
                            record.value());
                }

                consumer.commitAsync();
            }
        } catch (Exception ex) {
            System.out.println("commit async error: " + ex.getMessage());
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    public static void main(String[] args) {
        generalConsumeMessageAutoCommit();
    }
}
