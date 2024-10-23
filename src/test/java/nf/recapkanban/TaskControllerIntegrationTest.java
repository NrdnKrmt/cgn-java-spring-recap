package nf.recapkanban;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        Task testTask = new Task("123", "test-description", "test-status");
        taskRepository.save(testTask);

        //WHEN
        mockMvc.perform(get("/api/todo"))

        //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "123",
                                "description": "test-description",
                                "status": "test-status"
                            }
                        ]
                        """));
    }

    @Test
    void getTaskByIdTest() throws Exception {
        //GIVEN
        Task testTask = new Task("123", "test-description", "test-status");
        taskRepository.save(testTask);

        //WHEN
        mockMvc.perform(get("/api/todo/123"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "id": "123",
                                "description": "test-description",
                                "status": "test-status"
                            }
                        """));
    }

    @Test
    void addTaskTest() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "test-description",
                                  "status": "test-status"
                                }
                                """))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                  "description": "test-description",
                                  "status": "test-status"
                            }
                        """));
    }

    @Test
    void updateTaskTest() throws Exception {
        //GIVEN
        Task oldTask = new Task("123", "old-description", "old-status");
        taskRepository.save(oldTask);

        //WHEN
        mockMvc.perform(put("/api/todo/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "test-description",
                                  "status": "test-status"
                                }
                                """))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                  "id": "123",
                                  "description": "test-description",
                                  "status": "test-status"
                            }
                        """));
    }

    @Test
    void deleteTaskTest() throws Exception {
        //GIVEN
        Task testTask = new Task("123", "test-description", "test-status");
        taskRepository.save(testTask);

        //WHEN
        mockMvc.perform(delete("/api/todo/123"))

                //THEN
                .andExpect(status().isOk());
                assertFalse(taskRepository.existsById("123"));
    }
}
