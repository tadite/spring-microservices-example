package dev.tadite.habits.tracker;

import dev.tadite.habits.tracker.dto.RecordsPage;
import dev.tadite.habits.tracker.events.EventsMapper;
import dev.tadite.habits.tracker.events.EventsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final EventsService eventsService;
    private final EventsMapper eventsMapper;

    public RecordService(RecordRepository recordRepository,
                         EventsService eventsService,
                         EventsMapper eventsMapper) {
        this.recordRepository = recordRepository;
        this.eventsService = eventsService;
        this.eventsMapper = eventsMapper;
    }

    public Flux<Record> findAll(Pageable pageable, String taskId) {
        if (taskId != null)
            return recordRepository.findByTaskId(taskId, pageable);

        return recordRepository.findByIdNotNull(pageable);
    }

    public Mono<RecordsPage> findAllPage(Pageable pageable, String taskId) {
        if (taskId != null) {
            Flux<Record> countRecords = recordRepository.findByTaskId(taskId, pageable);
            Mono<Long> countMono = recordRepository.countByTaskId(taskId);
            return countRecords.collectList().zipWith(countMono)
                    .map(t -> new RecordsPage(t.getT1(), t.getT2()));
        }

        Flux<Record> countRecords = recordRepository.findByIdNotNull(pageable);
        Mono<Long> countMono = recordRepository.countByIdNotNull();
        return countRecords.collectList().zipWith(countMono)
                .map(t -> new RecordsPage(t.getT1(), t.getT2()));
    }

    public Mono<Record> findById(String id) {
        return recordRepository.findById(id);
    }

    public Mono<Void> deleteById(String id) {
        return recordRepository.deleteById(id);
    }

    public Mono<Record> save(Record record) {
        Mono<Record> savedRecord = recordRepository.save(record)
                .doOnSuccess(rec -> eventsService.publishEvent(eventsMapper.mapSavedRecord(rec)));

        return savedRecord;
    }
}
