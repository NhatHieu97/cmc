package com.example.taskmanage.service.Impl;

import com.example.taskmanage.model.Tasks;
import com.example.taskmanage.repository.TasksRepository;
import com.example.taskmanage.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {
    @Autowired
    private TasksRepository tasksRepository;
    @Override
    public Page<Tasks> findAllTask(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }

    @Override
    public void createTasks(Tasks tasks) {
        tasksRepository.save(tasks);
    }


    @Override
    public Tasks findById(Long id) {
        return tasksRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        tasksRepository.deleteById(id);
    }
}
