package dev.tadite.habits.tracker.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventsSource {
    String OUTPUT = "customer-events";

    @Output(EventsSource.OUTPUT)
    MessageChannel outputCustomerEvent();
}
