package dev.tadite.habits.tracker;

import dev.tadite.habits.tracker.dto.RecordDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
public class RecordIT {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordController recordController;

    @Test
    public void givenRecord_whenGetRecordById_thenStatus200() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2020, 3, 3, 10, 2, 3, 1);
        LocalDateTime endDateTime = LocalDateTime.of(2020, 3, 3, 11, 2, 3, 1);
        Record savedRecord = recordRepository.save(new Record(null, "1", startDateTime, endDateTime, "desc")).block();

        Mono<RecordDto> recordDtoMono = recordController.getById(savedRecord.getId());

        StepVerifier
                .create(recordDtoMono)
                .assertNext(recordDto -> {
                    assertEquals(savedRecord.getId(), recordDto.getId());
                    assertEquals(savedRecord.getTaskId(), "1");
                })
                .expectComplete()
                .verify();
    }
}
