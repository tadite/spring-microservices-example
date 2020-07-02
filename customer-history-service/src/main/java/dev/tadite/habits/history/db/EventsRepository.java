package dev.tadite.habits.history.db;

import dev.tadite.habits.history.events.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsRepository extends MongoRepository<Event, String> {
}
