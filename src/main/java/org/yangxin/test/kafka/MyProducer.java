package org.yangxin.test.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author yangxin
 * 2020/08/24 10:57
 */
public class MyProducer {

    private static final KafkaProducer<String, String> producer;

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("partitioner.class", "org.yangxin.kafkastudy.CustomPartitioner");
        producer = new KafkaProducer<>(properties);
    }

    private static void sendMessageForgetResult() {
        ProducerRecord<String, String> record = new ProducerRecord<>("imooc_kafka_study", "name", "ForgetResult");
        producer.send(record);
        producer.close();
    }

    private static void sendMessageSync() throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>("imooc_kafka_study", "name", "sync");
        RecordMetadata result = producer.send(record).get();

        System.out.println("topic: " + result.topic());
        System.out.println("partition: " + result.partition());
        System.out.println("offset: " + result.offset());

        producer.close();
    }

    private static void sendMessageCallback() {
        ProducerRecord<String, String> record = new ProducerRecord<>("imooc-kafka-study-x",
                "name",
                "callback");
        producer.send(record, new MyProducerCallback());

        record = new ProducerRecord<>("imooc-kafka-study-x",
                "name-x",
                "callback");
        producer.send(record, new MyProducerCallback());

        record = new ProducerRecord<>("imooc-kafka-study-x",
                "name-y",
                "callback");
        producer.send(record, new MyProducerCallback());

        record = new ProducerRecord<>("imooc-kafka-study-x",
                "name-z",
                "callback");
        producer.send(record, new MyProducerCallback());

        producer.close();
    }

    /**
     * @author yangxin
     * 2020/08/24 11:41
     */
    private static class MyProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
                return;
            }

            System.out.println("topic: " + recordMetadata.topic());
            System.out.println("partition: " + recordMetadata.partition());
            System.out.println("offset: " + recordMetadata.offset());
        }
    }

    public static void main(String[] args) {
        sendMessageCallback();
//        sendMessageSync();
//        sendMessageForgetResult();
    }
}
