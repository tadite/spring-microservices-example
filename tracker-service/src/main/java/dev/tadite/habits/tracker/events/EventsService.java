package dev.tadite.habits.tracker.events;

import dev.tadite.kafka.events.TrackerEvent;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Service
public class EventsService {
    private final String CUSTOMER_EVENTS = "customer-events";

    private final ReactiveKafkaProducerTemplate kafkaProducerTemplate;

    public EventsService(ReactiveKafkaProducerTemplate kafkaProducerTemplate) {
        this.kafkaProducerTemplate = kafkaProducerTemplate;
    }

    @SuppressWarnings("unchecked")
    public Mono<SenderResult<Void>> publishEvent(Event event){
        return kafkaProducerTemplate.send(CUSTOMER_EVENTS, new TrackerEvent(event.getType(), event.getProperties()));
    }
}
