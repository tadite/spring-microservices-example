package dev.tadite.habits.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class ProjectService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EntityManager entityManager;

    public ProjectService(TaskRepository taskRepository,
                          ProjectRepository projectRepository,
                          EntityManager entityManager) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.entityManager = entityManager;
    }

    public Optional<Project> findById(Integer id) {
        return projectRepository.findById(id);
    }

    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void deleteById(Integer id) {
        projectRepository.deleteById(id);
    }
}
