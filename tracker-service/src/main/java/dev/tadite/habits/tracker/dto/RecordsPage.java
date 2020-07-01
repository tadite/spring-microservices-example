package dev.tadite.habits.tracker.dto;

import dev.tadite.habits.tracker.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class RecordsPage {
    private final List<Record> content;
    private final Long totalElements;
}
