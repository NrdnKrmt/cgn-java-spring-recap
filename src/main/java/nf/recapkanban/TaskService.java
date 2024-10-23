package nf.recapkanban;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TaskService {

    TaskRepository taskRepository;
    IdService idService;

    public TaskService(TaskRepository taskRepository, IdService idService) {
        this.taskRepository = taskRepository;
        this.idService = idService;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).get();
    }

    public Task addTask(TaskDto taskDto) {
        Task task = new Task(idService.randomId(), taskDto.description(), taskDto.status());
        return taskRepository.save(task);
    }

    public Task updateTask(String id, TaskDto taskDto) {
        Task task = new Task(id, taskDto.description(), taskDto.status());
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
