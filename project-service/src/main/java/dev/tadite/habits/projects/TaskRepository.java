package dev.tadite.habits.projects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {

    Page<Task> findAllByProjectId(Integer projectId, Pageable pageable);
}
