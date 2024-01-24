package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService service;
    @GetMapping("/welcome")
    public String welcome() {
        return "Task REST API";
    }

    @GetMapping("/tasks")
    public List<Task> allTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable int id) {
        return service.getTask(id);
    }
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        task.setId(0);
        return service.addTask(task);
    }
    @PatchMapping("/tasks/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
       return service.updateTask(id,task);
    }

    @DeleteMapping("tasks/{id}")
    public void deleteTask(@PathVariable int id) {
        service.deleteTask(id);
    }
}
