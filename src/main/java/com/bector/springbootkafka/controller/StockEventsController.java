package com.bector.springbootkafka.controller;


import com.bector.springbootkafka.domain.StockEvent;
import com.bector.springbootkafka.domain.StockEventType;
import com.bector.springbootkafka.producer.StockEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class StockEventsController
{

    @Autowired
    StockEventProducer stockEventProducer;


    @PostMapping("/api/stockevent")
    public ResponseEntity<StockEvent> postStockEvent(@RequestBody @Valid StockEvent stockEvent) throws JsonProcessingException, ExecutionException, TimeoutException
    {
        log.info("Invoking Kafka Producer: Begins");

        stockEvent.setStockEventType(StockEventType.NEW);

        stockEventProducer.sendStockEventToTopic(stockEvent);

        log.info("Invoking Kafka Producer: Ends");

        return ResponseEntity.status(HttpStatus.CREATED).body(stockEvent);
    }
}
