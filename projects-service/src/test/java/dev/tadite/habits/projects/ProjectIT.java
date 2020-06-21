package dev.tadite.habits.projects;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Project createdProject = mapper.readValue(response, Project.class);

        Assertions.assertNotNull(createdProject.getId());
    }

    @Test
    void findById() throws Exception {
        Project createdProject = projectRepository.save(new Project(null, null, "1", "2", Collections.emptySet()));

        MvcResult mvcResult = mockMvc.perform(get("/projects/{id}", createdProject.getId()))
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Project responseProject = mapper.readValue(response, Project.class);

        createdProject.setCreatedAt(createdProject.getCreatedAt().truncatedTo(ChronoUnit.MILLIS));
        responseProject.setCreatedAt(responseProject.getCreatedAt().truncatedTo(ChronoUnit.MILLIS));
        assertThat(responseProject, equalTo(createdProject));
    }

    @Test
    void findAllDefaultPaged() throws Exception {
        Iterable<Project> iterator = Stream.generate(
                () -> new Project(null, null, UUID.randomUUID().toString(), UUID.randomUUID().toString(), Collections.emptySet()))
                .limit(100)::iterator;
        projectRepository.saveAll(iterator);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("numberOfElements", Matchers.equalTo(20)))
                .andExpect(jsonPath("first", Matchers.equalTo(true)))
                .andExpect(jsonPath("totalElements", Matchers.greaterThanOrEqualTo(100)))
                .andExpect(jsonPath("totalPages", Matchers.greaterThanOrEqualTo(5)));
    }
}
