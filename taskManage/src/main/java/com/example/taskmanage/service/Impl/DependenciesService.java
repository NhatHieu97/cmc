package com.example.taskmanage.service.Impl;

import com.example.taskmanage.model.Dependencies;
import com.example.taskmanage.model.Tasks;
import com.example.taskmanage.repository.DependenciesRepository;
import com.example.taskmanage.service.IDependenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DependenciesService implements IDependenciesService {
    @Autowired
    private DependenciesRepository dependenciesRepository;
    @Override
    public List<Dependencies> findAllDependencies() {
        return dependenciesRepository.findAll();
    }

    @Override
    public void createDependencies(Dependencies dependencies) {
        dependenciesRepository.save(dependencies);
    }
}
