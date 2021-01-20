package org.yangxin.test.kafka;

import lombok.extern.slf4j.Slf4j;
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
@SuppressWarnings("unused")
//@Slf4j
public class MyProducer {

    private static final KafkaProducer<String, String> PRODUCER;

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.3.3:9092");
//        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("partitioner.class", "org.yangxin.test.kafka.CustomPartitioner");

        PRODUCER = new KafkaProducer<>(properties);
    }

    private static void sendMessageForgetResult() {
        ProducerRecord<String, String> record = new ProducerRecord<>("mooc_kafka_study",
                "name", "ForgetResult");
        PRODUCER.send(record);
        PRODUCER.close();
    }

    private static void sendMessageBlocked() throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>("mooc_kafka_study",
                "name", "blocked");
        RecordMetadata recordMetadata = PRODUCER.send(record).get();

//        System.out.println("topic: " + recordMetadata.topic());
//        System.out.println("partition: " + recordMetadata.partition());
//        System.out.println("offset: " + recordMetadata.offset());
//        System.out.println("topic: [%s], partition: [%s], offset: [%]",
//                recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
        System.out.printf("topic: [%s], partition: [%s], offset: [%s]%n",
                recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());

//        log.info("recordMetadata: [{}]", recordMetadata);
//        log.info("topic: [{}], partition: [{}], offset: [{}]",
//                recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());

        PRODUCER.close();
    }

    private static void sendMessageCallback() {
        ProducerRecord<String, String> record = new ProducerRecord<>("mooc_kafka_study_x",
                "name",
                "callback");
        PRODUCER.send(record, new MyProducerCallback());

        record = new ProducerRecord<>("mooc_kafka_study_x",
                "name-x",
                "callback");
        PRODUCER.send(record, new MyProducerCallback());

        record = new ProducerRecord<>("mooc_kafka_study_x",
                "name-y",
                "callback");
        PRODUCER.send(record, new MyProducerCallback());

        record = new ProducerRecord<>("mooc_kafka_study_x",
                "name-z",
                "callback");
        PRODUCER.send(record, new MyProducerCallback());

        PRODUCER.close();
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

//            System.out.println("topic: " + recordMetadata.topic());
//            System.out.println("partition: " + recordMetadata.partition());
//            System.out.println("offset: " + recordMetadata.offset());
            System.out.printf("topic: [%s], partition: [%s], offset: [%s]%n",
                    recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());

//            log.info("topic: [{}], partition: [{}], offset: [{}]",
//                    recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        sendMessageCallback();
//        sendMessageSync();
//        sendMessageForgetResult();
    }
}
