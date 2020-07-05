package dev.tadite.habits.tracker;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ReactiveKafkaProducerTemplate<?,?> kafkaProducerTemplate(KafkaProperties properties){
        return new ReactiveKafkaProducerTemplate<Object, Object>(SenderOptions.create(properties.buildProducerProperties()));
    }
}
