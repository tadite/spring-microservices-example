package dev.tadite.habits.tracker;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Flux<Record> findAll(Pageable pageable, String projectId) {
        if (projectId!=null)
            return recordRepository.findByProjectId(projectId, pageable);

        return recordRepository.findByIdNotNull(pageable);
    }

    public Mono<Record> findById(String id) {
        return recordRepository.findById(id);
    }

    public Mono<Void> deleteById(String id) {
        return recordRepository.deleteById(id);
    }

    public Mono<Record> save(Record record) {
        return recordRepository.save(record);
    }
}
