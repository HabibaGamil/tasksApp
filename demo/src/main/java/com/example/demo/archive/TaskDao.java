package com.example.demo.archive;

import com.example.demo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int deleteTaskById(int id){
        String sql="DELETE FROM Task WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[] {id});
    }

    public int updateTask(Task task){
        String sql = "UPDATE Task SET Title= ?, Description = ?, Status= ? WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[] {task.getTitle(),
                                                       task.getDescription(),
                                                       task.getStatus(),
                                                       task.getId()});
    }
    public int insertTask(Task task){
        String sql = " INSERT INTO Task (ID,Title,Description,status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] {
                                                      task.getId(),
                                                      task.getTitle(),
                                                      task.getDescription(),
                                                      task.getStatus(),
                                                      });
    }

    public Task getTaskById(int id) {
        String sql = "SELECT * FROM Task WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Task>(Task.class), new Object[] {id});
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM Task";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Task>(Task.class));
    }
}
