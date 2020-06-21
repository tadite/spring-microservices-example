package dev.tadite.habits.projects;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tadite.habits.projects.dto.TaskCreationRequest;
import dev.tadite.habits.projects.dto.TaskDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
