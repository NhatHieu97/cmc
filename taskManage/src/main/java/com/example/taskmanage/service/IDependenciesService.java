package com.example.taskmanage.service;

import com.example.taskmanage.model.Dependencies;
import com.example.taskmanage.model.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDependenciesService {
    List<Dependencies> findAllDependencies();
}
