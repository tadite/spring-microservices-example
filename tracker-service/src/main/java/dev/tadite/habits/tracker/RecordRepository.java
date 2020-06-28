package dev.tadite.habits.tracker;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RecordRepository extends ReactiveMongoRepository<Record, String> {
    Flux<Record> findByProjectId(String projectId, Pageable pageable);

    Flux<Record> findByIdNotNull(Pageable pageable);
}
