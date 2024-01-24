package com.example.demo;

import com.example.demo.exceptions.TaskNotFound;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.notification.services.NotificationService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.model.TestTimedOutException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(TaskController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    TaskService taskService;
    @MockBean
    TaskRepository taskRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Task TASK1 = new Task(1,"Angular Task","Set up basic angular project",false);
        Task TASK2 = new Task(2,"React Task","Set up basic react project",false);
        Task TASK3 = new Task(3,"Spring Task","Set up basic spring project",true);

        List<Task> tasks = new ArrayList<>(Arrays.asList(TASK1,TASK2,TASK3));
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);
        Mockito.when(taskRepository.findById(1)).thenReturn(Optional.of(TASK1));
        Mockito.when(taskRepository.findById(2)).thenReturn(Optional.of(TASK2));
        Mockito.when(taskRepository.findById(3)).thenReturn(Optional.of(TASK3));
        Mockito.when(taskRepository.findById(4)).thenReturn(Optional.empty());

    }
    @Test
    public void getAllTasksSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));

    }
    @Test
    public void getTaskSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("Angular Task")));
    }

    @Test
    public void getTaskFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tasks/4")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is4xxClientError())
                        .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof TaskNotFound));
    }
    @Test
    public void longRunningTestFail() throws InterruptedException {
            TimeUnit.SECONDS.sleep(20);

    }
}
