package com.yango.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        ProducerRecord<String, String> record =
                new ProducerRecord<>("test-topic", null, null, message);

        kafkaTemplate.send(record)
                     .whenComplete((result, ex) -> {
                         if (ex == null) {
                             log.info("Sent to partition: " + result.getRecordMetadata().partition());
                         } else {
                             ex.printStackTrace();
                         }
                     });

    }
}

