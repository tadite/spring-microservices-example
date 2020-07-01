package dev.tadite.habits.tracker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Record {

    @Id
    private String id;
    private String taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
