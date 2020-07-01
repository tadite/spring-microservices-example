package dev.tadite.habits.history.dto;

import dev.tadite.habits.history.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskCreationRequest {
    private Integer id;
    private Integer projectId;
    private Integer parentId;
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
