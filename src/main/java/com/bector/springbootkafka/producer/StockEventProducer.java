package com.bector.springbootkafka.producer;

import com.bector.springbootkafka.domain.StockEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class StockEventProducer
{

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;



    public void sendStockEventToTopic(StockEvent stockEvent) throws JsonProcessingException
    {
        Integer key = stockEvent.getStockEventId();
        String value = objectMapper.writeValueAsString(stockEvent);
        kafkaTemplate.setDefaultTopic("stock-events");
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.sendDefault(key,value);

       // ProducerRecord producerRecord = buildProducerRecord(key,value,topic);
       // ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(producerRecord);


        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>()
        {
            @Override
            public void onSuccess(SendResult<Integer, String> result)
            {
                handleSuccess(key,value,result);

            }

            @Override
            public void onFailure(Throwable throwable)
            {
                handleFailure(key,value,throwable);

            }

        });

    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result)
    {
        System.out.println("key = " + key + "value = " + value + result.getRecordMetadata().topic());
    }

    private void handleFailure(Integer key, String value, Throwable throwable)
    {
        System.out.println("key = " + key + "value = " + value + throwable.getMessage());
    }
}
