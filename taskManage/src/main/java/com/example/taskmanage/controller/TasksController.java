package com.example.taskmanage.controller;

import com.example.taskmanage.model.Tasks;
import com.example.taskmanage.model.dto.TasksDto;
import com.example.taskmanage.service.ITaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {
    @Autowired
    private ITaskService taskService;

    @GetMapping("/list")
    public ResponseEntity<Page<Tasks>> getAllTasks(){
        Page<Tasks> tasksPage = taskService.findAllTask(Pageable.unpaged());
        if(tasksPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasksPage,HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<Tasks> getById(@PathVariable Long id) {
        Tasks tasks = taskService.findById(id);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Tasks> deleteEmployee(@PathVariable Long id) {
        Tasks tasks = taskService.findById(id);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        taskService.deleteById(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@Validated @RequestBody TasksDto tasksDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            Map<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
            });
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Tasks tasks = new Tasks();
        BeanUtils.copyProperties(tasksDto,tasks);
        taskService.createTasks(tasks);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
