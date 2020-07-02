package dev.tadite.habits.projects;

import dev.tadite.habits.projects.dto.PageDto;
import dev.tadite.habits.projects.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable Integer id) {
        return ResponseEntity.of(ProjectDto.fromEntity(projectService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<PageDto<ProjectDto>> getAllPaged(Pageable pageable) {
        Page<Project> res = projectService.findAll(pageable);
        return ResponseEntity.ok(PageDto.fromEntity(ProjectDto.fromEntity(res)));
    }

    @PostMapping
    public ResponseEntity<ProjectDto> create(@RequestBody Project project) {
        Project savedProject = projectService.save(project);
        return ResponseEntity.ok(ProjectDto.fromEntity(savedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeById(@PathVariable Integer id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
