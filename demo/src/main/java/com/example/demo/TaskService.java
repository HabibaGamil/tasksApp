package com.example.demo;

import com.example.demo.exceptions.InvalidIdException;
import com.example.demo.exceptions.TaskNotFound;
import com.utils.notification.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository repo;


    @Autowired
    @Qualifier("emailNotificationService")
    NotificationService notificationService;

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Task addTask(Task t) {
        notificationService.sendNotification();
        return repo.save(t);
    }
    public Task getTask(int id) {
        Optional<Task> tempTask = repo.findById(id);

        if(tempTask.isEmpty()){
            System.out.println("I was here no task file");
            throw new TaskNotFound("Task with id {"+ id +"} not found");
        }
        return tempTask.get();
    }

    public Task updateTask(int id, Task task) {
        Optional<Task> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty()){
            throw new InvalidIdException("Task with id {"+ id +"} not found");
        }
        task.setId(id);
        return repo.save(task);
    }

    public void deleteTask(int id) {
        Optional<Task> tempPlayer = repo.findById(id);

        if(tempPlayer.isEmpty()){
            throw new InvalidIdException("Task with id {"+ id +"} not found");
        }
        repo.delete(tempPlayer.get());
    }
}
