package dev.tadite.habits.projects;

import dev.tadite.habits.projects.dto.TaskCreationRequest;
import dev.tadite.habits.projects.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getById(@PathVariable Integer taskId) {
        return ResponseEntity.of(taskService.findById(taskId));
    }

    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<Page> getAllPagedByProject(Pageable pageable, @PathVariable Integer projectId) {
        Page<Task> res = taskService.findAllByProjectId(projectId, pageable);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/projects/tasks")
    public ResponseEntity<Page> getAllPaged(Pageable pageable) {
        Page<Task> res = taskService.findAll(pageable);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<TaskDto> create(@RequestBody TaskCreationRequest taskCreationRequest, @PathVariable Integer projectId) {
        taskCreationRequest.setProjectId(projectId);
        Task savedTask = taskService.save(taskCreationRequest);
        return ResponseEntity.ok(TaskDto.fromEntity(savedTask));
    }
}
