package com.example.taskmanage.controller;

import com.example.taskmanage.model.Dependencies;
import com.example.taskmanage.model.Tasks;
import com.example.taskmanage.service.IDependenciesService;
import com.example.taskmanage.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/dependencies")
public class DependenciesController {
    @Autowired
    private IDependenciesService dependenciesService;

    @GetMapping("/list")
    public ResponseEntity<List<Dependencies>> getAllTasks(){
        List<Dependencies> dependenciesList = dependenciesService.findAllDependencies();
        if(dependenciesList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dependenciesList,HttpStatus.OK);
    }
}
