package nk.projects.webapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import nk.projects.webapp.models.Task;
import nk.projects.webapp.services.TaskService;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskcontrollerUnittest {
    @Autowired
    MockMvc mockmvc;

    @MockBean
    TaskService task_service;

    @Autowired
    ObjectMapper object_mapper;

    Task test_task;
    @BeforeEach()
    public void setup(){
        test_task = Task.builder()
            .tasktitle("started a new learning")
            .taskfinish(false).build();
    }

    @Test
    @DisplayName("Controller Test N°1: test get method and mock task service")
    @Order(1)
    public void gettest() throws Exception{
        // precondition
        List<Task> tasks = new ArrayList();
        tasks.add(test_task);
        tasks.add(Task.builder().tasktitle("Another one").taskfinish(true).build());
        given(task_service.getAllTask()).willReturn(tasks);

        //action
        ResultActions response = mockmvc.perform(get("/tasks/"));

        //verification
        response.andExpect(status().isOk())
            .andDo(print())
            .andExpect(jsonPath("$.size()", is(tasks.size())));
    }

    @Test
    @DisplayName("Controller Test N°2: test save method and mock task service")
    @Order(2)
    public void saveTest() throws Exception{
        // precondition
        given(task_service.getTaskById(test_task.getId()))
            .willReturn(Optional.of(test_task));

        // action
        ResultActions response = mockmvc.perform(
            post("/task/add/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(object_mapper.writeValueAsString(test_task))
        );

        // verification
        response.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.tasktitle", is(test_task.getTasktitle())))
            .andExpect(jsonPath("$.taskfinish", is(test_task.isTaskfinish())));
    }
}
