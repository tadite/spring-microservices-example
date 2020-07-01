package dev.tadite.habits.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityCreatedResponse {
    private Long id;

    public static EntityCreatedResponse of(Long id){
        return new EntityCreatedResponse(id);
    }
}
