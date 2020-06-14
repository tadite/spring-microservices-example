package dev.tadite.habits.tracker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id
    private Long id;
    private String projectId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
