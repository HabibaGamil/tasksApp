package com.example.demo.archive;

import com.example.demo.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

//@Repository
@Transactional
public class TaskRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Task insertTask(Task task){
        System.out.println("in insert");
        return entityManager.merge(task);
    }
    public Task updateTask(Task task){

        return entityManager.merge(task);
    }
    public Task getTaskById(int id){

        return entityManager.find(Task.class, id);
    }
    public void deleteById(int id){
        Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
    }
    public List<Task> getAllTasks() {
        TypedQuery<Task> getAll = entityManager.createNamedQuery("get_all_tasks", Task.class);
        return getAll.getResultList();
    }

}
