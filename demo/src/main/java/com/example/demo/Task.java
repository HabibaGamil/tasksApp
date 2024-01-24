package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name="get_all_tasks", query="select t from Task t")
public class Task {
    @Id
    @GeneratedValue
    private int id;
    String title;
    String description;
    Boolean status;

    public Task(){

    }
    public Task(String title,String description, Boolean status){
        this.title=title;
        this.description=description;
        this.status=status;
    }
    public Task(int id,String title,String description, Boolean status){
        this.id=id;
        this.title=title;
        this.description=description;
        this.status=status;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public Boolean getStatus(){
        return this.status;
    }
}
