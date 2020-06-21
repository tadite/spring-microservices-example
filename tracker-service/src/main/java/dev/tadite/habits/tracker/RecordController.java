package dev.tadite.habits.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordRepository recordRepository;

    public RecordController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.of(recordRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Record record) {
        recordRepository.save(record);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeById(@PathVariable Long id) {
        recordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}