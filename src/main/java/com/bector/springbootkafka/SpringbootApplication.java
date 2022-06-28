package com.bector.springbootkafka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
