package dev.tadite.habits.projects;

import dev.tadite.habits.projects.dto.TaskCreationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EntityManager entityManager;

    public TaskService(TaskRepository taskRepository, EntityManager entityManager) {
        this.taskRepository = taskRepository;
        this.entityManager = entityManager;
    }


    public Task save(TaskCreationRequest request){
        Task task = request.toEntity();
        task.setProject(entityManager.getReference(Project.class, request.getProjectId()));
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Integer taskId) {
        return taskRepository.findById(taskId);
    }

    public Page<Task> findAllByProjectId(Integer projectId, Pageable pageable) {
        return taskRepository.findAllByProjectId(projectId, pageable);
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
