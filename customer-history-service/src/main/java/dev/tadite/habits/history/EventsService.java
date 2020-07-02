package dev.tadite.habits.history;

import dev.tadite.habits.history.db.EventsRepository;
import dev.tadite.habits.history.events.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public void saveEvent(Event event){
        eventsRepository.save(event);
    }

    public List<Event> findAll(){
        return eventsRepository.findAll();
    }
}
