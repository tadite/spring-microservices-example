package dev.tadite.habits.projects.dto;

import dev.tadite.habits.projects.Task;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Builder
public class TaskDto {

    private Integer id;
    private Integer projectId;
    private LocalDateTime createdAt;
    private Integer parentId;
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

    public static Optional<TaskDto> fromEntity(Optional<Task> taskOptional){
        return taskOptional.map(t -> TaskDto.fromEntity(t));
    }

    public static Page<TaskDto> fromEntity(Page<Task> projectOptional) {
        if (projectOptional == null)
            return null;
        return projectOptional.map(TaskDto::fromEntity);
    }
}
