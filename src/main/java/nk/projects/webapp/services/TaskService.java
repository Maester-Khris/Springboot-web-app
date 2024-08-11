package nk.projects.webapp.services;

import nk.projects.webapp.models.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task saveTask(Task a_task);
    List<Task> getAllTask();
    Optional<Task> getTaskById(int task_id);
}
