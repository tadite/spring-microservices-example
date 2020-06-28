package dev.tadite.habits.projects.dto;

import dev.tadite.habits.projects.Project;
import dev.tadite.habits.projects.Task;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class ProjectDto {
    private Integer id;
    private LocalDateTime createdAt;
    private String name;
    private String description;
    private Page<TaskDto> tasks;

    public static ProjectDto fromEntity(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .createdAt(project.getCreatedAt())
                .name(project.getName())
                .description(project.getDescription())
                .tasks(getTasksDtoPage(project))
                .build();
    }

    private static Page<TaskDto> getTasksDtoPage(Project project) {
        List<Task> tasks = project.getTasks();
        tasks = tasks == null ? Collections.emptyList() : tasks;
        return new PageImpl<TaskDto>(tasks.stream()
                .map(TaskDto::fromEntity)
                .limit(10)
                .collect(Collectors.toList()),
                PageRequest.of(0, 10),
                tasks.size());
    }

    public static Optional<ProjectDto> fromEntity(Optional<Project> projectOptional) {
        return projectOptional.map(ProjectDto::fromEntity);
    }

    public static Page<ProjectDto> fromEntity(Page<Project> projectOptional) {
        if (projectOptional == null)
            return null;
        return projectOptional.map(ProjectDto::fromEntity);
    }
}
