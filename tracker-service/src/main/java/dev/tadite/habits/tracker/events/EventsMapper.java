package dev.tadite.habits.tracker.events;

import dev.tadite.habits.tracker.Record;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EventsMapper {

    public Event mapSavedRecord(Record record) {
        HashMap<String, Object> props = new HashMap<>();
        props.put("id", record.getId());
        props.put("taskId", record.getTaskId());
        props.put("startTime", record.getStartTime());
        props.put("endTime", record.getEndTime());
        props.put("description", record.getDescription());

        return new Event("record-created",props);
    }
}
