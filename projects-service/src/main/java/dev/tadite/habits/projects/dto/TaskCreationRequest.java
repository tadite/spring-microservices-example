package dev.tadite.habits.projects.dto;

import dev.tadite.habits.projects.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskCreationRequest {
    private Long id;
    private Long projectId;
    private Long parentId;
    private String name;
    private String description;

    public Task toEntity(){
        return Task.builder().id(id)
                .parentId(parentId)
                .name(name)
                .description(description)
                .build();
    }
}
