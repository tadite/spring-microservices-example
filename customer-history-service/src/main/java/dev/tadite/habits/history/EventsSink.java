package dev.tadite.habits.history;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventsSink {
    String INPUT = "customer-events";

    @Input(EventsSink.INPUT)
    SubscribableChannel input();
}
