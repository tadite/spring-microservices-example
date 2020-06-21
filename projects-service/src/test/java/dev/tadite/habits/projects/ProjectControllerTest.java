package dev.tadite.habits.projects;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProjectController.class)
class ProjectControllerTest {

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetWithPage() throws Exception {
        //given
        Pageable expectedPageabe = PageRequest.of(0,
                25,
                Sort.by(Sort.Order.desc("createdAt")));

        //when
        mockMvc.perform(get("/projects")
                .param("page", "0")
                .param("size", "25")
                .param("sort", "createdAt,desc")
        )
                .andExpect(status().isOk());

        //then
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(projectRepository).findAll(pageableCaptor.capture());
        Pageable pageable = pageableCaptor.getValue();
        assertThat(pageable, equalTo(expectedPageabe));
    }
}