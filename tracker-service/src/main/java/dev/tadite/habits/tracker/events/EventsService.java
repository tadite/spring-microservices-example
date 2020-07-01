package dev.tadite.habits.tracker.events;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(EventsSource.class)
public class EventsService {
    private final EventsSource eventsSource;

    public EventsService(EventsSource eventsSource) {
        this.eventsSource = eventsSource;
    }

    public void publishEvent(Event event){
        eventsSource.outputCustomerEvent().send(MessageBuilder.withPayload(event).build());
    }
}
