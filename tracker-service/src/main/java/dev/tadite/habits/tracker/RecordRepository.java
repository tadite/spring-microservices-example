package dev.tadite.habits.tracker;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {
}
