package org.yangxin.test.kafka;

import lombok.extern.slf4j.Slf4j;
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
//@Slf4j
public class MyConsumer {

    private static KafkaConsumer<String, String> consumer;
    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        PROPERTIES.put("bootstrap.servers", "192.168.3.3:9092");
//        properties.put("bootstrap.servers", "127.0.0.1:9092");
        PROPERTIES.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        PROPERTIES.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        PROPERTIES.put("group.id", "KafkaStudy");
    }

    private static void generalConsumeMessageAutoCommit() {
        PROPERTIES.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<>(PROPERTIES);
        // 消费者订阅主题
        consumer.subscribe(Collections.singleton("mooc_kafka_study_x"));

        try {
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("topic = %s, partition = %s, key = %s, value = %s%n",
                            record.topic(), record.partition(), record.key(), record.value());
//                    log.info("topic: [{}], partition: [{}], key: [{}], value: [{}]",
//                            record.topic(), record.partition(), record.key(), record.value());

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
        PROPERTIES.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(PROPERTIES);
        consumer.subscribe(Collections.singletonList("mooc_kafka_study_x"));

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
        PROPERTIES.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(PROPERTIES);
        consumer.subscribe(Collections.singletonList("mooc_kafka_study_x"));

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
        PROPERTIES.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(PROPERTIES);
        consumer.subscribe(Collections.singletonList("mooc_kafka_study_x"));

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
        PROPERTIES.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<>(PROPERTIES);
        consumer.subscribe(Collections.singletonList("mooc_kafka_study_x"));

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
