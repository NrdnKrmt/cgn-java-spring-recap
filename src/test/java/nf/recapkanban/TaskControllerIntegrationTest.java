package nf.recapkanban;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskRepository taskRepository;

    @Test
    void getAllTasksTest() throws Exception {
        //GIVEN
        Task testTask = new Task("35", "Read", Status.TODO);
        taskRepository.save(testTask);

        //WHEN
        mockMvc.perform(get("/api/todo"))

        //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "35",
                                "name": "Read",
                                "status": "ToDo"
                            }
                        ]
                        """));
    }
}
