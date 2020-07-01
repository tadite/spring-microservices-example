package dev.tadite.habits.tracker.dto;


import dev.tadite.habits.tracker.Record;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Builder
public class RecordDto {
    private String id;
    private String taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;

    public static RecordDto fromEntity(Record project) {
        return RecordDto.builder()
                .id(project.getId())
                .taskId(project.getTaskId())
                .description(project.getDescription())
                .startTime(project.getStartTime())
                .endTime(project.getEndTime())
                .build();
    }

    public static Optional<RecordDto> fromEntity(Optional<Record> recordOptional) {
        return recordOptional.map(RecordDto::fromEntity);
    }

    public static Page<RecordDto> fromEntity(Page<Record> recordPage) {
        if (recordPage == null)
            return null;
        return recordPage.map(RecordDto::fromEntity);
    }
}