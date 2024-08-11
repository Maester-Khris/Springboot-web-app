package nk.projects.webapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import nk.projects.webapp.models.Task;
import nk.projects.webapp.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskrepo;
    public TaskServiceImpl(TaskRepository taskrepo){
        this.taskrepo = taskrepo;
    }

    @Override
    public Task saveTask(Task a_task) {
       Optional<Task> task = taskrepo.findByTasktitle(a_task.getTasktitle());
       if(task.isPresent()){
            throw new RuntimeException("A task with the name "+task.get().getTasktitle()+" already exists");
       }
       return taskrepo.save(a_task);
    }

    @Override
    public List<Task> getAllTask() {
        // return taskrepo.findAll();
        return StreamSupport.stream(taskrepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<Task> getTaskById(int task_id) {
        return taskrepo.findById(task_id);
    }
}
