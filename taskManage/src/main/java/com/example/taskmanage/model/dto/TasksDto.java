package com.example.taskmanage.model.dto;

import com.example.taskmanage.model.Dependencies;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class TasksDto implements Validator {
    private Long id;
    private String name;
    private String date;
    private String end;
    private int progress;
    private List<Dependencies> dependencies;

    public TasksDto() {
    }

    public TasksDto(Long id, String name, String date, String end, int progress, List<Dependencies> dependencies) {
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

    public List<Dependencies> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependencies> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
