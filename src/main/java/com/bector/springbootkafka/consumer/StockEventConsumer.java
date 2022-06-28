package com.bector.springbootkafka.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockEventConsumer
{
    @Value("${consumer.group.id}")
    private String groupId;

    @KafkaListener(topics={"stock-events"}, groupId = "groupId")
    public void readMessage(ConsumerRecord<Integer,String> consumerRecord)
    {
        log.info(" Consumer Record: {}", consumerRecord);
    }

}
