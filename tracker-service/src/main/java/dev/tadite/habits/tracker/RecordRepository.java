package dev.tadite.habits.tracker;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecordRepository extends ReactiveMongoRepository<Record, String> {
    Flux<Record> findByTaskId(String taskId, Pageable pageable);

    Mono<Long> countByTaskId(String taskId);

    Flux<Record> findByIdNotNull(Pageable pageable);

    Mono<Long> countByIdNotNull();
}
