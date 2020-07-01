package dev.tadite.habits.history;

import dev.tadite.habits.events.Event;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(EventsSink.class)
public class EventsListener {

    @StreamListener(EventsSink.INPUT)
    public void handle(Event event) {
        System.out.println("Received: " + event);
    }
}
