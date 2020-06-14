package dev.tadite.habits.tracker;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class RecordControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RecordRepository recordRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(recordRepository).isNotNull();
    }

    @Test
    public void givenRecord_whenGetRecordById_thenStatus200() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(2020, 3, 3, 10, 2, 3, 1);
        LocalDateTime endDateTime = LocalDateTime.of(2020, 3, 3, 11, 2, 3, 1);
        Record record = recordRepository.save(new Record(null, "1", startDateTime, endDateTime, "desc"));

        mvc.perform(get("/api/records/{id}", record.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].projectId", is(record.getProjectId())));
    }

}