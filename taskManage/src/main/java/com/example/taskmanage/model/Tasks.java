package com.example.taskmanage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String date;
    private String end;
    private int progress;

    private  Boolean main;

    private  Boolean sub;

    @JsonBackReference(value = "dependencies_tasks")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tasks")

    private List<Dependencies> dependencies;

    public Tasks() {
    }

    public Tasks(Long id, String name, String date, String end, int progress, Boolean main, Boolean sub) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.end = end;
        this.progress = progress;
        this.main = main;
        this.sub = sub;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getSub() {
        return sub;
    }

    public void setSub(Boolean sub) {
        this.sub = sub;
    }

    public List<Dependencies> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependencies> dependencies) {
        this.dependencies = dependencies;
    }
}
