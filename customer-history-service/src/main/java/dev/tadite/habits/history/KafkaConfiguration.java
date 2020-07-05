package dev.tadite.habits.history;

import dev.tadite.kafka.events.TrackerEvent;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;

@Configuration
public class KafkaConfiguration {

    private final EventsListener eventsListener;
    private final String CUSTOMER_EVENTS = "customer-events";

    public KafkaConfiguration(EventsListener eventsListener) {
        this.eventsListener = eventsListener;
    }

    @Bean
    public ApplicationRunner runner(KafkaProperties properties) {
        ReceiverOptions<String, TrackerEvent> receiverOptions = ReceiverOptions.create(properties.buildConsumerProperties());
        ReceiverOptions<String, TrackerEvent> receiverOpts = receiverOptions.toImmutable();
        return args -> {
            Flux<ReceiverRecord<String, TrackerEvent>> receiveFlux = KafkaReceiver.create(receiverOpts.subscription(Collections.singleton(CUSTOMER_EVENTS))).receive();

            eventsListener.handle(receiveFlux)
                    .subscribe();
        };
    }
}
