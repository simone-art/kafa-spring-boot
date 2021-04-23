package com.kafka.springbootkafkaproducerexample.config;

import com.kafka.springbootkafkaproducerexample.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ProducerFactory<String, User> producerFactory(){

        Map<String, Object> config = new HashMap<>();
        //9092 é a mesma porta que está sendo usada pelo consumer é so ir lá e verificar
        //StringSerializer.class vem do própio Spring
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        return new DefaultKafkaProducerFactory<>(config);
    }

    //Código que vai mostrar o producerFactory na tela
    @Bean
    public KafkaTemplate<String, User> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
