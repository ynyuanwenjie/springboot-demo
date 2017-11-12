package com.kafka.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by yuanwenjie on 2017/11/11.
 */
public class SynchronousSendProducer {
    private static final String topic = "fireAndForget";
    private static final Integer partition = 0;
    private static final Long timestamp = null;
    private static final String key = "key";
    private static final String value = "--value--syn-from-partition 1";

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "10.143.20.199:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("acks","-1");
        kafkaProps.put("retries","3");
        KafkaProducer producer = new KafkaProducer(kafkaProps);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,partition,key, value);
        try {
            producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
