package com.findit.FindIt.kafka;


import lombok.Data;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Data
public class KafkaConsumer {
    private String messageKafka;

    @KafkaListener(topics = "code",groupId = "my_code")
    public void kafkaListen(String message){
        messageKafka=message;

    }

}
