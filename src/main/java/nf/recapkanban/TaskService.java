package nf.recapkanban;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    TaskRepository taskRepository;
    IdService idService;

    public TaskService(TaskRepository taskRepository, IdService idService) {
        this.taskRepository = taskRepository;
        this.idService = idService;
    }

    public List<Task> getAllCharacters() {
        return taskRepository.findAll();
    }
}
