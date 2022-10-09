package com.example.taskmanage.controller;

import com.example.taskmanage.model.Dependencies;
import com.example.taskmanage.model.Tasks;
import com.example.taskmanage.model.dto.TasksDto;
import com.example.taskmanage.service.IDependenciesService;
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
    @Autowired
    private IDependenciesService dependenciesService;

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
    public ResponseEntity<?> create(@Validated @RequestBody TasksDto tasksDto, BindingResult bindingResult) {
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

    @PostMapping("/createSub/{id}")
    public ResponseEntity<?> createMainSub(@Validated @PathVariable Long id, @RequestBody TasksDto tasksDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            Map<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
            });
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Tasks tasks = taskService.findById(id);
        if(tasks == null){
            return null;
        }
        tasks.setMain(true);
        taskService.createTasks(tasks);
        Tasks subTasks = new Tasks();
        BeanUtils.copyProperties(tasksDto,subTasks);
        subTasks.setSub(true);
        taskService.createTasks(subTasks);
        Dependencies dependencies = new Dependencies(subTasks.getName(),tasks);
        dependenciesService.createDependencies(dependencies);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Validated @PathVariable Long id, @RequestBody TasksDto tasksDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            Map<String, Object> response = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
            });
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Tasks tasks = taskService.findById(id);
        tasks.setName(tasksDto.getName());
        tasks.setDate(tasksDto.getDate());
        tasks.setEnd(tasksDto.getEnd());
        tasks.setProgress(tasksDto.getProgress());

        if(tasks.getMain() == true){
            if(tasks.getSub() == true){

                Dependencies dependencies = new Dependencies();
                dependencies.setName(tasks.getName());
                dependencies.setTasks(tasks);

            }

        }


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
