package dev.tadite.habits.history;

import dev.tadite.habits.history.events.Event;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(EventsSink.class)
public class EventsListener {

    private final EventsService eventsService;

    public EventsListener(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @StreamListener(EventsSink.INPUT)
    public void handle(Event event) {
        System.out.println("Received: " + event);
        eventsService.saveEvent(event);
    }
}
