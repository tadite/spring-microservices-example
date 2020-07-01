package dev.tadite.habits.tracker;

import dev.tadite.habits.tracker.dto.RecordDto;
import dev.tadite.habits.tracker.dto.RecordsPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public Mono<RecordsPage> getAll(@RequestParam(required = false) String taskId,
                                    @RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "20") int size) {

        return recordService.findAllPage(PageRequest.of(page, size), taskId);
    }

    @GetMapping("/{id}")
    public Mono<RecordDto> getById(@PathVariable String id) {
        return recordService.findById(id).map(RecordDto::fromEntity);
    }

    @PostMapping
    public Mono<RecordDto> create(@RequestBody Record record) {
        return recordService.save(record).map(RecordDto::fromEntity);
    }

    @DeleteMapping("/{id}")
    public Mono removeById(@PathVariable String id) {
        return recordService.deleteById(id);
    }
}
