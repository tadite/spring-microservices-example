package dev.tadite.habits.history;

import dev.tadite.habits.history.events.Event;
import dev.tadite.kafka.events.TrackerEvent;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;

@Service
public class EventsListener {

    private final EventsService eventsService;

    public EventsListener(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    public Flux<Disposable> handle(Flux<ReceiverRecord<String, TrackerEvent>> receiveFlux) {
        return receiveFlux.map(r -> {
            TrackerEvent trackerEvent = r.value();
            Event event = new Event(null, trackerEvent.getType(), trackerEvent.getProperties());
            System.out.println("Received: " + event);
            return eventsService.saveEvent(event)
                    .subscribe(mongoMono -> r.receiverOffset().acknowledge());
        });
    }
}
