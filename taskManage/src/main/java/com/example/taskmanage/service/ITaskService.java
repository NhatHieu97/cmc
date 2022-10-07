package com.example.taskmanage.service;

import com.example.taskmanage.model.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskService {
    Page<Tasks> findAllTask(Pageable pageable);
    void createTasks (Tasks tasks);
    Tasks findById(Long id);
    void deleteById(Long id);

}
