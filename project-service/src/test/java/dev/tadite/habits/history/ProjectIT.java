package dev.tadite.habits.history;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tadite.habits.history.matchers.LocalDateTimeStringMatcher;
import dev.tadite.habits.history.matchers.PageDtoMatcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class ProjectIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void createProject() throws Exception {
        Project project = new Project(null, null, "1", "2", null);

        MvcResult mvcResult = mockMvc.perform(post("/projects")
                .contentType("application/json")
                .content(mapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", Matchers.anything()))
                .andExpect(jsonPath("name", Matchers.equalTo(project.getName())))
                .andExpect(jsonPath("description", Matchers.equalTo(project.getDescription())))
                .andExpect(jsonPath("$.tasks", PageDtoMatcher.empty()))
                .andReturn();
    }

    @Test
    void findById() throws Exception {
        Project createdProject = projectRepository.save(new Project(null, null, "1", "2", Collections.emptyList()));

        MvcResult mvcResult = mockMvc.perform(get("/projects/{id}", createdProject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createdAt", new LocalDateTimeStringMatcher(createdProject.getCreatedAt())))
                .andExpect(jsonPath("$.tasks", PageDtoMatcher.empty()))
                .andReturn();
    }

    @Test
    void findAllDefaultPaged() throws Exception {
        Iterable<Project> iterator = Stream.generate(
                () -> new Project(null, null, UUID.randomUUID().toString(), UUID.randomUUID().toString(), Collections.emptyList()))
                .limit(100)::iterator;
        projectRepository.saveAll(iterator);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("pageSize", Matchers.equalTo(20)))
                .andExpect(jsonPath("totalElements", Matchers.greaterThanOrEqualTo(100)))
                .andExpect(jsonPath("totalPages", Matchers.greaterThanOrEqualTo(5)));
    }
}
