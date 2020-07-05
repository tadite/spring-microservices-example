package dev.tadite.habits.history.db;

import dev.tadite.habits.history.events.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EventsRepository extends ReactiveMongoRepository<Event, String> {
}
