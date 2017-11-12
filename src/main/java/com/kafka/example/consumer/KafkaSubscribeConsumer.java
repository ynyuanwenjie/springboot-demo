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
        props.put("group.id", "group1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "true");//default :true
        //props.put("auto.offset.reset", "earliest");//default :latest
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singletonList("fireAndForget"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    logger.debug("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset() + ", customer = " + record.key() + ", country = " + record.value() + "\n");
                    System.out.println("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
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

       /* while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
            {
                System.out.printf("topic = %s, partition = %s, offset =
                        %d, customer = %s, country = %s\n",
                record.topic(), record.partition(),
                        record.offset(), record.key(), record.value()); 1
            }
            try {
                consumer.commitSync(); 2
            } catch (CommitFailedException e) {
                log.error("commit failed", e) 3
            }*/

       /* while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
            {
                System.out.printf("topic = %s, partition = %s,
                        offset = %d, customer = %s, country = %s\n",
                record.topic(), record.partition(), record.offset(),
                        record.key(), record.value());
            }
            consumer.commitAsync(); 1
        }*/
    }
}
