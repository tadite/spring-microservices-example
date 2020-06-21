package dev.tadite.habits.projects.dto;

import dev.tadite.habits.projects.Task;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskDto {

    private Long id;
    private Long projectId;
    private LocalDateTime createdAt;
    private Long parentId;
    private String name;
    private String description;

    public static TaskDto fromEntity(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .projectId(task.getProject().getId())
                .createdAt(task.getCreatedAt())
                .parentId(task.getParentId())
                .name(task.getName())
                .description(task.getDescription())
                .build();
    }
}
