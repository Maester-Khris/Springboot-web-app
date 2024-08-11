package nk.projects.webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nk.projects.webapp.models.Task;
import nk.projects.webapp.repository.TaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskrepo;
    
    @GetMapping("/")
    public ResponseEntity<Iterable<Task>> getAllTask() {
        return ResponseEntity.ok(taskrepo.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<String> plusoneTask(@RequestBody Task newTask) {
       taskrepo.save(newTask);
        return new ResponseEntity<>("Everything is good", HttpStatus.CREATED);
    }
}
