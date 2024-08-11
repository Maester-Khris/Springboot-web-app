package nk.projects.webapp.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import nk.projects.webapp.models.Task;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskrepoUnittest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Test N°1: Save test method")
    @Order(1)
    @Rollback(value=true)
    public void testSaveTask(){
        // action
        Task t = Task.builder().tasktitle("Testing with JUnit").taskfinish(true).build();
        taskRepository.save(t);
        System.out.println("Insertion finished");
        // verification
        Assertions.assertThat(t.getId()).isGreaterThan(0);
        System.out.println("Verification finished");
    }

    @Test
    @DisplayName("Test N°2: Get test method")
    @Order(2)
    public void testGetTask(){
        Optional<Task> t = taskRepository.findById(1);
        System.out.println(t);
        Assertions.assertThat(t.get().getId()).isEqualTo(1);
    }
}
