package nk.projects.webapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nk.projects.webapp.models.Task;
import nk.projects.webapp.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskserviceUnittest {
    @Mock
    TaskRepository taskrepo;
    @InjectMocks
    TaskServiceImpl taskService;
    
    private Task test_task;
    @BeforeEach()
    public void setup(){
        test_task = Task.builder()
            .tasktitle("started a new learning")
            .taskfinish(false).build();
    }

    @Test
    @Order(1)
    public void saveMethodTest(){
        //precondition
        given(taskrepo.save(test_task)).willReturn(test_task);
        //action
        Task t = taskService.saveTask(test_task);
        //verification
        System.out.println(t);
        assertThat(t).isNotNull();
    }

    @Test
    @Order(2)
    public void getallMethodTest(){
        //precondition
        Task test_task1 = Task.builder().tasktitle("Another one").taskfinish(true).build();
        given(taskrepo.findAll()).willReturn(List.of(test_task, test_task1));
        //action
        List<Task> tasks = taskService.getAllTask();
        //verification
        System.out.println(tasks);
        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isGreaterThan(1);
    }
}
