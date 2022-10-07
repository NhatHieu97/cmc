package com.example.taskmanage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String end;
    private int progress;

    private  Boolean main;

    private  Boolean sub;



    @ManyToOne
    @JoinColumn(name = "id_dependencies", referencedColumnName = "id")

    private Dependencies dependencies;

    public Tasks() {
    }

    public Tasks(Long id, String name, String date, String end, int progress, Dependencies dependencies) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.end = end;
        this.progress = progress;
        this.dependencies = dependencies;
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

    public Dependencies getDependencies() {
        return dependencies;
    }

    public void setDependencies(Dependencies dependencies) {
        this.dependencies = dependencies;
    }
}
