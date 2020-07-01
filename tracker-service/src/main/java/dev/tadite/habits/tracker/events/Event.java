package dev.tadite.habits.tracker.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String type;
    private Map<String, Object> properties;
}
