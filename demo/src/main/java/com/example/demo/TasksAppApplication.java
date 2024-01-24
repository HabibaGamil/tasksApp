package com.example.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

import java.util.List;

@SpringBootApplication
public class TasksAppApplication{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TaskRepository repo;

//	@Autowired
//	TaskDao dao;

	public static void main(String[] args) {
		System.out.println(SpringVersion.getVersion());
		SpringApplication.run(TasksAppApplication.class, args);

	}

//	@Override
//	public void run(String... args) throws Exception {
//	repo.insertTask(new Task("Angular Project","Basic angular project",false));
//	repo.insertTask(new Task("React Project","Basic React project",false));
//	repo.insertTask(new Task("Spring Project","Basic Spring project",false));
//	repo.updateTask(new Task(2,"React Project","Basic React project",true));
//	//repo.deleteById(1);
//		List<Task> tasks = repo.getAllTasks();
//		for(Task task : tasks){
//			System.out.println("Task "+task.getId());
//			System.out.println("Title:  "+task.getTitle());
//			System.out.println("Description:  "+task.getDescription());
//			System.out.println("Status: "+ task.getStatus());
//			System.out.println("------------------------------------");
//		}
//	}


//	@Override
//	public void run(String... args) throws Exception {
//
//		logger.info("All Players Data: {}", dao.getAllTasks());
//
//		logger.info("Deleting Player with Id 1: {}", dao.deleteTaskById(1));
//
//		logger.info("Updating Player with Id 2: {}",  dao.updateTask(new Task(2,
//				"Angular Project","Basic angular project",false)));
//
//		logger.info("Insert new task with id 3: {}",  dao.insertTask(new Task(3,
//				"React Project","Basic React project setup",false)));
//
//		logger.info("All Players Data: {}", dao.getAllTasks());
//
//	}

}
