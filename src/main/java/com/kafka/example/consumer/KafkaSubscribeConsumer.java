package com.kafka.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by yuanwenjie on 2017/11/11.
 */
public class KafkaSubscribeConsumer {
    private static Logger logger = Logger.getLogger(KafkaSubscribeConsumer.class);

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.143.20.199:9092");
        props.put("group.id", "grop1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singletonList("fireAndForget"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    logger.debug("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset() + ", customer = " + record.key() + ", country = " + record.value() + "\n");

                    /*int updatedCount = 1;
                    if (custCountryMap.countainsValue(record.value())) {
                        updatedCount = custCountryMap.get(record.value()) + 1;
                    }
                    custCountryMap.put(record.value(), updatedCount)

                    JSONObject json = new JSONObject(custCountryMap);
                    System.out.println(json.toString(4));*/
                }
            }
        } finally {
            consumer.close();
        }
    }
}
