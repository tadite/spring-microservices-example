package dev.tadite.habits.history;

import dev.tadite.habits.history.db.EventsRepository;
import dev.tadite.habits.history.events.Event;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public Mono<Event> saveEvent(Event event){
        return eventsRepository.save(event);
    }

    public Flux<Event> findAll(){
        return eventsRepository.findAll();
    }
}
