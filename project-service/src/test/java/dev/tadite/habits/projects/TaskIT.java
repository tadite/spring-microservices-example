package dev.tadite.habits.projects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tadite.habits.projects.dto.ProjectDto;
import dev.tadite.habits.projects.dto.TaskCreationRequest;
import dev.tadite.habits.projects.dto.TaskDto;
import dev.tadite.habits.projects.matchers.PageMatcher;
import net.minidev.json.JSONArray;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class TaskIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Test
    void createTask() throws Exception {
        Project savedProject = projectRepository.save(new Project(null, null, "1", "2", null));

        MvcResult mvcResult = mockMvc.perform(post("/projects/{projectId}/tasks", savedProject.getId())
                .contentType("application/json")
                .content(mapper.writeValueAsString(TaskCreationRequest.builder()
                        .name("1")
                        .description("2")
                        .projectId(savedProject.getId())
                        .build())))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        TaskDto createdTask = mapper.readValue(response, TaskDto.class);

        Assertions.assertNotNull(createdTask.getId());
        Assertions.assertEquals(createdTask.getName(), "1");
        Assertions.assertEquals(createdTask.getDescription(), "2");
        Assertions.assertEquals(createdTask.getProjectId(), savedProject.getId());
    }

    @Test
    void getProjectWithTasks() throws Exception {
        Project savedProject = projectRepository.save(new Project(null, null, "1", "2", null));
        for (int i = 0; i < 20; i++) {
            TaskCreationRequest taskCreationRequest = TaskCreationRequest.builder()
                    .name("project-name" + i)
                    .description("project-desc" + i)
                    .projectId(savedProject.getId())
                    .build();
            taskService.save(taskCreationRequest);
        }

        MvcResult mvcResult = mockMvc.perform(get("/projects/{projectId}", savedProject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", Matchers.anything()))
                .andExpect(jsonPath("name", Matchers.equalTo(savedProject.getName())))
                .andExpect(jsonPath("description", Matchers.equalTo(savedProject.getDescription())))
                .andExpect(jsonPath("$.tasks",
                        PageMatcher.fromPageable(PageRequest.of(1, 10),
                                20,
                                contentArr -> ((Map) contentArr.get(0)).get("id").equals(20))))
                .andReturn();
    }
}
